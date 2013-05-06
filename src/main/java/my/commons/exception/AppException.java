package my.commons.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 应用运行时异常<br/>
 * 异常必须要有code
 * </p>
 * 
 * @author xiegang
 * @since 2011-12-06
 *
 */
public class AppException extends RuntimeException {
	private static final long serialVersionUID = 5694277643293106939L;
	
	private static final Logger LOG = LoggerFactory.getLogger(AppException.class);
	
	/**
	 * 异常明细
	 */
	private ExDetail detail;
	
	public AppException(int code) {
		this(new ExDetail(code, ""));
	}
	
	public AppException(int code, String message) {
		this(new ExDetail(code, message));
	}
	
	public AppException(int code, Throwable cause) {
		this(new ExDetail(code, ""), cause);
	}
	
	public AppException(int code, String message, Throwable cause) {
		this(new ExDetail(code, message), cause);
	}
	
	public AppException(ExDetail detail) {
		super(detail.getMessage());
		this.detail = detail;
		
		// 打印异常日志
		LOG.error(this.detail.toString());
	}
	
	public AppException(ExDetail detail, Throwable cause) {
		super(detail.getMessage(), cause);
		this.detail = detail;
		
		if (!(cause instanceof AppException)) { 
			// 不是系统自定义的异常，打印异常日志
			LOG.error(this.detail.toString(), cause);
		} else {
			AppException e = (AppException)cause;
			if (!e.getDetail().equals(this.detail)) {
				// 包装详细信息发送变化
				LOG.error(this.detail.toString());
			}
		}
	}
	
	/**
	 * 获取异常明细
	 * @return
	 */
	public ExDetail getDetail() {
		return detail;
	}

	public void setDetail(ExDetail detail) {
		this.detail = detail;
	}
	
	/**
	 * 异常编码
	 * @return
	 */
	public int getExCode() {
		return this.getDetail().getCode();
	}
	
	/**
	 * 异常信息
	 * @return
	 */
	public String getExMessage() {
		return this.getDetail().getMessage();
	}

	/**
	 * 异常信息包括被包装的异常
	 */
	public String getMessage() {
		if (this.getCause() != null) {
			StringBuffer buf = new StringBuffer();
			if (super.getMessage() != null) {
				buf.append(super.getMessage()).append("; ");
			}
			buf.append("nested exception is ").append(this.getCause());
			return buf.toString();
		}

		return super.getMessage();
	}
}
