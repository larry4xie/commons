package my.commons.lang.comparator;

/**
 * bean比较器,使用链式属性比较器实现{@link BeanPropertyComparator},描述字符串使用逗号分隔
 * 
 * @author xiegang
 * @since 2013-8-31
 * 
 * @param <T> bean 类型
 */
public class BeanComparator<T> extends ChainComparator<T> {
	/**
	 * 根据指定描述串生成bean比较器实例
	 * 
	 * @param cmps bean比较器描述串
	 * @return
	 */
	public static <T> BeanComparator<T> getInstance(String cmps) {
		return new BeanComparator<T>(cmps);
	}

	/**
	 * 根据指定描述串生成bean比较器实例
	 * @param cmps bean比较器描述串
	 */
	public BeanComparator(String cmps) {
		super();
		String[] properties = cmps.split(",");
		for (int i = 0; i < properties.length; i++) {
			addComparator(new BeanPropertyComparator<T>(properties[i].trim()));
		}
	}
}
