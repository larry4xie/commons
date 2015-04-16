package my.commons.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.lang3.StringUtils;

/**
 * wrapper for jsp template
 * @author xieg
 * @since 2014-11-18
 */
public class JspWrapper extends HttpServletResponseWrapper {
	private final StringWriter writer = new StringWriter();
	
	public static JspWrapper getInstance(HttpServletResponse response) {
		return new JspWrapper(response);
	}

	public JspWrapper(HttpServletResponse response) {
		super(response);
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(writer);
	}

	@Override
	public String toString() {
		return StringUtils.strip(writer.toString(), " \t\r\n");
	}
}
