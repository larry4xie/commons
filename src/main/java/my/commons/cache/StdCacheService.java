package my.commons.cache;

/**
 * <p>
 * 标准cache服务提供者默认实现
 * </p>
 * 
 * @author xiegang
 * @since 2011-11-23
 *
 */
public class StdCacheService implements CacheService {
	/**
	 * Cache管理器
	 */
	private CacheManager cacheManager;
	
	public StdCacheService() {
		super();
	}
	
	public StdCacheService(CacheManager cacheManager) {
		super();
		this.setCacheManager(cacheManager);
	}
	
	/**
	 * 获取cache
	 * 
	 * @param cacheName
	 * @param autoCreate
	 * @return
	 * @throws CacheException
	 */
	private Cache getCache(String cacheName, boolean autoCreate) throws CacheException {
		return cacheManager.getCache(cacheName, autoCreate);	
	}
	
	@Override
	public Object get(String cacheName, Object key) throws CacheException {
		if(cacheName != null && key != null) {
			Cache cache = getCache(cacheName, false);
			if(null != cache) {
				return cache.get(key);
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String cacheName, Object key, Class<T> resultClass) throws CacheException {
		if(cacheName != null && key != null) {
			Cache cache = getCache(cacheName, false);
			if(null != cache) {
				Object obj = cache.get(key);
				return obj != null ? (T)obj : null;
			}
		}
		return null;
	}

	@Override
	public void set(String cacheName, Object key, Object value, boolean autoCreateCache) throws CacheException {
		if(cacheName != null && key != null && value != null) {
			Cache cache = getCache(cacheName, autoCreateCache);
			if(null != cache) {
				cache.put(key, value);
			}
		}
	}
	
	@Override
	public void set(String cacheName, Object key, Object value) throws CacheException {
		this.set(cacheName, key, value, false);
	}
	
	@Override
	public void update(String cacheName, Object key, Object value, boolean autoCreateCache) throws CacheException {
		if(cacheName != null && key != null && value != null) {
			Cache cache = getCache(cacheName, autoCreateCache);
			if(null != cache) {
				cache.update(key, value);
			}
		}
	}
	
	@Override
	public void update(String cacheName, Object key, Object value) throws CacheException {
		this.update(cacheName, key, value, false);
	}

	@Override
	public Object remove(String cacheName, Object key) throws CacheException {
		if(cacheName != null && key != null) {
			Cache cache = getCache(cacheName, false);
			if(null != cache) {
				return cache.remove(key);
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T remove(String cacheName, Object key, Class<T> resultClass) throws CacheException {
		if(cacheName != null && key != null) {
			Cache cache = getCache(cacheName, false);
			if(null != cache) {
				Object obj = cache.remove(key);
				return obj != null ? (T)obj : null;
			}
		}
		return null;
	}
	
	@Override
	public void clear(String cacheName) throws CacheException {
		if(cacheName != null) {
			Cache cache = getCache(cacheName, false);
			if(null != cache) {
				cache.clear();
			}
		}
	}

	@Override
	public CacheManager getCacheManager() {
		return cacheManager;
	}

	/**
	 * @param cacheManager the cacheManager to set
	 */
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
}
