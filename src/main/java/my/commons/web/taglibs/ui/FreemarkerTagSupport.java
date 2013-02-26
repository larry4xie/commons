package my.commons.web.taglibs.ui;

import javax.servlet.jsp.tagext.TagSupport;

import freemarker.template.Configuration;

/**
 * 基于freemarker 的DinnerTagSupport 抽象类
 * 
 * @author xiegang
 * @since 2011-12-30
 *
 */
public abstract class FreemarkerTagSupport extends TagSupport {
	private static final long serialVersionUID = -956434851181242927L;
	
	protected Configuration freemarkerConfiguration;

	public FreemarkerTagSupport() {
		super();
		freemarkerConfiguration = FreemarkerManager.getConfiguration();
	}
}
