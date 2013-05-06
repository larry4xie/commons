package my.commons.framework.struts2.interceptor;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * <p>
 * 将Struts2 的 FieldError 和 Error 转换成json格式写入response返回的拦截器
 * </p>
 * 
 * <p>
 * 例子：
 * <pre>
 * &lt;interceptor-ref name="jsonWorkflow"&gt;
 * 	&lt;param name="failedStatus">448&lt;/param&gt;
 * 	&lt;param name="encoding">UTF-8&lt;/param&gt;
 * &lt;/interceptor-ref&gt;
 * </pre>
 * </p>
 * 
 * @author xiegang
 * @since 2011-12-27
 *
 */
public class JSONWorkflowInterceptor extends MethodFilterInterceptor {
	private static final long serialVersionUID = 2690938894293946448L;

	private static final Logger LOG = LoggerFactory.getLogger(JSONWorkflowInterceptor.class);

    private static final String VALIDATE_JSON_PARAM = "struts.enableJSONWorkflow";
    private static final String ENCODING_SET_PARAM = "struts.JSONWorkflow.encoding";

	/**
	 * 有error 时的 html 状态吗
	 */
	private int failedStatus = HttpServletResponse.SC_BAD_REQUEST; // default 400

	/**
	 * 编码
	 */
	private String encoding = "UTF-8";

	/**
	 * 是否开启
	 */
	private String enableJSONWorkflow = "true";

	/**
	 * HTTP status that will be set in the response if validation fails
	 * 
	 * @param validationFailedStatus
	 */
	public void setFailedStatus(int failedStatus) {
		this.failedStatus = failedStatus;
	}

	/**
	 * @param encoding
	 *            the encoding to set
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * @param enableJSONWorkflow
	 *            the enableJSONWorkflow to set
	 */
	public void setEnableJSONWorkflow(String enableJSONWorkflow) {
		this.enableJSONWorkflow = enableJSONWorkflow;
	}

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();

		Object action = invocation.getAction();

		if (isJsonEnabled(request)) {
			if (action instanceof ValidationAware) {
				// generate json
				ValidationAware validationAware = (ValidationAware) action;
				if (validationAware.hasErrors()) {
					return generateJSON(request, response, validationAware);
				}
			}
		}
		return invocation.invoke();
	}

	private void setupEncoding(HttpServletResponse response, HttpServletRequest request) {
		String encoding = getSetEncoding(request);
		if (null != encoding) {
			LOG.debug("set encoding from request " + encoding);
			response.setCharacterEncoding(encoding);
		} else {
			if (LOG.isDebugEnabled()) {
				LOG.debug("Setting up encoding to: [" + this.encoding + "]!");
			}
			response.setCharacterEncoding(this.encoding);
		}
	}

	private String generateJSON(HttpServletRequest request, HttpServletResponse response, ValidationAware validationAware) throws IOException {
		if (failedStatus >= 0) {
			response.setStatus(failedStatus);
		}
		setupEncoding(response, request);
		String errorsJson = buildResponse(validationAware);
		response.getWriter().print(errorsJson);
		if (LOG.isDebugEnabled()) {
			LOG.debug("write to response json errors = " + errorsJson);
		}
		response.setContentType("application/json");
		response.flushBuffer();
		return Action.NONE;
	}

	/**
	 * 是否进行jsonworkflow的转换
	 * 
	 * @param request
	 * @return
	 */
	private boolean isJsonEnabled(HttpServletRequest request) {
		String enable = request.getParameter(VALIDATE_JSON_PARAM);
		return null != enable ? "true".equals(enable) : "true".equals(this.enableJSONWorkflow);
	}

	/**
	 * 获取请求要求的response编码
	 * 
	 * @param request
	 * @return
	 */
	private String getSetEncoding(HttpServletRequest request) {
		return request.getParameter(ENCODING_SET_PARAM);
	}

	/**
	 * @return JSON string that contains the errors and field errors
	 */
	protected String buildResponse(ValidationAware validationAware) {
		// should we use FreeMarker here?
		StringBuilder sb = new StringBuilder();
		sb.append("{ ");

		if (validationAware.hasErrors()) {
			// action errors
			if (validationAware.hasActionErrors()) {
				sb.append("\"errors\":");
				sb.append(buildArray(validationAware.getActionErrors()));
			}

			// field errors
			if (validationAware.hasFieldErrors()) {
				if (validationAware.hasActionErrors())
					sb.append(",");
				sb.append("\"fieldErrors\": {");
				Map<String, List<String>> fieldErrors = validationAware.getFieldErrors();
				for (Map.Entry<String, List<String>> fieldError : fieldErrors.entrySet()) {
					sb.append("\"");
					// if it is model driven, remove "model." see WW-2721
					String fieldErrorKey = fieldError.getKey();
					sb.append(((validationAware instanceof ModelDriven<?>) && fieldErrorKey.startsWith("model.")) ? fieldErrorKey.substring(6) : fieldErrorKey);
					sb.append("\":");
					sb.append(buildArray(fieldError.getValue()));
					sb.append(",");
				}
				// remove trailing comma, IE creates an empty object, duh
				sb.deleteCharAt(sb.length() - 1);
				sb.append("}");
			}
		}

		sb.append("}");
		/*response should be something like:
         * {
         *      "errors": ["this", "that"],
         *      "fieldErrors": {
         *            field1: "this",
         *            field2: "that"
         *      }
         * }
         */
		return sb.toString();
	}

	private String buildArray(Collection<String> values) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (String value : values) {
			sb.append("\"");
			sb.append(StringEscapeUtils.escapeEcmaScript(value));
			sb.append("\",");
		}
		if (values.size() > 0)
			sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}
}
