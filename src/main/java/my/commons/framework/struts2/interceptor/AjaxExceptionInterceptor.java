package my.commons.framework.struts2.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.commons.Result;
import my.commons.exception.AppException;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.TextProvider;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Ajax Exception Interceptor
 * @author xiegang
 * @since 2013-4-1
 *
 */
public class AjaxExceptionInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 6744388894795338116L;
	
	private static final Logger LOG = LoggerFactory.getLogger(AjaxExceptionInterceptor.class);
	
	/** 编码 */
	private String encoding = "UTF-8";
	
	/** HTTP status */
	private int httpStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	
	private final static String CONTENTTYPE_JSON = "application/json";

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();
		} catch (AppException e) {
			Object action = invocation.getAction();
			if (action instanceof TextProvider) {
				TextProvider tp = (TextProvider) action;
				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setStatus(httpStatus);
				response.setContentType(CONTENTTYPE_JSON);
				response.setCharacterEncoding(encoding);
				
				PrintWriter out = response.getWriter();
				Result result = new Result(e.getExCode(), tp.getText("ex." + e.getExCode()));
				out.print(result.toJsonString()); // output Ajax String
				if (LOG.isDebugEnabled()) {
					LOG.debug(new StringBuffer("Catch a Appexception and write to response json errors = ").append(result.toJsonString()).toString());
				}
				out.flush();
			}
			return null;
		}
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
}
