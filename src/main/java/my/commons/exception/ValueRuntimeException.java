package my.commons.exception;

/**
 * 用于返回值的运行时异常
 * 
 * @author xiegang
 * @since 2013-5-28
 */
public class ValueRuntimeException extends RuntimeException {
	private static final long serialVersionUID = -5561631295910836458L;

	private Object obj;

	public ValueRuntimeException() {
		super();
	}

	public ValueRuntimeException(Object value) {
		super();
		this.obj = value;
	}

	public void setValue(Object value) {
		this.obj = value;
	}

	@SuppressWarnings("unchecked")
	public <T> T getValue() {
		return (T) this.obj;
	}
}
