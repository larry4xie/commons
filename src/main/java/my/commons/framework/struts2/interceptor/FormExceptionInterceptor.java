package my.commons.framework.struts2.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.commons.exception.AppException;
import my.commons.exception.Result;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.TextProvider;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Form Exception Interceptor
 * @author xiegang
 * @since 2013-4-1
 *
 */
public class FormExceptionInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -4032608586742354158L;
	
	private static final Logger LOG = LoggerFactory.getLogger(FormExceptionInterceptor.class);
	
	/** 返回Result名称 */
	private String resultName = ActionSupport.INPUT;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = this.resultName;
		try {
			result = invocation.invoke();
		} catch (AppException e) {
			Object action = invocation.getAction();
			if (action instanceof TextProvider) {
				TextProvider tp = (TextProvider) action;
				Result error = new Result(e.getExCode(), tp.getText("ex." + e.getExCode()));
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute(Result.RET, error.getRet());
				request.setAttribute(Result.MSG, error.getMsg());
				if (LOG.isDebugEnabled()) {
					LOG.debug("Catch a Appexception and write error to request = " + error.toString());
				}
			}
		}
		return result;
	}
	
	public void setResultName(String resultName) {
		this.resultName = resultName;
	}
}
