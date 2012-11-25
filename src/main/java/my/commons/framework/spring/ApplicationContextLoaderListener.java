package my.commons.framework.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * <p>
 * 应用程序上下文加载监听器，基于Spring的ContextLoaderListener<br/>
 * 用于初始化应用程序上下文{@link ApplicationContextHolder}
 * </p>
 * 
 * <p>
 * 例子(web.xml)：
 * <pre>
 * &lt;listener&gt;
 * 	&lt;listener-class&gt;
 * 		my.commons.framework.spring.ApplicationContextLoaderListener
 * 	&lt;/listener-class&gt;
 * &lt;/listener&gt;
 * </pre>
 * 
 * @author xiegang
 * @since 2011-11-06
 * 
 */
public class ApplicationContextLoaderListener extends ContextLoaderListener {

	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		ApplicationContextHolder.setServletContext(context);
		super.contextInitialized(event);
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		ApplicationContextHolder.setApplicationContext(ctx);
	}
}
