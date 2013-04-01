package my.commons.lang.wrap;

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
public class ListWrap<E> implements Wrap<List<E>> {
	private List<E> list;

	public ListWrap() {
		super();
		list = new ArrayList<E>();
	}
	public ListWrap(E e) {
		this();
		add(e);
	}
	
	public ListWrap(List<E> list) {
		super();
		this.list = list;
	}
	
	public ListWrap<E> add(E e) {
		this.list.add(e);
		return this;
	}
	
	public E get(int index) {
		return this.list.get(index);
	}

	public List<E> getValue() {
		return this.list;
	}
}
