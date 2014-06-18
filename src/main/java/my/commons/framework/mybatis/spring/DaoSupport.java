package my.commons.framework.mybatis.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import my.commons.Assert;
import my.commons.db.util.JdbcUtils;
import my.commons.exception.AppException;
import my.commons.framework.mybatis.MybatisUtils;
import my.commons.framework.mybatis.batch.Batch;
import my.commons.framework.mybatis.batch.PreparedBatch;

import org.apache.ibatis.logging.jdbc.ConnectionLogger;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;


/** 
 * <p>
 * Mybatis Base Super Dao<br/>
 * dependency: mybatis, mybatis-spring
 * </p>
 *  
 * @author xiegang
 * @since 2011-12-06
 *
 */
public abstract class DaoSupport extends org.springframework.dao.support.DaoSupport {
	private SqlSession sqlSession;

	private boolean externalSqlSession;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		if (!this.externalSqlSession) {
			this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
		}
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSession = sqlSessionTemplate;
		this.externalSqlSession = true;
	}

	public SqlSession getSession() {
		return this.sqlSession;
	}
	
	/**
	 * 获取select的总数量<br/>
	 * 采用select count(*)
	 * 
	 * @param sqlSession
	 * @param statement
	 * @param parameter
	 * @return
	 * @throws SQLException 
	 */
	public long selectCount(SqlSession sqlSession, String statement, Object parameter) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		SqlSession ss = null;
		SqlSessionFactory sf = null;
		try {
			long count = 0L;
			MappedStatement mst = sqlSession.getConfiguration().getMappedStatement(statement);
			BoundSql boundSql = mst.getBoundSql(parameter); // PreparedStatement sql
			String sql = "select count(*) total_count from (" + boundSql.getSql()+ ") tb";
			
			//Connection conn = sqlSession.getConnection(); // bug get closed connection
			SqlSessionTemplate st = (SqlSessionTemplate) sqlSession;
			sf = st.getSqlSessionFactory();
			ss = SqlSessionUtils.getSqlSession(sf, st.getExecutorType(), st.getPersistenceExceptionTranslator());
			Connection conn = ss.getConnection();
			
			if (MybatisUtils.PREPAREDSTATEMENT_LOG.isDebugEnabled() || MybatisUtils.CONNECTION_LOG.isDebugEnabled()) { // log proxy
				conn =  ConnectionLogger.newInstance(conn, MybatisUtils.PREPAREDSTATEMENT_LOG, 0);
			}
			stmt = conn.prepareStatement(sql);
			MybatisUtils.setParameters(stmt, mst, boundSql, parameter);
			rs=stmt.executeQuery();
			if (rs.next()){
				count = rs.getLong("total_count");
			}
			return count;
		} finally {
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(stmt);
			if (null != ss && null != sf) {
				SqlSessionUtils.closeSqlSession(ss, sf);
			}
		}
	}
	
	/**
	 * 开始Statement Batch
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Batch startBatch() throws SQLException {
		return new Batch(this.getSession());
	}
	
	/**
	 * 开始PreparedStatement Batch
	 * 
	 * @param statement map id
	 * @param parameter
	 * @return
	 * @throws SQLException
	 */
	public PreparedBatch startPreparedBatch(String statement, Object parameter) throws SQLException {
		return new PreparedBatch(this.getSession(), statement, parameter);
	}
	
	/**
	 * {@inheritDoc}
	 */
	protected void checkDaoConfig() {
		Assert.notNull(this.sqlSession, "Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required");
	}
	
	// ================================ shortcut and exception process =========================================
	/**
	 * 处理mybatis操作数据库的异常<br/>
	 */
	protected abstract AppException handerException(Exception e)
			throws AppException;

	public <T> T selectOne(String statement) throws AppException {
		try {
			return this.getSession().selectOne(statement);
		} catch (Exception e) {
			throw handerException(e);
		}
	}

	public <T> T selectOne(String statement, Object parameter)
			throws AppException {
		try {
			return this.getSession().selectOne(statement, parameter);
		} catch (Exception e) {
			throw handerException(e);
		}
	}

	public <E> List<E> selectList(String statement) throws AppException {
		try {
			return this.getSession().selectList(statement);
		} catch (Exception e) {
			throw handerException(e);
		}
	}

	public <E> List<E> selectList(String statement, Object parameter)
			throws AppException {
		try {
			return this.getSession().selectList(statement, parameter);
		} catch (Exception e) {
			throw handerException(e);
		}
	}

	public <E> List<E> selectList(String statement, int offset, int limit)
			throws AppException {
		try {
			return this.getSession().selectList(statement, null,
					new RowBounds(offset, limit));
		} catch (Exception e) {
			throw handerException(e);
		}
	}

	public <E> List<E> selectList(String statement, Object parameter,
			int offset, int limit) throws AppException {
		try {
			return this.getSession().selectList(statement, parameter,
					new RowBounds(offset, limit));
		} catch (Exception e) {
			throw handerException(e);
		}
	}

	public long selectCount(String statement) throws AppException {
		return this.selectCount(statement, null);
	}

	public long selectCount(String statement, Object parameter)
			throws AppException {
		try {
			return this.selectCount(this.getSession(), statement, parameter);
		} catch (SQLException e) {
			throw handerException(e);
		}
	}

	public int insert(String statement) throws AppException {
		try {
			return this.getSession().insert(statement);
		} catch (Exception e) {
			throw handerException(e);
		}
	}

	public int insert(String statement, Object parameter) throws AppException {
		try {
			return this.getSession().insert(statement, parameter);
		} catch (Exception e) {
			throw handerException(e);
		}
	}

	public int update(String statement) throws AppException {
		try {
			return this.getSession().update(statement);
		} catch (Exception e) {
			throw handerException(e);
		}
	}

	public int update(String statement, Object parameter) throws AppException {
		try {
			return this.getSession().update(statement, parameter);
		} catch (Exception e) {
			throw handerException(e);
		}
	}

	public int delete(String statement) throws AppException {
		try {
			return this.getSession().delete(statement);
		} catch (Exception e) {
			throw handerException(e);
		}
	}

	public int delete(String statement, Object parameter) throws AppException {
		try {
			return this.getSession().delete(statement, parameter);
		} catch (Exception e) {
			throw handerException(e);
		}
	}
}
