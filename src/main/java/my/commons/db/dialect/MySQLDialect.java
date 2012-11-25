package my.commons.db.dialect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>
 * mysql数据库方言
 * </p>
 * 
 * @author xiegang
 * @since 2012-6-13
 * @see Dialect
 *
 */
public class MySQLDialect extends Dialect {
	@Override
	public String getLimitString(String sql, int offset, int limit) {
		sql = prepare(sql); // 准备sql
		StringBuffer sb = new StringBuffer(sql);
		if (offset > 0) {
			sb.append(" limit ").append(offset).append(',').append(limit);
		} else {
			sb.append(" limit ").append(limit);
		}
		return sb.toString();
	}

	@Override
	public String getLimitString(String sql, boolean hasOffset) {
		return new StringBuffer(prepare(sql)).append(hasOffset ? " limit ?,?" : " limit ?").toString();
	}

	@Override
	public void setLimitParamters(PreparedStatement ps, int parameterSize, int offset, int limit) throws SQLException {
		int index = 1;
		if(offset > 0) {
			ps.setInt(parameterSize + index, offset);
			index++;
		}
		ps.setInt(parameterSize + index, limit);
	}
	
	/**
	 * 支持分页
	 */
	@Override
	public boolean supportsLimit() {
		return true;
	}
}
