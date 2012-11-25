package my.commons.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 系统日志基于slf4j平台的实现<br/>
 * </p>
 * 
 * @author xiegang
 * @since 2011-11-01
 * @see Log
 * 
 */
public class SLF4JLog implements Log {
	private Logger logger;

	public SLF4JLog(String name) {
		logger = LoggerFactory.getLogger(name);
	}

	public SLF4JLog(Class<?> clazz) {
		logger = LoggerFactory.getLogger(clazz);
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
		logger.trace(message, args);
	}

	public void trace(String message, Throwable t) {
		logger.trace(message, t);
	}

	public void debug(String message, Object... args) {
		logger.debug(message, args);
	}

	public void debug(String message, Throwable t) {
		logger.debug(message, t);
	}

	public void info(String message, Object... args) {
		logger.info(message, args);
	}

	public void info(String message, Throwable t) {
		logger.info(message, t);
	}

	public void warn(String message, Object... args) {
		logger.warn(message, args);
	}

	public void warn(String message, Throwable t) {
		logger.warn(message, t);
	}

	public void error(String message, Object... args) {
		logger.error(message, args);
	}

	public void error(String message, Throwable t) {
		logger.error(message, t);
	}
}
