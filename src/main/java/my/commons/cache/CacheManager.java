package my.commons.cache;

import java.util.Collection;

/**
 * <p>
 * 缓存Cache的管理器<br/>
 * 用于集中管理多个缓存
 * <p>
 * 
 * @author xiegang
 * @since 2011-11-23
 */
public interface CacheManager {
	/**
	 * 是否存在指定cache
	 * 
	 * @param cacheId
	 * @return
	 * @throws CacheException
	 */
	public boolean hasCache(String cacheId) throws CacheException;
	
	/**
	 * 通过cacheName获取Cache {@link #getCache(String, false)}
	 * 
	 * @param cacheId
	 * @return 不存在时返回null
	 * @throws CacheException
	 */
	public Cache getCache(String cacheId) throws CacheException;
	
	/**
	 * 增加Cache {@link #getCache(String, true)}
	 * 
	 * @param cacheId
	 * @return
	 * @throws CacheException
	 */
	public Cache addCache(String cacheId) throws CacheException;
	
	/**
	 * 删除Cache
	 * 
	 * @param cacheId
	 * @return 删除的cache，不存在时返回null
	 * @throws CacheException
	 */
	public Cache removeCache(String cacheId) throws CacheException;
	
	/**
	 * 通过cacheId获取Cache
	 * 
	 * @param cacheId
	 * @param autoCreate 不存在时是否创建
	 * @return
	 * @throws CacheException
	 */
	public Cache getCache(String cacheId, boolean autoCreate) throws CacheException;

	public Collection<String> getCacheNames();
	/**
	 * 启动Manager
	 * 
	 * @throws CacheException
	 */
	public void start() throws CacheException;

	/**
	 * 关闭Manager
	 */
	public void stop();
	
	/**
	 * Return the the underlying native CacheManager provider.
	 */
	public Object getNativeCacheManager();
}
