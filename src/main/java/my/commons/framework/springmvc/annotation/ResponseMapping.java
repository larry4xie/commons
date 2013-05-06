package my.commons.framework.springmvc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 响应的映射，默认的name是ERROR
 * @author xiegang
 * @since 2013-5-6
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseMapping {
	String name() default ERROR;
	String value();
	
	// constant
	public static final String ERROR = "ERROR";
}
