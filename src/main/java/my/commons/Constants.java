package my.commons;

/**
 * 全局常量配置
 * 
 * @author xiegang
 * @since 2013-6-20
 *
 */
public class Constants {
	/**
	 * Global Encoding
	 */
	public static String ENCODING = "UTF-8";
	
	/**
	 * 分页参数
	 */
	public static String PAGINATION_PARAMETERS = "page,page_size";

	// 实例化修改常量值
	public void setEncoding(String Encoding) {
		ENCODING = Encoding;
	}
	
	public void setPaginationParameters(String paginationParameters) {
		PAGINATION_PARAMETERS = paginationParameters;
	}
}
