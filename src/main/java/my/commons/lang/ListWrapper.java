package my.commons.lang;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * List包装类,主要用于方便的添加数据<br/>
 * 默认使用ArrayList
 * </p>
 * 
 * @author xiegang
 * @since 2012-11-13
 *
 * @param <K>
 * @param <V>
 */
public class ListWrapper<E> {
	private List<E> list;

	public ListWrapper() {
		super();
		list = new ArrayList<E>();
	}
	public ListWrapper(E e) {
		this();
		add(e);
	}
	
	public ListWrapper(List<E> list) {
		super();
		this.list = list;
	}
	
	public ListWrapper(List<E> list, E e) {
		this(list);
		add(e);
	}

	public ListWrapper<E> add(E e) {
		this.list.add(e);
		return this;
	}
	
	public E get(int index) {
		return this.list.get(index);
	}

	public List<E> listValue() {
		return this.list;
	}
}
