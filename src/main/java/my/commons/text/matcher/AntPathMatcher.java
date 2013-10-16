package my.commons.text.matcher;

import java.util.regex.Pattern;

/**
 * <p>
 * AntPathMatcher implementation for Ant-style path patterns. Examples are provided below.
 * </p>
 * The mapping matches URLs using the following rules:<br>
 * <ul>
 * <li>? matches one character</li>
 * <li>* matches zero or more characters</li>
 * <li>** matches zero or more 'directories' in a path</li>
 * </ul>
 * <p>
 * Some examples:<br>
 * <ul>
 * <li><code>com/t?st.jsp</code> - matches <code>com/test.jsp</code> but also
 * <code>com/tast.jsp</code> or <code>com/txst.jsp</code></li>
 * <li><code>com/*.jsp</code> - matches all <code>.jsp</code> files in the <code>com</code>
 * directory</li>
 * <li><code>com/&#42;&#42;/test.jsp</code> - matches all <code>test.jsp</code> files underneath the
 * <code>com</code> path</li>
 * <li><code>org/beangle/&#42;&#42;/*.jsp</code> - matches all <code>.jsp</code> files underneath
 * the <code>org/beangle</code> path</li>
 * <li><code>org/&#42;&#42;/servlet/bla.jsp</code> - matches
 * <code>org/beangle/servlet/bla.jsp</code> but also
 * <code>org/beangle/testing/servlet/bla.jsp</code> and <code>org/servlet/bla.jsp</code></li>
 * </ul>
 * 
 * @author xiegang
 * @since 2013-9-4
 *
 */
public class AntPathMatcher {
	private static final boolean DEFAULT_IGNORECASE = false; // 默认不忽略大小写
	
	private String antPattern; // ant style pattern

	private boolean ignoreCase = DEFAULT_IGNORECASE; // 是否忽略大小写

	private Pattern pattern;
	
	
	/**
	 * 创建指定ant风格的匹配器，使用默认是否忽略大小写策略（false）
	 * @param antPattern 指定ant风格字符串
	 */
	public AntPathMatcher(String antPattern) {
		this(antPattern, DEFAULT_IGNORECASE);
	}

	/**
	 * 创建指定ant风格的匹配器
	 * 
	 * @param antPattern 指定ant风格字符串
	 * @param ignoreCase 匹配是否忽略大小写
	 */
	public AntPathMatcher(String antPattern, boolean ignoreCase) {
		super();
		this.antPattern = antPattern;
		this.ignoreCase = ignoreCase;
		
		// convert ant to regex
		StringBuilder sb = new StringBuilder();
		int length = antPattern.length();
		for (int i = 0; i < length; i++) {
			char c = antPattern.charAt(i);
			String substr = String.valueOf(c);
			
			// replace
			if (c == '.') substr = "\\.";
			else if (c == '?') substr = ".";
			
			// process * **/ **
			else if (c == '*') {
				if (i + 1 < length) {
					char next1 = antPattern.charAt(i + 1);
					if (next1 == '*') {
						i += 1;
						char next2 = '\n';
						if (i + 1 < length)
							next2 = antPattern.charAt(i + 1);
						if (next2 == '/') {
							i += 1;
							substr = "(.*/)*";
						} else {
							substr = "(.*)";
						}
					} else
						substr = "([^/]*?)";
				} else {
					substr = "([^/]*?)";
				}
			}
			sb.append(substr);
		}
		pattern = this.ignoreCase ? Pattern.compile(sb.toString(), Pattern.CASE_INSENSITIVE) : Pattern.compile(sb.toString());;
	}

	/**
	 * 使用ant风格匹配指定文本
	 * 
	 * @param text 待匹配的文本
	 * @return
	 */
	public boolean match(String text) {
		if ("/**".equals(text) || "**".equals(text)) {
			return true;
		}
		return pattern.matcher(text).matches();
	}
	
	/**
	 * 匹配是否忽略大小写
	 * @return
	 */
	public boolean isIgnoreCase() {
		return ignoreCase;
	}
	
	/**
	 * 使用指定ant风格匹配指定文本
	 * @param antPattern 指定ant风格字符串
	 * @param text 待匹配的文本
	 * @return
	 */
	public static boolean match(String antPattern, String text) {
		return new AntPathMatcher(antPattern).match(text);
	}

	/**
	 * get ant style pattern
	 * @return ant style pattern
	 */
	public String getAntPattern() {
		return antPattern;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((antPattern == null) ? 0 : antPattern.hashCode());
		result = prime * result + (ignoreCase ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AntPathMatcher other = (AntPathMatcher) obj;
		if (antPattern == null) {
			if (other.antPattern != null)
				return false;
		} else if (!antPattern.equals(other.antPattern))
			return false;
		if (ignoreCase != other.ignoreCase)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AntPathMatcher [antPattern=" + antPattern + ", ignoreCase=" + ignoreCase + "]";
	}
}
