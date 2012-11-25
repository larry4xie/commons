package my.commons.framework.struts2;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

/**
 * <p>
 * 自定义struts2 ActionSupport,用于扩展功能<br/>
 * 主要扩展功能:
 * <ol>
 * <li>增加快捷方法请求参数(getParamter*)</li>
 * <li>增加快捷方法向请求中添加数据(model)，用于返回数据</li>
 * </ol>
 * </p>
 * 
 * <p>
 * 例子：
 * <pre>
 * this.addModel("user", user);
 * ==
 * HttpServletRequest request = ServletActionContext.getRequest();
 * request.setAttribute("user", user);
 * </pre>
 * </p>
 * 
 * @author xiegang
 * @since 2012-8-10
 *
 */
public class ActionSupport extends com.opensymphony.xwork2.ActionSupport {
	private static final long serialVersionUID = 1925223172838436991L;
	
	private HttpServletRequest request;
	
	/**
	 * 延迟初始化获取Request
	 * 
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		if(null == request) {
			synchronized (this) {
				if(null == request) {
					request = ServletActionContext.getRequest();
				}
			}
		}
		return request;
	}

	/**
	 * 向请求中添加数据
	 * @param attributeName 数据名称
	 * @param attributeValue 数据值
	 * @return
	 */
	public ActionSupport addModel(String attributeName, Object attributeValue) {
		HttpServletRequest request = this.getRequest();
		request.setAttribute(attributeName, attributeValue);
		return this;
	}
	
	/**
	 * 批量向请求中添加数据
	 * @param modelMap 数据Map
	 * @return
	 */
	public ActionSupport addModel(Map<String, ?> modelMap) {
		HttpServletRequest request = this.getRequest();
		for(Entry<String, ?> entry : modelMap.entrySet()) {
			request.setAttribute(entry.getKey(), entry.getValue());
		}
		return this;
	}
	
	/**
	 * 对请求中删除数据
	 * @param attributeName 数据名称
	 * @return
	 */
	public ActionSupport removeModel(String attributeName) {
		HttpServletRequest request = this.getRequest();
		request.removeAttribute(attributeName);
		return this;
	}
	
	/**
	 * 获取请求中数据
	 * @param <T>
	 * @param attributeName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getModel(String attributeName) {
		HttpServletRequest request = this.getRequest();
		return (T) request.getAttribute(attributeName);
	}
	
	/**
	 * 获取单值请求参数
	 * @param name 参数名
	 * @return
	 */
	public String getParameter(String name) {
		HttpServletRequest request = this.getRequest();
		return request.getParameter(name);
	}
	
	/**
	 * 获取多值请求参数
	 * @param name 参数名
	 * @return
	 */
	public String[] getParameterValues(String name) {
		HttpServletRequest request = this.getRequest();
		return request.getParameterValues(name);
	}
}
