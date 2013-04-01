package my.commons.lang.collections;

import java.io.Serializable;

/**
 * 计数器
 * 
 * @author xiegang
 * @since 2013-3-27
 *
 */
public final class Count implements Serializable {
	private static final long serialVersionUID = 7204585816764626697L;
	private int value;

	Count(int value) {
		this.value = value;
	}

	public int get() {
		return value;
	}

	public int getAndAdd(int delta) {
		int result = value;
		value = result + delta;
		return result;
	}

	public int addAndGet(int delta) {
		return value += delta;
	}

	public void set(int newValue) {
		value = newValue;
	}

	public int getAndSet(int newValue) {
		int result = value;
		value = newValue;
		return result;
	}

	@Override
	public int hashCode() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Count && ((Count) obj).value == value;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}
}