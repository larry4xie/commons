package my.commons.exception;

/**
 * <p>
 * 异常明细，由异常编码和异常信息组成<br/>
 * 异常编码用于确定异常的类型,用于程序处理和用户提示信息<br/>
 * 异常信息一般用于调试和日志,而不是用于提示用户的信息，同Exception的message
 * </p>
 * 
 * @author xiegang
 * @since 2012-6-12
 *
 */
public class ExDetail {
	/**
	 * 异常编码
	 */
	private int code;
	
	/**
	 * 异常信息,一般用于调试和日志,而不是用于提示用户的信息
	 */
	private String message;

	public ExDetail(int code) {
		this(code, "");
	}

	public ExDetail(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 获取描述
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return new StringBuffer().append("[code=").append(code).append(", message=").append(message).append("]").toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExDetail other = (ExDetail) obj;
		if (code != other.code)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
}
