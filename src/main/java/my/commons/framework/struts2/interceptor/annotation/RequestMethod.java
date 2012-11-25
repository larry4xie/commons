package my.commons.framework.struts2.interceptor.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * HTTP请求方式注解
 * </p>
 * 
 * @author xiegang
 * @since 2012-6-25
 *
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMethod {
	public static enum Method {
		GET, HEAD, POST, PUT, DELETE, OPTIONS, TRACE
	}
	
	RequestMethod.Method[] value();
}
