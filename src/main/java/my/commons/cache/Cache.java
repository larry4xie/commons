package my.commons.cache;

import java.util.Collection;

/**
 * <p>
 * 缓存接口，一个缓存包含了若干Element<br/>
 * 比如存放user的缓存可以包含多个user的实例
 * </p>
 * 
 * @author xiegang
 * @since 2011-11-23
 */
public interface Cache {
	/**
	 * 获取cache的唯一标识id
	 * 
	 * @return
	 */
	public String getId();

	/**
	 * 获取cache中的元素个数
	 * 
	 * @return
	 * @throws CacheException
	 */
	public int getSize() throws CacheException;

	/**
	 * 通过key获取cache中对应object
	 * 
	 * @param key 要获取的对象的key
	 * @return 对应key的缓存中对象,不存在时返回null
	 * @throws CacheException
	 */
	public Object get(Object key) throws CacheException;
	
	/**
	 * Add an item to the cache
	 * 
	 * @param key 对象key
	 * @param value 对象值
	 * @throws CacheException
	 */
	public void put(Object key, Object value) throws CacheException;
	
	/**
	 * update an item in the cache
	 * 
	 * @param key 对象key
	 * @param value 对象值
	 * @throws CacheException
	 */
	public void update(Object key, Object value) throws CacheException;

	/**
	 * 获取所有的key的集合
	 * 
	 * @return key的集合
	 * @throws CacheException
	 */
	public Collection<?> keys() throws CacheException ;
	
	/**
	 * Remove an item from the cache
	 * 
	 * @param key
	 * @return 对应的对象,不存在时返回null
	 * @throws CacheException
	 */
	public Object remove(Object key) throws CacheException;
	
	/**
	 * Clear the cache, all keys
	 */
	public void clear() throws CacheException;
	
	/**
	 * Return the the underlying native cache provider.
	 */
	public Object getNativeCache();
}
