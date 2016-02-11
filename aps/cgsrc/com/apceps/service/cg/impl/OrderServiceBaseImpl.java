package com.apceps.service.cg.impl;

import com.apceps.domain.Order;
import com.apceps.dao.cg.OrderDaoBase;
import com.apceps.dao.cg.impl.OrderDaoBaseImpl;
import com.apceps.service.cg.OrderServiceBase;
import com.apceps.domain.Order;
import java.util.List;

public class OrderServiceBaseImpl implements OrderServiceBase {

	private OrderDaoBase orderDaoBase = null; 

	public OrderDaoBase getOrderDaoBase() {
		return orderDaoBase;
	}

	public void setOrderDaoBase(OrderDaoBase orderDaoBase) {
		this.orderDaoBase = orderDaoBase;
	}

	public OrderServiceBaseImpl() {
		this.orderDaoBase=new OrderDaoBaseImpl();
	}

	public Order loadOrderByOrderId(Long orderId) throws Exception{
		return orderDaoBase.loadOrderByOrderId(orderId);
	}

	public void deleteOrderByOrderId(Long orderId) throws Exception{
		orderDaoBase.deleteOrderByOrderId(orderId);
	}

	public List<Order> loadOrderByProductId(Long productId) throws Exception{
		return orderDaoBase.loadOrderByProductId(productId);
	}

	public void deleteOrderByProductId(Long productId) throws Exception{
		orderDaoBase.deleteOrderByProductId(productId);
	}

	public List<Order> loadOrderByOrderedBy(Long orderedBy) throws Exception{
		return orderDaoBase.loadOrderByOrderedBy(orderedBy);
	}

	public void deleteOrderByOrderedBy(Long orderedBy) throws Exception{
		orderDaoBase.deleteOrderByOrderedBy(orderedBy);
	}

	public List<Order> loadOrderByDeliveryLocation(Long deliveryLocation) throws Exception{
		return orderDaoBase.loadOrderByDeliveryLocation(deliveryLocation);
	}

	public void deleteOrderByDeliveryLocation(Long deliveryLocation) throws Exception{
		orderDaoBase.deleteOrderByDeliveryLocation(deliveryLocation);
	}

	public List<Order> loadAllOrders() throws Exception{
		return orderDaoBase.loadAllOrders();
	}

	public Long addOrder(Order order) throws Exception{
		return orderDaoBase.addOrder(order);
	}

	public void updateOrder(Order order) throws Exception{
		orderDaoBase.updateOrder(order);
	}

}