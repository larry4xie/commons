package my.commons.lang.comparator;

import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import my.commons.Assert;

/**
 * 属性比较器<br/>
 * 比较的对象属性必须实现了Comparator接口或者指定了比较器<br/>
 * 
 * 使用方法：<br/>
 * 按照id升序：new BeanPropertyComparator("id")<br>
 * 按照age升序：new BeanPropertyComparator("age asc");<br>
 * 按照name降序：new BeanPropertyComparator("name desc");<br>
 * 按照指定比较器比较：new BeanPropertyComparator(class, "user[DEFAULT_COMPARATOR]")<br
 * 
 * @author xiegang
 * @since 2013-8-30
 *
 * @param <T>
 */
public class BeanPropertyComparator<T> implements Comparator<T> {
	// property, must be not null
	private String property;

	// 是否升序
	private boolean asc = true;

	// 升序情况下，null排在前面，降序情况下，null排在后面。
	private boolean nullFirst = true;

	// 指定比较器(优先)
	@SuppressWarnings("rawtypes")
	private Comparator comparator;
	
	// static 比较器名称
	private String staticComparatorName;
	
	// 使用CollatorStringComparator验证string
	private StringComparator stringComparator;

	/**
	 * 按照id升序：new BeanPropertyComparator("id")<br>
	 * 按照age升序：new BeanPropertyComparator("age asc");<br>
	 * 按照name降序：new BeanPropertyComparator("name desc");<br>
	 * 按照指定比较器比较：new BeanPropertyComparator(class, "user[DEFAULT_COMPARATOR]")<br>
	 * 
	 * @param cmp comparator property description
	 */
	public BeanPropertyComparator(String cmp) {
		// 不能是空串
		Assert.notBlank(cmp);
		// 不能包含特殊字符
		Assert.isTrue(!StringUtils.contains(cmp, ','), "BeanPropertyComparator don't suport comma based format.");
		
		String cmpWhat = cmp.trim();
		
		String comparator = StringUtils.substringBetween(cmpWhat, "[", "]");
		if(StringUtils.isBlank(comparator)) {
			// 有指定排序选项
			if(StringUtils.contains(cmpWhat, " ")) {
				if (StringUtils.contains(cmpWhat, " desc")) {
					asc = false;
				}
				cmpWhat = cmpWhat.substring(0, cmpWhat.indexOf(' '));
			}
		} else {
			// 指定比较器名称，第一次比较时使用反射获取
			this.staticComparatorName = comparator;
			cmpWhat = cmpWhat.substring(0, cmpWhat.indexOf('['));
		}
		
		Assert.notBlank(cmpWhat, "comparator property description error.");
		this.property = cmpWhat;
		
		// 使用CollatorStringComparator验证string
		stringComparator = new CollatorStringComparator(asc);
	}

	/**
	 * 见{@link #BeanPropertyComparator(Object, String)},使用boolean知道升序降序
	 * 
	 * @param cmp
	 * @param asc
	 */
	public BeanPropertyComparator(String cmp, boolean asc) {
		this(cmp + (asc ? "" : " desc"));
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	public int compare(T obj1, T obj2) {
		// 属性值
		Object value1 = null;
		Object value2 = null;
		try {
			value1 = FieldUtils.readDeclaredField(obj1, this.property, true);
			value2 = FieldUtils.readDeclaredField(obj2, this.property, true);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}
		
		// null 情况判断
		if (value1 == null && null == value2) {
			return 0;
		}
		if (value1 == null && null != value2) {
			return asc && nullFirst ? -1 : 1;
		}
		if (value1 != null && null == value2) {
			return asc && nullFirst ? 1 : -1;
		}

		// 进行比较
		if (null == this.getComparator(value1.getClass())) {
			// 无指定比较器
			
			// 进行字符串比较
			if (value1 instanceof String || value2 instanceof String) {
				return stringComparator.compare(value1.toString(), value2.toString());
			} else {
				if (asc) {
					return ((Comparable<Object>) value1).compareTo(value2);
				} else {
					return ((Comparable<Object>) value2).compareTo(value1);
				}
			}
		} else {
			// 使用指定比较器比较
			return comparator.compare(value1, value2);
		}
	}
	
	/**
	 * Getter for the field <code>comparator</code>, may be null.
	 * 
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Comparator getComparator(Class<?> clazz) {
		if (null == this.comparator && null != this.staticComparatorName) {
			synchronized (this) {
				if (null == this.comparator && null != this.staticComparatorName) {
					try {
						this.comparator = (Comparator) FieldUtils.readDeclaredStaticField(clazz, staticComparatorName, true);
					} catch (IllegalAccessException e) {
						throw new IllegalArgumentException(e);
					}
				}
			}
		}
		
		return this.comparator;
	}

	/**
	 * <p>
	 * Getter for the field <code>comparator</code>, may be null.
	 * </p>
	 * 
	 * @return a {@link java.util.Comparator} object.
	 */
	@SuppressWarnings("rawtypes")
	public Comparator getComparator() {
		return comparator;
	}

	/**
	 * <p>
	 * Setter for the field <code>comparator</code>.
	 * </p>
	 * 
	 * @param comparator a {@link java.util.Comparator} object.
	 */
	@SuppressWarnings("rawtypes")
	public void setComparator(final Comparator comparator) {
		this.comparator = comparator;
	}

	/**
	 * <p>
	 * Getter for the field <code>stringComparator</code>.
	 * </p>
	 * 
	 * @return a {@link org.beangle.commons.bean.comparators.StringComparator} object.
	 */
	public StringComparator getStringComparator() {
		return stringComparator;
	}

	/**
	 * <p>
	 * Setter for the field <code>stringComparator</code>.
	 * </p>
	 * 
	 * @param stringComparator
	 */
	public void setStringComparator(final StringComparator stringComparator) {
		this.stringComparator = stringComparator;
	}

	/**
	 * <p>
	 * Getter for the field <code>name</code>.
	 * </p>
	 * 
	 * @return a {@link java.lang.String} object.
	 */
	public String getProperty() {
		return property;
	}
	
	/**
	 * <p>
	 * isAsc.
	 * </p>
	 * 
	 * @return a boolean.
	 */
	public boolean isAsc() {
		return asc;
	}

	/**
	 * <p>
	 * isNullFirst.
	 * </p>
	 * 
	 * @return a boolean.
	 */
	public boolean isNullFirst() {
		return nullFirst;
	}

	/**
	 * <p>
	 * Setter for the field <code>nullFirst</code>.
	 * </p>
	 * 
	 * @param nullFirst a boolean.
	 */
	public void setNullFirst(boolean nullFirst) {
		this.nullFirst = nullFirst;
	}

}
