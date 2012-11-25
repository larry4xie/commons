package my.commons.lang.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

/**
 * <p>
 * 反射工具类
 * </p>
 * 
 * @author xiegang
 * @since 2011-11-22
 * 
 */
public class ReflectionUtils {
	/**
	 * 使非public属性可以调用
	 * 
	 * @param 属性反射类
	 * @see java.lang.reflect.Field#setAccessible
	 */
	public static void makeAccessible(Field field) {
		if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
			field.setAccessible(true);
		}
	}

	/**
	 * 使非public方法可以调用
	 * 
	 * @param 方法反射类
	 * @see java.lang.reflect.Method#setAccessible
	 */
	public static void makeAccessible(Method method) {
		if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers())) && !method.isAccessible()) {
			method.setAccessible(true);
		}
	}

	/**
	 * 使非public构造方法可以调用
	 * 
	 * @param 构造方法反射类
	 * @see java.lang.reflect.Constructor#setAccessible
	 */
	public static void makeAccessible(Constructor<?> ctor) {
		if ((!Modifier.isPublic(ctor.getModifiers()) || !Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) && !ctor.isAccessible()) {
			ctor.setAccessible(true);
		}
	}

	/**
	 * 调用Getter方法
	 * 
	 * @param target
	 *            目标对象
	 * @param fieldName
	 *            属性名称
	 * @return get方法返回值
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 */
	public static Object invokeGetterMethod(Object target, String fieldName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		String getterMethodName = "get" + StringUtils.capitalize(fieldName);
		return MethodUtils.invokeMethod(target, getterMethodName);
	}  
  
	/**
	 * 调用Setter方法
	 * 
	 * @param target
	 *            目标对象
	 * @param fieldName
	 *            属性名称
	 * @param value
	 *            属性值
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 */
	public static void invokeSetterMethod(Object target, String fieldName, Object value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		String setterMethodName = "set" + StringUtils.capitalize(fieldName);
		MethodUtils.invokeMethod(target, setterMethodName, value);
	} 
}
