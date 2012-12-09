package my.commons.cache.spring;

import java.util.Collection;

import my.commons.Assert;
import my.commons.cache.Cache;
import my.commons.cache.CacheManager;

import org.springframework.beans.factory.InitializingBean;


/**
 * <p>
 * Adaptor my CacheManager to Spring CacheManager
 * since spring 3.1
 * </p>
 * 
 * @author xiegang
 * @since 2012-11-19
 * @see org.springframework.cache.CacheManager
 *
 */
public class CacheManagerAdaptor implements org.springframework.cache.CacheManager, InitializingBean {
	private CacheManager cacheManager;

	public CacheManagerAdaptor(CacheManager cacheManager) {
		super();
		this.cacheManager = cacheManager;
	}

	@Override
	public org.springframework.cache.Cache getCache(String name) {
		Cache cache = this.cacheManager.getCache(name);
		return null != cache? new CacheAdaptor(cache): null;
	}

	@Override
	public Collection<String> getCacheNames() {
		return this.cacheManager.getCacheNames();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.cacheManager, "Property 'cacheManager' are required");
	}
}
