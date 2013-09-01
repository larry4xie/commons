package my.commons.web.taglibs.functions;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

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
		return URLEncoder.encode(s, URL_ENCODING);
	}
	
	public static String decodeURI(String s) throws UnsupportedEncodingException {
		return URLDecoder.decode(s, URL_ENCODING);
	}
	
	/**
	 * 获取当前pageContext的原始请求URI
	 * 
	 * @param pageContext
	 * @return 结果encodeURI的原始请求URI
	 */
	public static String originRequestURI(PageContext pageContext) {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		// request URI
		String requestURI = (String) request.getAttribute("javax.servlet.forward.request_uri");
		if(null == requestURI) {
			requestURI = request.getRequestURI();
		}
		// query String
		String queryString = (String) request.getAttribute("javax.servlet.forward.query_string");
		if(null == queryString) {
			queryString = request.getQueryString();
		}
		return null != queryString ? new StringBuffer(requestURI).append('?').append(queryString).toString() : requestURI;
	}
}