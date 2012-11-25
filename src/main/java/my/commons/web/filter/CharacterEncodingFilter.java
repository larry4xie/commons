package my.commons.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 字符集过滤器<br/>
 * 参数：<br/>
 * encoding、forceEncoding
 * </p>
 * @author xiegang
 * @since 2012-11-12
 *
 */
public class CharacterEncodingFilter extends OnceRequestFilter {
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final String FILTER_PARAM_ENCODING = "encoding";
	private static final String FILTER_PARAM_FORCEENCODING = "forceEncoding";
	
	private String encoding;
	private boolean forceEncoding = false;
	
	@Override
	protected void doInit(FilterConfig config) throws ServletException {
		this.encoding = config.getInitParameter(FILTER_PARAM_ENCODING);
		if (this.encoding == null) {
			this.encoding = DEFAULT_ENCODING;
		}
		
		String force = config.getInitParameter(FILTER_PARAM_FORCEENCODING);
		if ("true".equalsIgnoreCase(force)) {
			forceEncoding = true;
		}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		if (this.encoding != null && (this.forceEncoding || request.getCharacterEncoding() == null)) {
			request.setCharacterEncoding(this.encoding);
			if (this.forceEncoding || response.getCharacterEncoding() == null) {
				response.setCharacterEncoding(this.encoding);
			}
		}
		chain.doFilter(request, response);
	}
}
