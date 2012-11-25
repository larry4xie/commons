package my.commons.cache.ehcache;

import java.util.Collection;

import my.commons.cache.Cache;
import my.commons.cache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * <p>
 * 缓存接口的Ehcache实现
 * </p>
 * 
 * @author xiegang
 * @since 2011-11-23
 */
public class EhcacheCache implements Cache {
	/**
	 * ehcache中的cache接口
	 */
	private Ehcache cache;

	/**
	 * 使用Ehcache实例初始化
	 * 
	 * @param cache
	 *            Ehcache中的cache实现类的实例
	 */
	public EhcacheCache(Ehcache cache) {
		this.cache = cache;
	}
	
	public String getId() {
		return this.cache.getName();
	}

	public int getSize() throws CacheException {
		try {
			return this.cache.getSize();
		} catch (IllegalStateException e) {
			throw new CacheException(e);
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}
	}

	public Collection<?> keys() throws CacheException {
		try {
			return this.cache.getKeys();
		} catch (IllegalStateException e) {
			throw new CacheException(e);
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}
	}

	public Object get(Object key) throws CacheException {
		try {
			if (key == null)
				return null;
			else {
				Element element = cache.get(key);
				if (element != null)
					return element.getObjectValue();
			}
			return null;
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		} catch (IllegalStateException e) {
			throw new CacheException(e);
		}
	}

	/**
	 * update an item in the cache, 使用{@link EhcacheCache#put(Object, Object)}
	 * 
	 * @param key
	 *            对象key
	 * @param value
	 *            对象值
	 * @throws CacheException
	 *             if the {@link CacheManager} is shutdown or another
	 *             {@link Exception} occurs.
	 */
	public void update(Object key, Object value) throws CacheException {
		put(key, value);
	}

	/**
	 * Add an item to the cache
	 * 
	 * @param key
	 *            对象key
	 * @param value
	 *            对象值
	 * @throws CacheException
	 *             if the {@link CacheManager} is shutdown or another
	 *             {@link Exception} occurs.
	 */
	public void put(Object key, Object value) throws CacheException {
		try {
			Element element = new Element(key, value);
			cache.put(element);
		} catch (IllegalArgumentException e) {
			throw new CacheException(e);
		} catch (IllegalStateException e) {
			throw new CacheException(e);
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}

	}

	public Object remove(Object key) throws CacheException {
		try {
			Object obj = cache.get(key);
			if(null != obj) {
				cache.remove(key);
			}
			return obj;
		} catch (IllegalStateException e) {
			throw new CacheException(e);
		}
	}

	/**
	 * Remove all elements in the cache
	 * 
	 * @throws CacheException
	 */
	public void clear() throws CacheException {
		try {
			cache.removeAll();
		} catch (IllegalStateException e) {
			throw new CacheException(e);
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}
	}

	@Override
	public Object getNativeCache() {
		return this.cache;
	}
}