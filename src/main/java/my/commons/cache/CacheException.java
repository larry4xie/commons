package my.commons.cache;

/**
 * <p>
 * 缓存异常,用于屏蔽具体提供商带来不同异常
 * </p>
 * 
 * @author xiegang
 * @since 2011-11-23
 */
public class CacheException extends RuntimeException {
	private static final long serialVersionUID = 8574734759755587935L;

	public CacheException(String message, Throwable cause) {
		super(message, cause);
	}

	public CacheException(String message) {
		super(message);
	}

	public CacheException(Throwable cause) {
		super(cause);
	}
}
