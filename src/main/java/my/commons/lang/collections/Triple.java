package my.commons.lang.collections;

/**
 * 不可变三元组
 * 
 * @author xiegang
 * @since 2013-3-27
 *
 * @param <A>
 * @param <B>
 * @param <C>
 */
public class Triple<A, B, C> {
	private final A first;
	private final B second;
	private final C third;

	private volatile String toStringResult;

	public Triple(A first, B second, C third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}

	public A getFirst() {
		return first;
	}

	public B getSecond() {
		return second;
	}

	public C getThird() {
		return third;
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
		final Triple triple = (Triple) o;
		if (first != null ? !first.equals(triple.first) : triple.first != null) {
			return false;
		}
		if (second != null ? !second.equals(triple.second)
				: triple.second != null) {
			return false;
		}
		if (third != null ? !third.equals(triple.third) : triple.third != null) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = first != null ? first.hashCode() : 0;

		result = 31 * result + (second != null ? second.hashCode() : 0);
		result = 31 * result + (third != null ? third.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		if (toStringResult == null) {
			toStringResult = "Triple{" + "first=" + first + ", second=" + second + ", third=" + third + '}';
		}
		return toStringResult;
	}
}
