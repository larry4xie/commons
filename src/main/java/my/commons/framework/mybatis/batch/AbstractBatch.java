package my.commons.framework.mybatis.batch;

import java.sql.SQLException;
import java.sql.Statement;

import my.commons.db.util.JdbcUtils;

import org.apache.ibatis.session.SqlSession;


/**
 * <p>
 * 抽象batch包装类
 * </p>
 * 
 * @author xiegang
 * @since 2012-11-6
 *
 */
public abstract class AbstractBatch {
	protected SqlSession sqlSession;
	
	protected Statement statement;
	
	/**
	 * 通过map映射的id和参数增加batch
	 * 
	 * @param statement
	 * @param parameter
	 * @throws SQLException
	 */
	public abstract void addBatch(String statement, Object parameter) throws SQLException;
	
	/**
	 * statement executeBatch
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int[] executeBatch() throws SQLException {
		return statement.executeBatch();
	}
	
	/**
	 * close statement and clear cache
	 * @throws SQLException
	 */
	public void endBatch() throws SQLException {
		JdbcUtils.closeStatement(statement);
		this.sqlSession.clearCache();
	}

	/**
	 * get Mybatis SqlSession
	 * @return
	 */
	public SqlSession getSqlSession() {
		return sqlSession;
	}
}
