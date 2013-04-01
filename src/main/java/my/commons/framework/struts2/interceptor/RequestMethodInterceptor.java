package my.commons.framework.struts2.interceptor;

import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.commons.framework.struts2.interceptor.annotation.RequestMethod;

import org.apache.struts2.StrutsStatics;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * <p>
 * HTTP请求方式拦截器<br/>
 * 使用&#64;RequestMethod注解标记哪些方式是允许访问的
 * </p>
 * 
 * <p>
 * 例子：
 * <pre>
 * &lt;interceptor-ref name="requestMethod" /&gt; // 作为第一个拦截器
 * 				
 * &#64;RequestMethod(RequestMethod.Method.POST)
 * public String login()throws Exception{ 
 * 	return "success"; 
 * }
 * </pre>
 * </p>
 * 
 * @author xiegang
 * @since 2012-6-25
 * @see RequestMethod
 *
 */
public class RequestMethodInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -2036505709045166417L;
	private static final Logger LOG = LoggerFactory.getLogger(RequestMethodInterceptor.class);
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String methodName = invocation.getProxy().getMethod();
		Method method = invocation.getAction().getClass().getMethod(methodName);
		if(method != null && method.isAnnotationPresent(RequestMethod.class)){
			ActionContext actionContext = invocation.getInvocationContext();
			HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
			String requestm = request.getMethod();
			
			RequestMethod requestMethod = method.getAnnotation(RequestMethod.class);
			RequestMethod.Method[] ms = requestMethod.value();
			if(LOG.isDebugEnabled()) {
				LOG.debug("RequestMethod = " + requestm);
				LOG.debug("invoke must request method = " + Arrays.toString(ms));
			}
			
			int i = 0;
			for(i = 0; i < ms.length; i++) {
				RequestMethod.Method m = ms[i];
				if(m.name().equalsIgnoreCase(requestm)) {
					// pass
					break;
				}
			}
			if(i >= ms.length) {
				// unpass
				HttpServletResponse response = (HttpServletResponse)actionContext.get(StrutsStatics.HTTP_RESPONSE);
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); // 405
				return null;
			}
		}
		return invocation.invoke();
	}
}
