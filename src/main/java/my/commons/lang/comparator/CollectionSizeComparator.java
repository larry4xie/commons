package my.commons.lang.comparator;

import java.util.Collection;
import java.util.Comparator;

/**
 * 比较两个集合，元素多的大
 * 
 * @author xiegang
 * @since 2012-12-21
 * 
 * @param <T>
 */
public class CollectionSizeComparator<T extends Collection<?>> implements Comparator<T> {
	/**
	 * <p>
	 * compare.
	 * </p>
	 * 
	 * @param first a T object.
	 * @param second a T object.
	 * @return equals : 0,first less then second : -1 or small , first greate then second : 1 or big
	 */
	public int compare(final T first, final T second) {
		return first.equals(second) ? 0 : (first.size() - second.size());
	}
}
