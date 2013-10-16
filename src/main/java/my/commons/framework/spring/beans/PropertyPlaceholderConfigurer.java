package my.commons.framework.spring.beans;

import java.util.Properties;

import my.commons.util.EncryptUtils;


/**
 * <p>
 * 自定义Spring的PropertyPlaceholderConfigurer类，用于获取加密的配置信息<br/>
 * 使用DES加密解密算法<br/>
 * 引用加密的属性值进行标记，比如${ENCRYPT:db.password}
 * </p>
 * 
 * <p>
 * 例子：
 * <pre>
 * &lt;property name="user" value="${db.username}"&gt;&lt;/property&gt;
 * &lt;property name="password" value="${ENCRYPT:db.password}"&gt;&lt;/property&gt;
 * </pre>
 * </p>
 * 
 * @author xiegang
 * @since 2011-11-06
 *
 */
public class PropertyPlaceholderConfigurer extends org.springframework.beans.factory.config.PropertyPlaceholderConfigurer {
	/**
	 * 标记引用加密的属性值，比如${ENCRYPT:db.password}
	 */
	private final static String DEFAULT_ENCRYPT_PLACEHOLDER_PREFIX = "ENCRYPT:";
	
	private final static String EMPTY_STRING = "";
	
	/**
	 * 密钥
	 */
	private String key = "I am ENCRYPT default key, ha ha ...";
	
	@Override
	protected String resolvePlaceholder(String placeholder, Properties props, int systemPropertiesMode) {
		if(placeholder != null && placeholder.startsWith(DEFAULT_ENCRYPT_PLACEHOLDER_PREFIX)) {
			String replaceholder = placeholder.replaceFirst(DEFAULT_ENCRYPT_PLACEHOLDER_PREFIX, EMPTY_STRING);
			String result =  super.resolvePlaceholder(replaceholder, props, systemPropertiesMode);
			
			try {
				result = EncryptUtils.DESDecrypt(result, key); // 解密
			} catch (Exception e) {
				throw new RuntimeException("spring对配置进行解密异常", e);
			} 
			return result;
		} else {
			return super.resolvePlaceholder(placeholder, props, systemPropertiesMode);
		}
	}

	public void setKey(String key) {
		this.key = key;
	}
}
