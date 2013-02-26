package my.commons.db.pagination;

import java.io.Serializable;
import java.util.List;

/**
 * Pagination model Orders model and data list
 * 
 * @author xiegang
 * @since 2013-1-22
 *
 * @param <T>
 */
public class PaginationOrdersList<T> implements Serializable {
	private static final long serialVersionUID = -2390789109957405179L;

	private Pagination pagination;
	
	private Orders orders;
	
	private List<T> datas;

	public PaginationOrdersList() {
		super();
	}
	
	public PaginationOrdersList(Pagination pagination) {
		super();
		this.pagination = pagination;
	}

	public PaginationOrdersList(List<T> datas, Pagination pagination, Orders orders) {
		super();
		this.pagination = pagination;
		this.orders = orders;
		this.datas = datas;
	}
	
	public PaginationOrdersList(List<T> datas, PaginationOrders paginationOrders) {
		super();
		this.pagination = paginationOrders.getPagination();
		this.orders = paginationOrders.getOrders();
		this.datas = datas;
	}

	/**
	 * @return the pagination
	 */
	public Pagination getPagination() {
		return pagination;
	}

	/**
	 * @return the orders
	 */
	public Orders getOrders() {
		return orders;
	}
	
	/**
	 * @return the datas
	 */
	public List<T> getDatas() {
		return datas;
	}

	/**
	 * @param orders the orders to set
	 */
	public PaginationOrdersList<T> setOrders(Orders orders) {
		this.orders = orders;
		return this;
	}
	
	/**
	 * @param pagination the pagination to set
	 */
	public PaginationOrdersList<T> setPagination(Pagination pagination) {
		this.pagination = pagination;
		return this;
	}

	/**
	 * @param datas the datas to set
	 */
	public PaginationOrdersList<T> setDatas(List<T> datas) {
		this.datas = datas;
		return this;
	}
}
