package my.commons.framework.struts2.interceptor;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.commons.Result;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.TextProvider;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * Form Workflow Interceptor
 * @author xiegang
 * @since 2013-4-3
 *
 */
public class FormWorkflowInterceptor extends MethodFilterInterceptor {
	private static final long serialVersionUID = 6104247482633525701L;
	
	private static final Logger LOG = LoggerFactory.getLogger(FormWorkflowInterceptor.class);

	/** 返回Result名称 */
	private String resultName = ActionSupport.INPUT;
	
	/** 错误编码 */
	private int errorCode = 99000;
	/** 错误消息(i18n) */
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
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute(Result.RET, error.getRet());
			request.setAttribute(Result.MSG, error.getMsg());
			if (LOG.isDebugEnabled()) {
				LOG.debug("Errors on action and write error to request = " + error.toString());
			}
			return resultName;
		}
		// execute action method
		return invocation.invoke();
	}

	public void setResultName(String resultName) {
		this.resultName = resultName;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}