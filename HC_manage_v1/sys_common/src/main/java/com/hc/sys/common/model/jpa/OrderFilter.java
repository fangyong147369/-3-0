package com.hc.sys.common.model.jpa;

/**
 * 查询
 * @author fangyong
 * @version 1.0.0.0
 * @since 2017年6月28日
 */
public class OrderFilter {

	public enum OrderType {
		DESC, ASC
	}

	public String name;
	public OrderType order;

	public OrderFilter(OrderType order, String name) {
		super();
		this.name = name;
		this.order = order;
	}

	public OrderFilter(String name) {
		super();
		this.name = name;
		this.order = OrderType.ASC;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OrderType getOrder() {
		return order;
	}

	public void setOrder(OrderType order) {
		this.order = order;
	}

}
