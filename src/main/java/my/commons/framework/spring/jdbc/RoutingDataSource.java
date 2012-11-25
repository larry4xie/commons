package my.commons.framework.spring.jdbc;

import my.commons.dao.DataRoutingContextHolder;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * <p>
 * 数据源路由,基于spring jdbc AbstractRoutingDataSource<br/>
 * 根本不同的DataRoutingContextHolder context选择datasource
 * </p>
 * @author xiegang
 * @since 2012-11-8
 * @see DataRoutingContextHolder
 *
 */
public class RoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataRoutingContextHolder.getContext();
	}

}
