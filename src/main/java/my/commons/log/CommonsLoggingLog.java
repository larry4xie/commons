package my.commons.log;

import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * 系统日志基于commons-logging平台的实现<br/>
 * <br/>
 * commons-logging平台的实现会不支持格式化的消息
 * </p>
 * 
 * @author xiegang
 * @since 2011-11-01
 * @see Log
 * 
 */
public class CommonsLoggingLog implements Log {
	private org.apache.commons.logging.Log logger;

	public CommonsLoggingLog(String name) {
		logger = LogFactory.getLog(name);
	}

	public CommonsLoggingLog(Class<?> clazz) {
		logger = LogFactory.getLog(clazz);
	}

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	public void trace(String message, Object... args) {
		logger.trace(message);
	}

	public void trace(String message, Throwable t) {
		logger.trace(message, t);
	}

	public void debug(String message, Object... args) {
		logger.debug(message);

	}

	public void debug(String message, Throwable t) {
		logger.debug(message, t);
	}

	public void info(String message, Object... args) {
		logger.info(message);
	}

	public void info(String message, Throwable t) {
		logger.info(message, t);
	}

	public void warn(String message, Object... args) {
		logger.warn(message);
	}

	public void warn(String message, Throwable t) {
		logger.warn(message, t);
	}

	public void error(String message, Object... args) {
		logger.error(message);
	}

	public void error(String message, Throwable t) {
		logger.error(message, t);
	}
}
