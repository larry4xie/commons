package my.commons.web.taglibs.functions;

import java.io.UnsupportedEncodingException;

import my.commons.web.util.ServletUtils;

/**
 * JSTL Functions
 * 
 * @author xiegang
 * @since 2013-1-23
 *
 */
public class Functions {
	public static String URL_ENCODING = "UTF-8";
	
	public static String encodeURI(String s) throws UnsupportedEncodingException {
		return ServletUtils.encodeURI(s);
	}
	
	public static String decodeURI(String s) throws UnsupportedEncodingException {
		return ServletUtils.decodeURI(s);
	}
}