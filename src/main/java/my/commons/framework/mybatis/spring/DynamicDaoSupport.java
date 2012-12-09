package my.commons.framework.mybatis.spring;

import java.util.Map;

import my.commons.Assert;
import my.commons.dao.DataRoutingContextHolder;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


/**
 * <p>
 * Mybatis Dynamic Super Dao<br/>
 * dependency: mybatis, mybatis-spring
 * </p>
 * 
 * <p>
 * 动态切换数据源：
 * <pre>
 * DataRoutingContextHolder.setContext()
 * DataRoutingContextHolder.setUniqueContext()
 * </pre>
 * </p>
 * @author xiegang
 * @since 2012-11-4
 * @see DataRoutingContextHolder
 *
 */
public class DynamicDaoSupport extends DaoSupport {
	/**
	 * SqlSessionFactory映射表
	 */
	private Map<Object, SqlSessionFactory> targetSqlSessionFactorys;  
	
	/**
	 * 默认SqlSessionFactory
	 */
	private SqlSessionFactory defaultTargetSqlSessionFactory;
	
	@Override
	public SqlSession getSession() {
		SqlSessionFactory targetSqlSessionFactory = targetSqlSessionFactorys.get(DataRoutingContextHolder.getContext()); // 获取数据路由上下文
		if (targetSqlSessionFactory != null) {
			setSqlSessionFactory(targetSqlSessionFactory);
		} else if (defaultTargetSqlSessionFactory != null) {
			setSqlSessionFactory(defaultTargetSqlSessionFactory);
		}
		return super.getSession();
	}
	
	/**
	 * 检测合法性
	 */
	@Override
	protected void checkDaoConfig() {
		Assert.notNull(this.targetSqlSessionFactorys, "Property 'targetSqlSessionFactorys' is required");
		Assert.notNull(this.defaultTargetSqlSessionFactory, "Property 'defaultTargetSqlSessionFactory' is required");
		super.checkDaoConfig();
	}

	public Map<Object, SqlSessionFactory> getTargetSqlSessionFactorys() {
		return targetSqlSessionFactorys;
	}

	/**
	 * 设置SqlSessionFactory映射表
	 * @param targetSqlSessionFactorys
	 */
	public void setTargetSqlSessionFactorys(Map<Object, SqlSessionFactory> targetSqlSessionFactorys) {
		this.targetSqlSessionFactorys = targetSqlSessionFactorys;
	}

	public SqlSessionFactory getDefaultTargetSqlSessionFactory() {
		return defaultTargetSqlSessionFactory;
	}

	/**
	 * 设置默认SqlSessionFactory
	 * @param defaultTargetSqlSessionFactory
	 */
	public void setDefaultTargetSqlSessionFactory(SqlSessionFactory defaultTargetSqlSessionFactory) {
		this.defaultTargetSqlSessionFactory = defaultTargetSqlSessionFactory;
		if (defaultTargetSqlSessionFactory != null) {
			setSqlSessionFactory(defaultTargetSqlSessionFactory);
		}
	}
}
