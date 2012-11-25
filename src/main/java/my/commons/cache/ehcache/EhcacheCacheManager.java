package my.commons.cache.ehcache;

import java.util.Arrays;
import java.util.Collection;

import my.commons.cache.Cache;
import my.commons.cache.CacheException;
import my.commons.cache.CacheManager;
import net.sf.ehcache.ObjectExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <p>
 * 缓存Cache的管理器Ehcache实现<br/>
 * stop以后可以重新start使用
 * </p>
 * 
 * @author xiegang
 * @since 2011-11-23
 */
public class EhcacheCacheManager implements CacheManager {
	private static final Logger logger = LoggerFactory.getLogger(EhcacheCacheManager.class);
	
	/**
	 * Ehcache配置文件的位置
	 */
	private String configurationFileName;

	private net.sf.ehcache.CacheManager manager;
	
	/**
	 * 构造方法<br/>
	 * {@link #EhcacheCacheManager(boolean)} 参数为false
	 */
	public EhcacheCacheManager() {
		this(false);
	}
	
	/**
	 * 构造方法<br/>
	 * {@link #EhcacheCacheManager(boolean)} 参数为false
	 * 
	 * @param configurationFileName Ehcache配置文件的位置
	 */
	public EhcacheCacheManager(String configurationFileName) {
		this(false);
		this.configurationFileName = configurationFileName;
	}

	/**
	 * 构造方法
	 * 
	 * @param autoStart 创建后是否调用{@link #start()}
	 */
	public EhcacheCacheManager(boolean autoStart) {
		if(autoStart) {
			start();
		}
	}
	
	/**
	 * 构造方法
	 * 
	 * @param configurationFileName Ehcache配置文件的位置
	 * @param autoStart 创建后是否调用{@link #start()}
	 */
	public EhcacheCacheManager(String configurationFileNam, boolean autoStart) {
		if(autoStart) {
			start();
		}
	}

	@Override
	public Cache getCache(String cacheName) throws CacheException {
		net.sf.ehcache.Cache cache = this.manager.getCache(cacheName);
		return null != cache ? new EhcacheCache(cache) : null;
	}
	
	@Override
	public synchronized Cache addCache(String cacheName) throws CacheException {
		try {
			net.sf.ehcache.Cache cache = this.manager.getCache(cacheName);
			if (null == cache) {
				this.manager.addCache(cacheName);
				cache = this.manager.getCache(cacheName);
			}
			return new EhcacheCache(cache);
		} catch (ObjectExistsException e) {
			throw new CacheException(e);
		} catch (IllegalStateException e) {
			throw new CacheException(e);
		} catch (ClassCastException e) {
			throw new CacheException(e);
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}
	}

	@Override
	public synchronized Cache removeCache(String cacheName) throws CacheException {
		try {
			net.sf.ehcache.Cache cache = this.manager.getCache(cacheName);
			if(null != cache) {
				this.manager.removeCache(cache.getName());
			}
			return new EhcacheCache(cache);
		} catch (IllegalStateException e) {
			throw new CacheException(e);
		}
	}

	@Override
	public Cache getCache(String cacheName, boolean autoCreate) throws CacheException {
		Cache cache = this.getCache(cacheName);
		if(null == cache && autoCreate) {
			cache = this.addCache(cacheName);
		}
		
		return cache;
	}

	/**
	 * 如果没有配置configurationFileName,使用默认的配置文件
	 */
	@Override
	public synchronized void start() throws CacheException {
		try {
			if (manager != null) {
				if(logger.isWarnEnabled()) {
					logger.warn("EhcacheCacheManager[" + this.getClass() + "] already start.");
				}
				return;
			}
			manager = (null == this.configurationFileName || this.configurationFileName.trim().length() < 1) ? net.sf.ehcache.CacheManager.create() : net.sf.ehcache.CacheManager.create(this.getClass().getClassLoader().getResourceAsStream(configurationFileName));
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}
	}

	@Override
	public synchronized void stop() {
		if(manager != null) {
			manager.shutdown();
			manager = null;
		}
	}

	public String getConfigurationFileName() {
		return configurationFileName;
	}

	public void setConfigurationFileName(String configurationFileName) {
		this.configurationFileName = configurationFileName;
	}

	@Override
	public boolean hasCache(String cacheName) throws CacheException {
		return this.manager.getCache(cacheName) != null;
	}

	@Override
	public Object getNativeCacheManager() {
		return this.manager;
	}

	@Override
	public Collection<String> getCacheNames() {
		return Arrays.asList(this.manager.getCacheNames());
	}
}
