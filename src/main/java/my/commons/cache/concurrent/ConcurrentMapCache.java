package my.commons.cache.concurrent;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import my.commons.cache.Cache;
import my.commons.cache.CacheException;


/**
 * Simple {@link Cache} implementation based on the core JDK {@code java.util.concurrent} package.
 * 
 * <p><b>Note:</b> As {@link ConcurrentHashMap} (the default implementation used)
 * does not allow for {@code null} values to be stored, this class will replace
 * them with a predefined internal object. This behavior can be changed through the
 * {@link #ConcurrentMapCache(String, ConcurrentMap, boolean)} constructor.
 * </p>
 * 
 * @author xiegang
 * @since 2012-11-19
 *
 */
public class ConcurrentMapCache implements Cache {
	private static final Object NULL_HOLDER = new NullHolder();
	
	/** Cache id(name) */
	private final String id;
	
	/** 存储 */
	private final ConcurrentMap<Object, Object> store;

	/** 是否进行null--NullHolder */
	private final boolean allowNullValues;
	
	/**
	 * Create a new ConcurrentMapCache with the specified name and the
	 * given internal ConcurrentMap to use.
	 * @param id the name of the cache
	 * @param store the ConcurrentMap to use as an internal store
	 * @param allowNullValues whether to allow <code>null</code> values
	 * (adapting them to an internal null holder value)
	 */
	public ConcurrentMapCache(String id, ConcurrentMap<Object, Object> store, boolean allowNullValues) {
		this.id = id;
		this.store = store;
		this.allowNullValues = allowNullValues;
	}
	
	/**
	 * Create a new ConcurrentMapCache with the specified name.
	 * @param id the name of the cache
	 */
	public ConcurrentMapCache(String id) {
		this(id, new ConcurrentHashMap<Object, Object>(), true);
	}

	/**
	 * Create a new ConcurrentMapCache with the specified name.
	 * @param id the name of the cache
	 */
	public ConcurrentMapCache(String id, boolean allowNullValues) {
		this(id, new ConcurrentHashMap<Object, Object>(), allowNullValues);
	}


	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public int getSize() throws CacheException {
		return this.store.size();
	}

	@Override
	public Object get(Object key) throws CacheException {
		if (key == null)
			return null;
		return fromStoreValue(this.store.get(key));
	}

	@Override
	public void put(Object key, Object value) throws CacheException {
		this.store.put(key, toStoreValue(value));
	}

	@Override
	public void update(Object key, Object value) throws CacheException {
		put(key, value);
	}

	@Override
	public Collection<?> keys() throws CacheException {
		return this.store.keySet();
	}

	@Override
	public Object remove(Object key) throws CacheException {
		return this.store.remove(key);
	}

	@Override
	public void clear() throws CacheException {
		this.store.clear();
	}

	@Override
	public ConcurrentMap<Object, Object> getNativeCache() {
		return this.store;
	}
	
	/**
	 * Convert the given value from the internal store to a user value
	 * returned from the get method (adapting <code>null</code>).
	 * @param userValue the store value
	 * @return the value to return to the user
	 */
	protected Object fromStoreValue(Object storeValue) {
		if (this.allowNullValues && storeValue == NULL_HOLDER) {
			return null;
		}
		return storeValue;
	}

	/**
	 * Convert the given user value, as passed into the put method,
	 * to a value in the internal store (adapting <code>null</code>).
	 * @param userValue the given user value
	 * @return the value to store
	 */
	protected Object toStoreValue(Object userValue) {
		if (this.allowNullValues && userValue == null) {
			return NULL_HOLDER;
		}
		return userValue;
	}

	
	/**
	 * 替换null值，解决null不能序列化
	 * @author xiegang
	 * @since 2012-11-19
	 *
	 */
	private static class NullHolder implements Serializable {
	}
}
