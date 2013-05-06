package my.commons.exception;

import java.util.Map;

/**
 * 结果信息
 * 
 * @author xiegang
 * @since 2013-4-2
 *
 */
public class Result {
	/** 返回码名称 */
	public static final String RET = "ret";
	/** 返回信息名称 */
	public static final String MSG = "msg";
	
	/** 返回码 */
	private int ret;
	/** 返回信息 */
	private String msg;
	
	public Result(int ret) {
		super();
		this.ret = ret;
	}
	
	public Result(int ret, String msg) {
		super();
		this.ret = ret;
		this.msg = msg;
	}
	
	/**
	 * 将ret和msg属性添加到map
	 * @param map
	 */
	public void add2Map(Map<Object, Object> map) {
		map.put("ret", ret);
		map.put("msg", msg);
	}
	
	public String toAjaxString() {
		return new StringBuffer("{\"ret\":\"").append(ret).append("\",\"msg\":\"").append(msg).append("\"}").toString();
	}
	
	@Override
	public String toString() {
		return "[ret=" + ret + ", msg=" + msg + "]";
	}

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
