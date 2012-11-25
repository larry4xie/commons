package my.commons.lang.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * <p>
 * Object工具类
 * </p>
 * 
 * @author xiegang
 * @since 2011-11-22
 *
 */
public class ObjectUtils {
	/**
	 * 检查一个throwable是否是受检查的<br/>
	 * 非RuntimeException和Error的throwable都是受检查的
	 * 
	 * @param ex 
	 * @return throwable是否是受检查的
	 * @see java.lang.Exception
	 * @see java.lang.RuntimeException
	 * @see java.lang.Error
	 */
	public static boolean isCheckedException(Throwable ex) {
		return !(ex instanceof RuntimeException || ex instanceof Error);
	}
	
	/**
	 * 判断一个对象是否是Array,null不是一个Array
	 * 
	 * @param obj
	 */
	public static boolean isArray(Object obj) {
		return (obj != null && obj.getClass().isArray());
	}
	
	/**
	 * 判断一个对象是否是Annotation,null不是一个Annotation
	 * 
	 * @param obj
	 */
	public static boolean isAnnotation(Object obj) {
		return (obj != null && obj.getClass().isAnnotation());
	}
	
	/**
	 * 判断一个对象是否是Enum,null不是一个Enum
	 * 
	 * @param obj
	 */
	public static boolean isEnum(Object obj) {
		return (obj != null && obj.getClass().isEnum());
	}
	
	/**
	 * 判断一个对象是否是Primitive,null不是一个Primitive
	 * 
	 * @param obj
	 * @see     java.lang.Boolean#TYPE
     * @see     java.lang.Character#TYPE
     * @see     java.lang.Byte#TYPE
     * @see     java.lang.Short#TYPE
     * @see     java.lang.Integer#TYPE
     * @see     java.lang.Long#TYPE
     * @see     java.lang.Float#TYPE
     * @see     java.lang.Double#TYPE
     * @see     java.lang.Void#TYPE
	 */
	public static boolean isPrimitive(Object obj) {
		return (obj != null && obj.getClass().isPrimitive());
	}
	
	/**
	 * 判断对象数组中是否含有null的对象
	 * @param objs
	 * @return
	 */
	public static boolean isContainsNull(Object... objs) {
		if(null == objs) {
			return true;
		}
		for(Object obj : objs) {
			if(obj == null) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断对象数组中是否不含有null的对象
	 * @param objs
	 * @return
	 */
	public static boolean isNotContainsNull(Object... objs) {
		return !isContainsNull(objs);
	}

	/**
	 * Object to double<br/>
	 * Object可能是：Double\Integer\BigDecimal\String
	 * @param obj
	 * @return
	 */
	public static double parseDouble(Object obj) throws NumberFormatException {
		if (obj instanceof Double) {
			return (Double)obj;
		} else if (obj instanceof Integer) {
			return (Integer)obj;
		} else if (obj instanceof BigDecimal) {
			return ((BigDecimal)obj).doubleValue();
		} else {
			return Double.parseDouble(obj.toString());
		}
	}
	
	/**
	 * Object to long<br/>
	 * Object可能是：Long\Integer\BigInteger\String
	 * @param obj
	 * @return
	 * @throws NumberFormatException
	 */
	public static long parseLong(Object obj) throws NumberFormatException  {
		if (obj instanceof Long) {
			return (Long)obj;
		} else if(obj instanceof Integer) {
			return ((Integer) obj).longValue();
		} else if(obj instanceof BigInteger) {
			return ((BigInteger) obj).longValue();
		} else {
			return Long.parseLong(obj.toString());
		}
	}
	
	/**
	 * Object to int<br/>
	 * Object可能是：Integer\Long\BigInteger\String
	 * @param obj
	 * @return
	 */
	public static int parseInt(Object obj) throws NumberFormatException  {
		if (obj instanceof Integer) {
			return (Integer)obj;
		} else if (obj instanceof Long) {
			return ((Long)obj).intValue();
		} else if(obj instanceof BigInteger) {
			return ((BigInteger) obj).intValue();
		} else {
			return Integer.parseInt(obj.toString());
		}
	}
	
	/**
	 * Serializable Clone
	 * 
	 * @param obj
	 * @return Object
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static Object serialClone(Serializable obj) throws IOException, ClassNotFoundException {   
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();     
		ObjectOutputStream out = new ObjectOutputStream(byteOut);     
		out.writeObject(obj);            
		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());     
		ObjectInputStream in =new ObjectInputStream(byteIn);           
		return in.readObject();   
	} 
}
