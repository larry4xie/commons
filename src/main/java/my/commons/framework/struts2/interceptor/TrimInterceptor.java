package my.commons.framework.struts2.interceptor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.TextParseUtil;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * <p>
 * 去除参数空格的拦截器
 * </p>
 * 
 * <p>
 * 例子：
 * <pre>
 * &lt;interceptor-ref name="i18n" /&gt;
 * &lt;interceptor-ref name="chain" /&gt;
 * &lt;interceptor-ref name="trim" /&gt;
 * &lt;interceptor-ref name="params" /&gt;
 * 
 * or
 * 
 * &lt;interceptor-ref name="trim"&gt;
 * 	&lt;param name="excludeParams"&gt;nature_*&lt;/param&gt;
 * &lt;/interceptor-ref&gt;
 * </pre>
 * </p>
 * 
 * @author xiegang
 * @since 2011-12-27
 * 
 */
public class TrimInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 5713415703069590517L;
	private static final Logger LOG = LoggerFactory.getLogger(TrimInterceptor.class);
	
	/**
	 * 不去除空格的参数列表
	 */
	private Set<Pattern> excludeParams = Collections.emptySet();

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ac = invocation.getInvocationContext();
		Map<String, Object> params = ac.getParameters();
		if(params != null && params.size() > 0) {
			trimParams(params);
		}
		return invocation.invoke();
	}
	
	private void trimParams(Map<String, Object> params) {
		if(LOG.isDebugEnabled()) {
			LOG.debug("Before Trim Params = " + getParameterLogMap(params));
		}
		for(Entry<String, Object> entry : params.entrySet()) {
			if(!isExcluded(entry.getKey())) {
				Object value = entry.getValue();
				if(value instanceof String) {
					entry.setValue(StringUtils.trim((String)value));
				} else if(value instanceof String[]) {
					String[] values = (String[])value;
					for(int i = values.length - 1; i >=0; i--) {
						values[i] = StringUtils.trim(values[i]);
					}
					entry.setValue(values);
				}
			}
		}
		if(LOG.isDebugEnabled()) {
			LOG.debug("After Trim Params = " + getParameterLogMap(params));
		}
	}
	
	protected boolean isExcluded(String paramName) {
		if (!this.excludeParams.isEmpty()) {
			for (Pattern pattern : excludeParams) {
				Matcher matcher = pattern.matcher(paramName);
				if (matcher.matches()) {
					return true;
				}
			}
		}
		return false;
	}
	
	private String getParameterLogMap(Map<String, Object> parameters) {
		if (parameters == null) {
			return "NONE";
		}

		StringBuilder logEntry = new StringBuilder();
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			logEntry.append(String.valueOf(entry.getKey()));
			logEntry.append(" = ");
			if (entry.getValue() instanceof Object[]) {
				Object[] valueArray = (Object[]) entry.getValue();
				logEntry.append("[");
				if (valueArray.length > 0) {
					for (int indexA = 0; indexA < (valueArray.length - 1); indexA++) {
						Object valueAtIndex = valueArray[indexA];
						logEntry.append(String.valueOf(valueAtIndex));
						logEntry.append(", ");
					}
					logEntry.append(String
							.valueOf(valueArray[valueArray.length - 1]));
				}
				logEntry.append("] ");
			} else {
				logEntry.append(String.valueOf(entry.getValue()));
			}
		}

		return logEntry.toString();
	}
	
	/**
	 * Sets a comma-delimited list of regular expressions to match parameters
	 * that should be removed from the parameter map.
	 * 
	 * @param commaDelim
	 *            A comma-delimited list of regular expressions
	 */
	public void setExcludeParams(String commaDelim) {
		Collection<String> excludePatterns = asCollection(commaDelim);
		if (excludePatterns != null) {
			excludeParams = new HashSet<Pattern>();
			for (String pattern : excludePatterns) {
				excludeParams.add(Pattern.compile(pattern));
			}
		}
	}

	/**
	 * Return a collection from the comma delimited String.
	 * 
	 * @param commaDelim
	 *            the comma delimited String.
	 * @return A collection from the comma delimited String. Returns
	 *         <tt>null</tt> if the string is empty.
	 */
	private Collection<String> asCollection(String commaDelim) {
		if (commaDelim == null || commaDelim.trim().length() == 0) {
			return null;
		}
		return TextParseUtil.commaDelimitedStringToSet(commaDelim);
	}
}
