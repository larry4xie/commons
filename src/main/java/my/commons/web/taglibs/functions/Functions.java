package my.commons.web.taglibs.functions;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * JSTL Functions
 * 
 * @author xiegang
 * @since 2013-1-23
 *
 */
public class Functions {
	private static String URL_ENCODING = "UTF-8";
	
	public static String encodeURI(String s) throws UnsupportedEncodingException {
		return URLEncoder.encode(s, URL_ENCODING);
	}
	
	public static String decodeURI(String s) throws UnsupportedEncodingException {
		return URLDecoder.decode(s, URL_ENCODING);
	}
}