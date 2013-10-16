package my.commons.framework.spring.beans;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;

import my.commons.Assert;

/**
 * spring 静态属性注入器
 * 
 * @author xiegang
 * @since 2013-10-16
 *
 */
public class StaticFieldsInjectBean implements InitializingBean{
	/**
	 * 需要对静态属性进行注入的class名称 
	 */
	private String targetClassName;
	
	/**
	 * 静态属性名称和值的map
	 */
	private Map<String, Object> fieldValueMap;
	
	/**
	 * check config
	 */
	protected void checkConfig() {
		Assert.notBlank(targetClassName, "Property targetClassName is required");
		Assert.notNull(fieldValueMap, "Property fieldValueMap is required");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.checkConfig();
		try {
			Class<?> clazz = ClassUtils.getClass(targetClassName);
			for (Entry<String, Object> kv: fieldValueMap.entrySet()) {
				FieldUtils.writeStaticField(clazz, kv.getKey(), kv.getValue());
			}
		} catch (Exception e) {
			throw new BeanCreationException("StaticFieldsInjectBean Inject Exception", e);
		}
	}

	public String getTargetClassName() {
		return targetClassName;
	}

	public void setTargetClassName(String targetClassName) {
		this.targetClassName = targetClassName;
	}

	public Map<String, Object> getFieldValueMap() {
		return fieldValueMap;
	}

	public void setFieldValueMap(Map<String, Object> fieldValueMap) {
		this.fieldValueMap = fieldValueMap;
	}
}
