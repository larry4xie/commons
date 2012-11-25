package my.commons.db.dialect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>
 * 数据库方言<br/>
 * 主要功能：
 * <ol>
 * <li>数据库分页</li>
 * </ol>
 * </p>
 * 
 * @author xiegang
 * @since 2011-11-21
 *
 */
public abstract class Dialect {
	/**
	 * sql结束符
	 */
	protected static final String SQL_END_DELIMITER = ";"; 
	
	/**
	 * 该种方言表示的数据库是否支持分页
	 * 
	 * @return
	 */
    public abstract boolean supportsLimit();

    /**
     * 将sql变成分页sql语句,直接使用offset,limit的值作为占位符
     * 
     * @param sql
     * @param offset
     * @param limit
     * @return 包含集体值的分页sql
     */
    public abstract String getLimitString(String sql, int offset, int limit);
    
    /**
     * 将sql变成分页sql语句,提供将offset及limit使用占位符?替换
     * 
     * @param sql
     * @param hasOffset
     * @return 包含占位符的分页sql
     */
    public abstract String getLimitString(String sql, boolean hasOffset);
    
    /**
     * 准备sql主要是去除结束符
     * 
     * @param sql
     * @return
     */
    protected String prepare(String sql) {  
        sql = sql.trim(); 
        if (sql.endsWith(SQL_END_DELIMITER)) {  
            sql = sql.substring(0, sql.length() - 1 - SQL_END_DELIMITER.length());  
        }  
        return sql;  
    }  
    
    /**
     * 设置PreparedStatement参数
     * 
     * @param ps
     * @param parameterSize 预编译的sql的参数个数
     * @param offset
     * @param limit
     * @throws SQLException
     */
	public abstract void setLimitParamters(PreparedStatement ps, int parameterSize, int offset, int limit) throws SQLException;
	
	/**
     * 获取Dialect的实现，通过方言标识，如果方言不存在抛出RuntimeException
     * 
     * @param dialect
     * @return
     */
    public static Dialect getInstance(String dialect) {
    	if("mysql".equalsIgnoreCase(dialect)) {
    		return new MySQLDialect();
    	} else if("oracle".equalsIgnoreCase(dialect)) {
    		return new OracleDialect();
    	}
    	throw new RuntimeException("unknown database dialect:" + dialect);
    }
}

