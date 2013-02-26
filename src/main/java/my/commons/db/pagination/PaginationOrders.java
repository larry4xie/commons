package my.commons.db.pagination;


/**
 * Pagination model and Orders model
 * 
 * @author xiegang
 * @since 2013-1-22
 */
public class PaginationOrders {

	public Pagination pagination;
	
	public Orders orders;
	
	public PaginationOrders() {
		super();
	}
	
	public PaginationOrders(Pagination pagination, Orders orders) {
		super();
		this.pagination = pagination;
		this.orders = orders;
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
	 * @param orders the orders to set
	 */
	public PaginationOrders setOrders(Orders orders) {
		this.orders = orders;
		return this;
	}
	
	/**
	 * @param pagination the pagination to set
	 */
	public PaginationOrders setPagination(Pagination pagination) {
		this.pagination = pagination;
		return this;
	}
}
