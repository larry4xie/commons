package my.commons.framework.struts2.interceptor;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import my.commons.Constants;
import my.commons.Result;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.TextProvider;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * Ajax Workflow Interceptor
 * @author xiegang
 * @since 2013-4-3
 *
 */
public class AjaxWorkflowInterceptor extends MethodFilterInterceptor {
	private static final long serialVersionUID = 6104247482633525701L;
	
	private static final Logger LOG = LoggerFactory.getLogger(AjaxWorkflowInterceptor.class);

	/** 编码 */
	private String encoding = Constants.ENCODING;
	/** HTTP status */
	private int httpStatus = HttpServletResponse.SC_BAD_REQUEST;
	private final static String CONTENTTYPE_JSON = "application/json";
	
	/** 错误编码 */
	private int errorCode = 99000;
	/** 错误消息 */
	private String errorMsg;
	
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();
		Result error = null;
		if (action instanceof ValidationAware) {
			ValidationAware validationAware = (ValidationAware) action;
			if (validationAware.hasErrors()) { // has action or field errors
				if (errorMsg != null && errorMsg.length() > 0 && action instanceof TextProvider) {
					// using fuzzy unified message
					error = new Result(errorCode, ((TextProvider) action).getText(errorMsg));
				} else {
					Collection<String> actionErrors = validationAware.getActionErrors();
					Map<String, List<String>> fieldErrors = validationAware.getFieldErrors();
					if (actionErrors != null && actionErrors.size() > 0) {
						// get first action error
						for(String msg : actionErrors) {
							error = new Result(errorCode, msg);
							break;
						}
					} else if(fieldErrors != null && fieldErrors.size() > 0) {
						// get first field error
						Set<Entry<String, List<String>>> errorEntry = fieldErrors.entrySet();
						for(Entry<String, List<String>> item : errorEntry) {
							error = new Result(errorCode, item.getValue().get(0));
							break;
						}
					}
				}
			}
		}
		if (error != null) {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setStatus(httpStatus);
			response.setContentType(CONTENTTYPE_JSON);
			response.setCharacterEncoding(encoding);
			
			PrintWriter out = response.getWriter();
			out.print(error.toJsonString());
			if (LOG.isDebugEnabled()) {
				LOG.debug(new StringBuffer("Errors on action and write to response json errors = ").append(error.toJsonString()).toString());
			}
			out.flush();
			return null;
		}
		// execute action method
		return invocation.invoke();
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
}