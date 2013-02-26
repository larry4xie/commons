package my.commons.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>
 * 数据路由上下文Holder<br/>
 * base on ThreadLocal<br/>
 * {@link DataRoutingContextHolder#setContext(Object)}<br/>
 * {@link DataRoutingContextHolder#setUniqueContext(Object)} 
 * </p>
 * 
 * @author xiegang
 * @since 2012-11-4
 * 
 */
public class DataRoutingContextHolder {
	private static final Logger LOG = LoggerFactory.getLogger(DataRoutingContextHolder.class);
	
	private static final ThreadLocal<Object> contextHolder = new ThreadLocal<Object>();

	/**
	 * 设置当前线程的数据路由标识上下文<br/>
	 * 可自由切换
	 * 
	 * @param <T>
	 * @param context
	 */
	public static <T> void setContext(T context) {
		contextHolder.set(context);
		if(LOG.isDebugEnabled()) {
			LOG.debug("Data Routing >> " + context);
		}
	}

	/**
	 * 设置当前线程的数据路由标识上下文<br/>
	 * 如果当前上下文已经设置了上下文，则该方法什么也不做
	 * 
	 * @param <T>
	 * @param context
	 */
	public static <T> void setUniqueContext(T context) {
		if(contextHolder.get() == null) {
			DataRoutingContextHolder.setContext(context);
		}
	}

	/**
	 * 获取当前线程的数据路由标识上下文
	 * 
	 * @param <T>
	 * @param context
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getContext() {
		return (T) contextHolder.get();
	}

	/**
	 * 清空当前线程的数据路由标识上下文
	 * 
	 * @param <T>
	 * @param context
	 */
	public static void clearContext() {
		contextHolder.remove();
	}
}