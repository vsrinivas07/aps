package com.apceps.dao.cg;

import com.apceps.domain.Order;
import java.util.List;

public interface OrderDaoBase {

	public Order loadOrderByOrderId(Long orderId) throws Exception;

	public void deleteOrderByOrderId(Long orderId) throws Exception;

	public List<Order> loadOrderByProductId(Long productId) throws Exception;

	public void deleteOrderByProductId(Long productId) throws Exception;

	public List<Order> loadOrderByOrderedBy(Long orderedBy) throws Exception;

	public void deleteOrderByOrderedBy(Long orderedBy) throws Exception;

	public List<Order> loadOrderByDeliveryLocation(Long deliveryLocation) throws Exception;

	public void deleteOrderByDeliveryLocation(Long deliveryLocation) throws Exception;

	public List<Order> loadAllOrders() throws Exception;

	public Long addOrder(Order order) throws Exception;

	public void updateOrder(Order order) throws Exception;

}