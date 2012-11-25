package my.commons.lang;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Map包装类,主要用于方便的添加数据<br/>
 * 默认使用HashMap
 * </p>
 * 
 * @author xiegang
 * @since 2012-11-13
 *
 * @param <K>
 * @param <V>
 */
public class MapWrapper<K, V> {
	private Map<K, V> map;

	public MapWrapper() {
		super();
		map = new HashMap<K, V>();
	}
	public MapWrapper(K key, V value) {
		this();
		put(key, value);
	}
	
	public MapWrapper(Map<K, V> map) {
		super();
		this.map = map;
	}
	
	public MapWrapper(Map<K, V> map, K key, V value) {
		this(map);
		put(key, value);
	}

	public MapWrapper<K, V> put(K key, V value) {
		this.map.put(key, value);
		return this;
	}
	
	public V get(K key) {
		return this.map.get(key);
	}

	public Map<K, V> mapValue() {
		return this.map;
	}
}
