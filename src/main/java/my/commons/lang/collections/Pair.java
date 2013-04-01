package my.commons.lang.collections;

/**
 * 一对不可变的对象
 * 
 * @author xiegang
 * @since 2013-3-27
 *
 * @param <A>
 * @param <B>
 */
public class Pair<A, B> {
	private final A first;
	private final B second;

	private volatile String toStringResult;

	public Pair(A first, B second) {
		this.first = first;
		this.second = second;
	}

	public A getFirst() {
		return first;
	}

	public B getSecond() {
		return second;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		@SuppressWarnings("rawtypes")
		final Pair pair = (Pair) o;
		if (first != null ? !first.equals(pair.first) : pair.first != null) {
			return false;
		}
		if (second != null ? !second.equals(pair.second) : pair.second != null) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = first != null ? first.hashCode() : 0;
		result = 31 * result + (second != null ? second.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		if (toStringResult == null) {
			toStringResult = "Pair{" + "first=" + first + ", second=" + second + '}';
		}
		return toStringResult;
	}
}