package my.commons.framework.mybatis.batch;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import my.commons.framework.mybatis.MybatisUtils;

import org.apache.ibatis.logging.jdbc.ConnectionLogger;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.SqlSession;


/**
 * <p>
 * Statement的Batch包装类
 * </p>
 * 
 * @author xiegang
 * @since 2012-11-6
 *
 */
public class Batch extends AbstractBatch {
	/**
	 * connection createStatement
	 * 
	 * @param sqlSession
	 * @throws SQLException
	 */
	public Batch(SqlSession sqlSession) throws SQLException {
		this.sqlSession = sqlSession;
		Connection connection = this.sqlSession.getConnection();
		if (MybatisUtils.STATEMENT_LOG.isDebugEnabled() || MybatisUtils.CONNECTION_LOG.isDebugEnabled()) { // log proxy
			connection =  ConnectionLogger.newInstance(connection, MybatisUtils.STATEMENT_LOG);
		}
		this.statement = connection.createStatement();
	}
	
	/**
	 * statement.addBatch(String sql)
	 * 
	 * @param sql
	 * @throws SQLException
	 */
	public void addBatch(String sql) throws SQLException {
		this.statement.addBatch(sql);
	}
	
	@Override
	public void addBatch(String statement, Object parameter) throws SQLException {
		MappedStatement mst = this.sqlSession.getConfiguration().getMappedStatement(statement);
		BoundSql boundSql = mst.getBoundSql(parameter);
		this.statement.addBatch(boundSql.getSql());
	}
	
	/**
	 * get Statement
	 * 
	 * @return
	 */
	public Statement getStatement() {
		return statement;
	}
}
