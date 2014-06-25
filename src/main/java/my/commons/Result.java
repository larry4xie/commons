package my.commons;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * service result model
 * 
 * { ret: int, msg: string data: object [other] }
 * 
 * @author xiegang
 */
public class Result extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = -4060845259179349523L;

	/** 返回码名称 */
	public static final String RET = "ret";
	/** 返回信息名称 */
	public static final String MSG = "msg";
	/** 返回的数据部分 */
	public static final String DATA = "data";

	public Result() {
		super();
		this.setRet(0);
	}

	public Result(int ret, Object data) {
		this();
		setRet(ret).setData(data);
	}

	public Result(int ret, String msg, Object data) {
		this();
		setRet(ret).setMsg(msg).setData(data);
	}

	public int getRet() {
		return (Integer) super.get(RET);
	}

	public Result setRet(int ret) {
		put(RET, ret);
		return this;
	}

	public String getMsg() {
		return (String) super.get(MSG);
	}

	public Result setMsg(String msg) {
		put(MSG, msg);
		return this;
	}

	public String getData() {
		return (String) super.get(DATA);
	}

	public Result setData(Object data) {
		put(DATA, data);
		return this;
	}

	/**
	 * result中ret和msg的jsonString
	 */
	public String toJsonString() {
		return new StringBuffer("{\"ret\":\"").append(getRet())
				.append("\",\"msg\":\"").append(getMsg()).append("\"}")
				.toString();
	}

	public Result addAttribute(String attributeName, Object attributeValue) {
		Assert.notNull(attributeName, "Model attribute name must not be null");
		put(attributeName, attributeValue);
		return this;
	}

	/** add all attributes, 已经存在的key会被覆盖 */
	public Result addAllAttributes(Map<String, ?> attributes) {
		if (attributes != null) {
			putAll(attributes);
		}
		return this;
	}

	/** merge all attributes, 已经存在的key不会覆盖 */
	public Result mergeAttributes(Map<String, ?> attributes) {
		if (attributes != null) {
			for (String key : attributes.keySet()) {
				if (!containsKey(key)) {
					put(key, attributes.get(key));
				}
			}
		}
		return this;
	}

	/** 判断result是否已经某个属性 */
	public boolean containsAttribute(String attributeName) {
		return containsKey(attributeName);
	}
}