package my.commons.framework.springmvc;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * Spring MVC 国际化工具类
 * @author xiegangs
 * @since 2013-5-6
 *
 */
public class MessageUtils {
	private MessageUtils() {}
	
	/**
	 * 获取当前request环境下的MessageSource
	 * 
	 * @param request
	 * @return
	 */
	public static MessageSource getMessageSource(HttpServletRequest request) {
		WebApplicationContext context = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		if (context == null) {
			throw new IllegalStateException("No WebApplicationContext found: not in a DispatcherServlet request?");
		}
		return context;
	}
	
	/**
	 * 获取当前request环境下的Locale
	 * 
	 * @param request
	 * @return
	 */
	public static Locale getLocale(HttpServletRequest request) {
		return RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
	}
	
	/**
	 * Retrieve the message for the given code
	 * 
	 * @param request
	 * @param code code of the message
	 * @return
	 * @throws NoSuchMessageException
	 */
	public static String getMessage(HttpServletRequest request, String code) throws NoSuchMessageException {
		return MessageUtils.getMessage(request, code, new Object[]{});
	}
	
	/**
	 * Retrieve the message for the given code
	 * 
	 * @param request
	 * @param code code of the message
	 * @param defaultMessage defaultMessage String to return if the lookup fails
	 * @return
	 */
	public static String getMessage(HttpServletRequest request, String code, String defaultMessage) {
		return MessageUtils.getMessage(request, code, new Object[]{}, defaultMessage);
	}
	
	/**
	 * Retrieve the message for the given code
	 * 
	 * @param request
	 * @param code code of the message
	 * @param args arguments for the message, or <code>null</code> if none
	 * @return
	 * @throws NoSuchMessageException
	 */
	public static String getMessage(HttpServletRequest request, String code, Object[] args) throws NoSuchMessageException {
		WebApplicationContext context = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		if (context == null) {
			throw new IllegalStateException("No WebApplicationContext found: not in a DispatcherServlet request?");
		}
		Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
		return context.getMessage(code, args, locale);
	}
	
	/**
	 * Retrieve the message for the given code
	 * 
	 * @param request
	 * @param code code of the message
	 * @param args arguments for the message, or <code>null</code> if none
	 * @param defaultMessage defaultMessage String to return if the lookup fails
	 * @return
	 */
	public static String getMessage(HttpServletRequest request, String code, Object[] args, String defaultMessage) {
		WebApplicationContext context = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		if (context == null) {
			throw new IllegalStateException("No WebApplicationContext found: not in a DispatcherServlet request?");
		}
		Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
		return context.getMessage(code, args, defaultMessage, locale);
	}
}
