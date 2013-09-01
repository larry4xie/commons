package my.commons.lang.util;

import org.apache.commons.lang3.CharUtils;

/**
 * <p>
 * String 工具类
 * </p>
 * 
 * @author xiegang
 * @since 2012-11-19
 *
 */
public class StringUtils {
	/**
	 * String trim for String Array
	 * @param strArray
	 * @return
	 */
	public static String[] trim(String... strArray) {
		for (int i = 0; i < strArray.length; i++) {
			strArray[i] = strArray[i].trim();
		}
		return strArray;
	}
	
	/**
	 * 是否空白，包括0
	 * <pre>
	 * StringUtils.isBlankIncludeZero(null)      = true
	 * StringUtils.isBlankIncludeZero("")        = true
	 * StringUtils.isBlankIncludeZero(" ")       = true
	 * StringUtils.isBlankIncludeZero("0")       = true
	 * StringUtils.isBlankIncludeZero("00000")       = true
	 * StringUtils.isBlankIncludeZero("0bob")     = false
	 * StringUtils.isBlankIncludeZero("  b0ob  ") = false
	 * </pre>
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isBlankIncludeZero(CharSequence cs){
		if(org.apache.commons.lang3.StringUtils.isNotBlank(cs)) {
			for(int i = 0; i < cs.length(); i++) {
				if(cs.charAt(i) != '0') {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 是否不空白，包括0
	 * 
	 * @param cs
	 * @return
	 * @see StringUtils#isBlankIncludeZero(CharSequence)
	 */
	public static boolean isNotBlankIncludeZero(CharSequence cs){
		return !StringUtils.isBlankIncludeZero(cs);
	}
	
	/**
	 * Converts the string to the Unicode format
	 * 
	 * @param cs
	 * @return
	 */
	public static String unicodeEscaped(CharSequence cs) {
		StringBuffer buf = new StringBuffer();
		for(int i = 0; i < cs.length(); i++) {
			buf.append(CharUtils.unicodeEscaped(cs.charAt(i)));
		}
		return buf.toString();
	}
}
