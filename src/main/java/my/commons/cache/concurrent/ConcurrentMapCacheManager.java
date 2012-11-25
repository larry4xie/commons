package my.commons.cache.concurrent;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import my.commons.cache.Cache;
import my.commons.cache.CacheException;
import my.commons.cache.CacheManager;


/**
 * <p>
 * base ConcurrentMapCache<br/>
 * 可以使用构造函数初始化一部分cache
 * </p>
 * 
 * @author xiegang
 * @since 2012-11-19
 * @see ConcurrentMapCache
 *
 */
public class ConcurrentMapCacheManager implements CacheManager {
	private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();

	public ConcurrentMapCacheManager() {
	}
	
	public ConcurrentMapCacheManager(Collection<String> cacheNames) {
		if (cacheNames != null) {
			for (String name : cacheNames) {
				this.cacheMap.put(name, new ConcurrentMapCache(name));
			}
		}
	}
	public ConcurrentMapCacheManager(String... cacheNames) {
		this(Arrays.asList(cacheNames));
	}
	
	@Override
	public boolean hasCache(String cacheId) throws CacheException {
		return cacheMap.containsKey(cacheId);
	}
	@Override
	public Cache getCache(String cacheId) throws CacheException {
		return cacheMap.get(cacheId);
	}

	@Override
	public synchronized Cache addCache(String cacheId) throws CacheException {
		Cache cache = this.cacheMap.get(cacheId);
		if(null == cache) {
			cache = new ConcurrentMapCache(cacheId);
			this.cacheMap.put(cacheId, cache);
		}
		return cache;
	}

	@Override
	public synchronized Cache removeCache(String cacheId) throws CacheException {
		return this.cacheMap.remove(cacheId);
	}

	@Override
	public Cache getCache(String cacheId, boolean autoCreate) throws CacheException {
		Cache cache = getCache(cacheId);
		if(null == cache) {
			cache = addCache(cacheId);
		}
		return cache;
	}

	@Override
	public Collection<String> getCacheNames() {
		return Collections.unmodifiableSet(this.cacheMap.keySet());
	}

	@Override
	public synchronized void start() throws CacheException {
	}

	@Override
	public synchronized void stop() {
		this.cacheMap.clear();
	}

	@Override
	public ConcurrentMap<String, Cache> getNativeCacheManager() {
		return this.cacheMap;
	}
}
