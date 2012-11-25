package my.commons.cache.spring;

import my.commons.cache.Cache;

import org.springframework.cache.support.SimpleValueWrapper;


/**
 * <p>
 * Adaptor my Cache to spring Cache. <br/>
 * since spring 3.1
 * </p>
 * 
 * @author xiegang
 * @since 2012-11-19
 * @see org.springframework.cache.Cache
 *
 */
public class CacheAdaptor implements org.springframework.cache.Cache {
	private Cache cache;

	public CacheAdaptor(Cache cache) {
		super();
		this.cache = cache;
	}

	@Override
	public String getName() {
		return this.cache.getId();
	}

	@Override
	public Object getNativeCache() {
		return this.cache.getNativeCache();
	}

	@Override
	public ValueWrapper get(Object key) {
		Object value = this.cache.get(key);
		return (value != null ? new SimpleValueWrapper(value) : null);
	}

	@Override
	public void put(Object key, Object value) {
		this.cache.put(key, value);
	}

	@Override
	public void evict(Object key) {
		this.cache.remove(key);
	}

	@Override
	public void clear() {
		this.cache.clear();
	}
}
