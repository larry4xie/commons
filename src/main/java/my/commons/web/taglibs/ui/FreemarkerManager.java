package my.commons.web.taglibs.ui;

import freemarker.template.Configuration;

/**
 * jstl tag Freemarker manager<br/>
 * 需要项目注入具体的Freemarker Configuration 否则使用基于freemarker的标签会报错
 * 
 * @author xiegang
 * @since 2013-1-28
 *
 */
public class FreemarkerManager {
	private static Configuration configuration;

	/**
	 * 设置freemarker config
	 * @param config
	 */
	public FreemarkerManager(Configuration config) {
		super();
		FreemarkerManager.configuration = config;
	}

	/**
	 * 获取freemarker config
	 * @return
	 */
	public static Configuration getConfiguration() {
		return configuration;
	}
	
	/**
	 * 设置freemarker config
	 * @param config
	 */
	public static void setConfiguration(Configuration config) {
		FreemarkerManager.configuration = config;
	}
}