package my.commons.web.util;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import my.commons.Result;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * servlet工具类<br/>
 * 主要功能：
 * <ul>
 * <li>获取真实IP地址</li>
 * <li>简化session部分操作</li>
 * <li>简化cookie部分操作</li>
 * <li>URL相关</li>
 * </ul>
 * </p>
 * @author xiegang
 * @since 2012-11-12
 *
 */
public class ServletUtils {
	/**
	 * 判断请求是否是ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}
	
	/**
	 * 获取IP地址
	 * @param request request current HTTP request
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * Determine the session id of the given request, if any.
	 * @param request current HTTP request
	 * @return the session id, or <code>null</code> if none
	 */
	public static String getSessionId(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return (session != null ? session.getId() : null);
	}
	
	/**
	 * Check the given request for a session attribute of the given name.
	 * Returns null if there is no session or if the session has no such attribute.
	 * Does not create a new session if none has existed before!
	 * @param request current HTTP request
	 * @param name the name of the session attribute
	 * @return the value of the session attribute, or <code>null</code> if not found
	 */
	public static Object getSessionAttribute(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(false);
		return (session != null ? session.getAttribute(name) : null);
	}
	
	/**
	 * Set the session attribute with the given name to the given value.
	 * Removes the session attribute if value is null, if a session existed at all.
	 * Does not create a new session if not necessary!
	 * @param request current HTTP request
	 * @param name the name of the session attribute
	 * @param value the value of the session attribute
	 */
	public static void setSessionAttribute(HttpServletRequest request, String name, Object value) {
		if (value != null) {
			request.getSession().setAttribute(name, value);
		}
		else {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.removeAttribute(name);
			}
		}
	}
	
	/**
	 * Retrieve the first cookie with the given name. Note that multiple
	 * cookies can have the same name but different paths or domains.
	 * @param request current servlet request
	 * @param name cookie name
	 * @return the first cookie with the given name, or <code>null</code> if none is found
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					return cookie;
				}
			}
		}
		return null;
	}
	
	/**
	 * 根据cookie名称返回cookie的值
	 *
	 * @param request http请求
	 * @param name	cookie名称
	 * @return	返回cookie值,值不存在则返回null
	 */
	public static String getCookieValue(HttpServletRequest request, String name){
		Cookie cookie = getCookie(request,name);
		if(cookie==null) {
			return null;
		}
		return cookie.getValue();
	}
	
	/**
	 * 是否是有效的Redirect地址
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isValidRedirectUrl(String url) {
		return url != null && url.startsWith("/") || isAbsoluteUrl(url);
	}

	/**
	 * 是否是绝对地址
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isAbsoluteUrl(String url) {
		final Pattern ABSOLUTE_URL = Pattern.compile("\\A[a-z.+-]+://.*", Pattern.CASE_INSENSITIVE);
		return ABSOLUTE_URL.matcher(url).matches();
	}
	
	/**
	 * 获取请求的路径，应用名称之后的部分<br/>
	 * 比如：/index.jsp
	 * 
	 * @param req
	 * @param query 返回的路径中是否包含QueryString
	 * @return 获取请求的路径
	 */
	public static String buildRequestUrl(HttpServletRequest req, boolean query) {
		return buildRequestUrl(req.getServletPath(), req.getPathInfo(), req.getRequestURI(), req.getContextPath(), query ? req.getQueryString() : null);
	}

	/**
	 * 获取请求的路径，应用名称之后的部分
	 * 
	 * @param servletPath
	 * @param pathInfo
	 * @param requestURI
	 * @param contextPath
	 * @param queryString
	 * @return
	 */
	private static String buildRequestUrl(String servletPath, String pathInfo, String requestURI, String contextPath, String queryString) {
		StringBuilder url = new StringBuilder();
		if (servletPath != null) {
			url.append(servletPath);
			if (pathInfo != null) {
				url.append(pathInfo);
			}
		} else {
			url.append(requestURI.substring(contextPath.length()));
		}
		if (queryString != null) {
			url.append("?").append(queryString);
		}
		return url.toString();
	}
	
	/**
	 * 获取请求的完整请求路径
	 * 
	 * @param req
	 * @param query 返回的路径中是否包含QueryString
	 * @return
	 */
	public static String buildFullRequestUrl(HttpServletRequest req, boolean query) {
		return buildFullRequestUrl(req.getScheme(), req.getServerName(), req.getServerPort(), req.getRequestURI(), req.getContextPath(), req.getServletPath(), req.getPathInfo(), query? req.getQueryString() : null);
	}

	/**
	 * 获取请求的完整请求路径
	 * 
	 * @param scheme
	 * @param serverName
	 * @param serverPort
	 * @param requestURI
	 * @param contextPath
	 * @param servletPath
	 * @param pathInfo
	 * @param queryString
	 * @return
	 */
	private static String buildFullRequestUrl(String scheme, String serverName, int serverPort, String requestURI, String contextPath, String servletPath, String pathInfo, String queryString) {
		scheme = scheme.toLowerCase();
		StringBuilder url = new StringBuilder();
		url.append(scheme).append("://").append(serverName);
		// Only add port if not default
		if ("http".equals(scheme)) {
			if (serverPort != 80) {
				url.append(":").append(serverPort);
			}
		} else if ("https".equals(scheme)) {
			if (serverPort != 443) {
				url.append(":").append(serverPort);
			}
		}
		if(servletPath != null) {
			url.append(contextPath).append(servletPath);
			if (pathInfo != null) {
				url.append(pathInfo);
			}
		} else {
			url.append(requestURI);
		}
		if (queryString != null) {
			url.append("?").append(queryString);
		}
		return url.toString();
	}
	
	/**
	 * 转发到指定页面<br/>
	 * /user/index.jsp
	 * 
	 * @param request
	 * @param response
	 * @param pageUrl 转发的页面
	 * @throws IOException
	 * @throws ServletException 
	 */
	public static void forward(HttpServletRequest request, HttpServletResponse response, String pageUrl) throws IOException, ServletException {
		if (!response.isCommitted()) {
			if (StringUtils.isNotBlank(pageUrl)) {
				// forward to page.
				request.getRequestDispatcher(pageUrl).forward(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Not Found");
			}
		}
	}

	/**
	 * 重定向到指定页面<br/>
	 * /user/index.jsp
	 * 
	 * @param request
	 * @param response
	 * @param pageUrl 重定向的页面
	 * @throws IOException 
	 */
	public static void redirect(HttpServletRequest request, HttpServletResponse response, String pageUrl) throws IOException {
		if (!response.isCommitted()) {
			if (StringUtils.isNotBlank(pageUrl)) {
				// redirect to page.
				response.sendRedirect(request.getContextPath() + pageUrl);
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Not Found");
			}
		}
	}
	
	/**
	 * 将result中的属性添加到请求
	 * 
	 * @param request
	 * @param result
	 * 
	 * @see Result
	 */
	public static void addResult2Request(HttpServletRequest request, Result result) {
		for (Entry<String, Object> entry : result.entrySet()) {
			request.setAttribute(entry.getKey(), entry.getValue());
		}
	}
	
	/**
	 * 将result中的属性合并到请求
	 * 
	 * @param request
	 * @param result
	 * 
	 * @see Result
	 */
	public static void mergeResult2Request(HttpServletRequest request, Result result) {
		for (Entry<String, Object> entry : result.entrySet()) {
			if (request.getAttribute(entry.getKey()) == null) {
				request.setAttribute(entry.getKey(), entry.getValue());
			}
		}
	}
}
