package my.commons.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 一次请求只过滤一次的过滤器<br/>
 * just supports HTTP requests
 * </p>

 * @author xiegang
 * @since 2012-11-12
 *
 */
public abstract class OnceRequestFilter implements Filter {
	/** Servlet FilterConfig */
	protected FilterConfig filterConfig;
	
	public static final String ALREADY_FILTERED_SUFFIX = ".FILTERED";
	
	/**
	 * 单次过滤器初始化
	 * @param filterConfig
	 * @throws ServletException
	 */
	protected abstract void doInit(FilterConfig filterConfig) throws ServletException ;
	
	/**
	 * 单次过滤器内容
	 * @param request
	 * @param response
	 * @param chain
	 * @throws ServletException
	 * @throws IOException
	 */
	protected abstract void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		doInit(this.filterConfig);
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
			throw new ServletException("just supports HTTP requests");
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String alreadyFilteredAttributeName = getAlreadyFilteredAttributeName();
		if (request.getAttribute(alreadyFilteredAttributeName) != null) {
			// Proceed without invoking this filter...
			chain.doFilter(request, response);
		} else {
			// Do invoke this filter...
			request.setAttribute(alreadyFilteredAttributeName, Boolean.TRUE);
			try {
				doFilterInternal(httpRequest, httpResponse, chain);
			} finally {
				// Remove the "already filtered" request attribute for this request.
				request.removeAttribute(alreadyFilteredAttributeName);
			}
		}
	}
	
	@Override
	public void destroy() {
		// do nothing
	}

	/**
	 * filter.FILTERED
	 * @return filtername.FILTERED
	 */
	private String getAlreadyFilteredAttributeName() { 
		String name = this.filterConfig != null ? this.filterConfig.getFilterName() : getClass().getName();
		return name + ALREADY_FILTERED_SUFFIX;
	}
}