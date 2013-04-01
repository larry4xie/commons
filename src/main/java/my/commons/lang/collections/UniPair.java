package my.commons.lang.collections;

/**
 * Two elements of the same type if only java had pairs...
 * 
 * @param <T>
 */
public class UniPair<T> extends Pair<T, T> {
	public UniPair(T t, T t1) {
		super(t, t1);
	}
}
