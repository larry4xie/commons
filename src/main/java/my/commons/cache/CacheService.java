package my.commons.cache;

/**
 * <p>
 * cache服务<br/>
 * 使用外观屏蔽所有cache和cacheManager的细节
 * </p>
 * 
 * @author xiegang
 * @since 2011-11-23
 *
 */
public interface CacheService {
	/**
	 * 获取指定cache中指定key缓存的对象
	 * 
	 * @param cacheName cache名称
	 * @param key 缓存对象的key
	 * @return 不存在返回null
	 * @throws CacheException
	 */
	public Object get(String cacheName, Object key) throws CacheException;
	
	/**
	 * 获取指定cache中指定key缓存的对象
	 * 
	 * @param <T>
	 * @param cacheName cache名称
	 * @param key 缓存对象的key
	 * @param resultClass 返回的缓存对象的类型
	 * @return 不存在返回null
	 * @throws CacheException
	 */
	public <T> T get(String cacheName, Object key, Class<T> resultClass) throws CacheException;
	
	/**
	 * 向指定cache中存入指定key的缓存对象
	 * 
	 * @param cacheName
	 * @param key
	 * @param value
	 * @param autoCreateCache 指定cache不存在时是否创建
	 * @throws CacheException
	 */
	public void set(String cacheName, Object key, Object value, boolean autoCreateCache) throws CacheException;
	
	/**
	 * 向指定cache中存入指定key的缓存对象，autoCreateCache=false
	 * 
	 * @param cacheName
	 * @param key
	 * @param value
	 * @throws CacheException
	 */
	public void set(String cacheName, Object key, Object value) throws CacheException;
	
	/**
	 * update指定cache中指定key的缓存对象
	 * 
	 * @param cacheName
	 * @param key
	 * @param value
	 * @param autoCreateCache 指定cache不存在时是否创建
	 * @throws CacheException
	 */
	public void update(String cacheName, Object key, Object value, boolean autoCreateCache) throws CacheException;
	
	/**
	 * update指定cache中指定key的缓存对象，autoCreateCache=false
	 * 
	 * @param cacheName
	 * @param key
	 * @param value
	 * @throws CacheException
	 */
	public void update(String cacheName, Object key, Object value) throws CacheException;
	
	/**
	 * 删除cache中指定对象
	 * 
	 * @param cacheName
	 * @param key
	 * @return 返回删除的对象，cache不存在或者对象不存在返回null
	 * @throws CacheException
	 */
	public Object remove(String cacheName, Object key) throws CacheException;
	
	/**
	 * 删除cache中指定对象
	 * 
	 * @param cacheName
	 * @param key
	 * @param resultClass 返回的缓存对象的类型
	 * @return 返回删除的对象，cache不存在或者对象不存在返回null
	 * @throws CacheException
	 */
	public <T> T remove(String cacheName, Object key, Class<T> resultClass) throws CacheException;
	
	/**
	 * Clear the cache, all keys
	 */
	public void clear(String cacheName) throws CacheException;
	
	/**
	 * 获取提供服务的Cache管理器<br/>
	 * 可以通过这个管理器更全面的操作cache，但是不建议，因为可以使用这里提供的更简便的方法
	 * 
	 * @return
	 */
	public CacheManager getCacheManager();
}
