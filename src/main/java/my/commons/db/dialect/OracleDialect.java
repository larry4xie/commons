package my.commons.db.dialect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Oracle数据库方言
 * 
 * @author xiegang
 * @since 2012-6-13
 *
 */
public class OracleDialect extends Dialect {

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		sql = prepare(sql);
		StringBuffer pagingSelect = new StringBuffer( sql.length()+100 );
		if (offset > 0) {
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		} else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(sql);
		if (offset > 0) {
			pagingSelect.append(" ) row_ where rownum <= ").append(limit).append(") where rownum_ > ").append(offset);
		} else {
			pagingSelect.append(" ) where rownum <= ").append(limit);
		}
		return pagingSelect.toString();
	}

	@Override
	public String getLimitString(String sql, boolean hasOffset) {
		sql = prepare(sql);
		StringBuffer pagingSelect = new StringBuffer( sql.length()+100 );
		if (hasOffset) {
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		} else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(sql);
		if (hasOffset) {
			pagingSelect.append(" ) row_ where rownum <= ?) where rownum_ > ?");
		} else {
			pagingSelect.append(" ) where rownum <= ?");
		}
		return pagingSelect.toString();
	}

	@Override
	public void setLimitParamters(PreparedStatement ps, int parameterSize, int offset, int limit) throws SQLException {
		int index = 1;
		ps.setInt(parameterSize + index, limit);
		if(offset > 0) {
			ps.setInt(parameterSize + index, offset);
			index++;
		}
	}

	/**
	 * 支持分页
	 */
	@Override
	public boolean supportsLimit() {
		return true;
	}
}
