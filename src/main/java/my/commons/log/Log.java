package my.commons.log;

/**
 * <p>
 * 系统公用日志接口<br/>
 * 主要作用：
 * <ol>
 * <li>用于和具体提供日志功能的组件解耦</li>
 * <li>用于使日志功能可扩展</li>
 * </ol>
 * 具体日志实现可以是slf4j平台或者commons-logging平台
 * </p>
 * 
 * <p>
 * 有5个日志级别可以使用:
 * <ol>
 * <li>trace (the least serious)</li>
 * <li>debug</li>
 * <li>info</li>
 * <li>warn</li>
 * <li>error</li>
 * </ol>
 * </p>
 * 
 * <p>
 * 例子:
 * <pre>
 * if (LOG.isDebugEnabled()) {
 * 	LOG.debug("this is a {0} level log message by {1}", "debug", "xiegang");
 * }
 * </pre>
 * </P>
 * 
 * @author xiegang
 * @since 2011-11-01
 * 
 */
public interface Log {
	/**
	 * 判断日志DEBUG级别是否可用
	 * 
	 * @return
	 */
	public boolean isDebugEnabled();

	/**
	 * 判断日志ERROR级别是否可用
	 * 
	 * @return
	 */
	public boolean isErrorEnabled();

	/**
	 * 判断日志INFO级别是否可用
	 * 
	 * @return
	 */
	public boolean isInfoEnabled();

	/**
	 * 判断日志TRACE级别是否可用
	 * 
	 * @return
	 */
	public boolean isTraceEnabled();
	
	/**
	 * 判断日志WARN级别是否可用
	 * 
	 * @return
	 */
	public boolean isWarnEnabled();

	/**
	 * 使用格式化的字符串与参数(可以为空),记录一个TRACE级别的日志
	 * 
	 * @param message the format string
	 * @param args an array of arguments
	 */
	public void trace(String message, Object... args);

	/**
	 * 使用伴随异常(Throwable)信息的字符串,记录一个TRACE级别的日志
	 * @param message the message accompanying the exception
	 * @param t the exception (throwable) to log
	 */
	public void trace(String message, Throwable t);

	/**
	 * 使用格式化的字符串与参数(可以为空),记录一个DEBUG级别的日志
	 * 
	 * @param message the format string
	 * @param args an array of arguments
	 */
	public void debug(String message, Object... args);

	/**
	 * 使用伴随异常(Throwable)信息的字符串,记录一个DEBUG级别的日志
	 * @param message the message accompanying the exception
	 * @param t the exception (throwable) to log
	 */
	public void debug(String message, Throwable t);

	/**
	 * 使用格式化的字符串与参数(可以为空),记录一个INFO级别的日志
	 * 
	 * @param message the format string
	 * @param args an array of arguments
	 */
	public void info(String message, Object... args);

	/**
	 * 使用伴随异常(Throwable)信息的字符串,记录一个INFO级别的日志
	 * @param message the message accompanying the exception
	 * @param t the exception (throwable) to log
	 */
	public void info(String message, Throwable t);

	/**
	 * 使用格式化的字符串与参数(可以为空),记录一个WARN级别的日志
	 * 
	 * @param message the format string
	 * @param args an array of arguments
	 */
	public void warn(String message, Object... args);

	/**
	 * 使用伴随异常(Throwable)信息的字符串,记录一个WARN级别的日志
	 * @param message the message accompanying the exception
	 * @param t the exception (throwable) to log
	 */
	public void warn(String message, Throwable t);

	/**
	 * 使用格式化的字符串与参数(可以为空),记录一个ERROR级别的日志
	 * 
	 * @param message the format string
	 * @param args an array of arguments
	 */
	public void error(String message, Object... args);

	/**
	 * 使用伴随异常(Throwable)信息的字符串,记录一个ERROR级别的日志
	 * @param message the message accompanying the exception
	 * @param t the exception (throwable) to log
	 */
	public void error(String message, Throwable t);
}
