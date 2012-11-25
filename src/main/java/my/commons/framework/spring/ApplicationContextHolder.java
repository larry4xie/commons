package my.commons.framework.spring;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;

/**
 * <p>
 * 应用程序上下文Holder<br/>
 * 包含如下内容：
 * <ol>
 * <li>javax.servlet.ServletContext</li>
 * <li>org.springframework.context.ApplicationContext</li>
 * </ol>
 * </p>
 * 
 * @author xiegang
 * @since 2011-11-06
 *
 */
public class ApplicationContextHolder {
	private static ServletContext servletContext;
	
	private static ApplicationContext applicationContext;
	
	private ApplicationContextHolder() {}

	/**
	 * 获取持有的上下文
	 * 
	 * @return 没有调用{@link ContextHolder#setApplicationContext(ApplicationContext)}之前返回null
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 设置持有的上下文
	 * 
	 * @param applicationContext
	 */
	public static void setApplicationContext(ApplicationContext applicationContext) {
		ApplicationContextHolder.applicationContext = applicationContext;
	}

	/**
	 * @return the servletContext
	 */
	public static ServletContext getServletContext() {
		return servletContext;
	}

	/**
	 * @param servletContext the servletContext to set
	 */
	public static void setServletContext(ServletContext servletContext) {
		ApplicationContextHolder.servletContext = servletContext;
	}
}
