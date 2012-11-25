package my.commons.framework.mybatis.interceptor;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.session.RowBounds;

/**
 * <p>
 * mybatis 拦截StatementHandler的抽象实现
 * <p>
 * 
 * @author xiegang
 * @since 2011-11-21
 * 
 */
public abstract class AbstractStatementHandlerInterceptor implements Interceptor {
	/**
	 * 获取StatementHandler
	 * 
	 * @param invocation
	 * @return
	 * @throws IllegalAccessException 
	 */
	protected StatementHandler getStatementHandler(Invocation invocation) throws IllegalAccessException {
		StatementHandler statement = (StatementHandler) invocation.getTarget();
		if (statement instanceof RoutingStatementHandler) {
			statement = (StatementHandler)FieldUtils.readField(statement, "delegate", true);
		}
		return statement;
	}

	/**
	 * 获取RowBounds
	 * 
	 * @param statement
	 * @return
	 * @throws IllegalAccessException 
	 */
	protected RowBounds getRowBounds(StatementHandler statement) throws IllegalAccessException {
		return (RowBounds)FieldUtils.readField(statement, "rowBounds", true);
	}
	
	/**
	 * 设置RowBounds的offset和limit
	 * 
	 * @param rowBounds
	 * @param offset
	 * @param limit
	 * @throws IllegalAccessException 
	 */
	protected void setRowBounds(RowBounds rowBounds, int offset, int limit) throws IllegalAccessException {
		FieldUtils.writeField(rowBounds, "offset", offset, true); // offset
		FieldUtils.writeField(rowBounds, "limit", limit, true); // limit
	}

	/**
	 * 判断RowBounds是否存在及合法
	 * @param rowBounds
	 * @return
	 */
	protected boolean hasBounds(RowBounds rowBounds) {
		return (rowBounds != null && rowBounds.getLimit() > 0 && rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT);
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}
}
