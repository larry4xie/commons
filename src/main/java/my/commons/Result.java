package my.commons;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = -4060845259179349523L;

	/** 返回码名称 */
	public static final String RET = "ret";
	/** 返回信息名称 */
	public static final String MSG = "msg";

	public Result() {
		super();
	}

	public Result(int ret, String msg, String attributeName, Object attributeValue) {
		super();
		setRet(ret).setMsg(msg).addAttribute(attributeName, attributeValue);
	}
	
	public Result(int ret, String msg) {
		super();
		setRet(ret).setMsg(msg);
	}
	
	public Result(String attributeName, Object attributeValue) {
		super();
		addAttribute(attributeName, attributeValue);
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
	
	/**
	 * result中ret和msg的jsonString
	 */
	public String toJsonString() {
		return new StringBuffer("{\"ret\":\"").append(getRet()).append("\",\"msg\":\"").append(getMsg()).append("\"}").toString();
	}

	public Result addAttribute(String attributeName, Object attributeValue) {
		Assert.notNull(attributeName, "Model attribute name must not be null");
		put(attributeName, attributeValue);
		return this;
	}

	public Result addAllAttributes(Map<String, ?> attributes) {
		if (attributes != null) {
			putAll(attributes);
		}
		return this;
	}

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

	public boolean containsAttribute(String attributeName) {
		return containsKey(attributeName);
	}
}