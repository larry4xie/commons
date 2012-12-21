package my.commons.lang.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 比较器组合
 * 
 * @author xiegang
 * @since 2012-12-21
 * 
 * @param <T>
 */
public class ChainComparator<T> implements Comparator<T> {

	private List<Comparator<T>> comparators;

	/**
	 * <p>
	 * compare.
	 * </p>
	 * 
	 * @param first a T object.
	 * @param second a T object.
	 * @return 0 is equals,-1 first &lt; second ,1 first &gt; second
	 */
	public int compare(final T first, final T second) {
		int cmpRs = 0;
		for (Comparator<T> com : comparators) {
			cmpRs = com.compare(first, second);
			if (0 == cmpRs) {
				continue;
			} else {
				break;
			}
		}
		return cmpRs;
	}

	/**
	 * <p>
	 * Constructor for ChainComparator.
	 * </p>
	 */
	public ChainComparator() {
		this.comparators = new ArrayList<Comparator<T>>();
	}

	/**
	 * <p>
	 * Constructor for ChainComparator.
	 * </p>
	 * 
	 * @param comparators a {@link java.util.List} object.
	 */
	public ChainComparator(final List<Comparator<T>> comparators) {
		super();
		this.comparators = comparators;
	}

	/**
	 * <p>
	 * addComparator.
	 * </p>
	 * 
	 * @param com a {@link java.util.Comparator} object.
	 */
	public ChainComparator<T> addComparator(final Comparator<T> com) {
		this.comparators.add(com);
		return this;
	}

	/**
	 * <p>
	 * Getter for the field <code>comparators</code>.
	 * </p>
	 * 
	 * @return a {@link java.util.List} object.
	 */
	public List<Comparator<T>> getComparators() {
		return comparators;
	}

	/**
	 * <p>
	 * Setter for the field <code>comparators</code>.
	 * </p>
	 * 
	 * @param comparators a {@link java.util.List} object.
	 */
	public void setComparators(final List<Comparator<T>> comparators) {
		this.comparators = comparators;
	}
}
