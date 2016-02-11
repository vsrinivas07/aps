package com.apceps.domain.cg;

import java.sql.Date;

public class OrderBase {

	private Long orderId = null; 

	private Long productId = null; 

	private Long quantity = null; 

	private Long orderedBy = null; 

	private Date orderDate = null; 

	private Long deliveryLocation = null; 

	private Date prodPickupDate = null; 

	private Date whsReachDate = null; 

	private Date whsPickupDate = null; 

	private Date deliveryDate = null; 

	private String receivedBy = null; 

	private String orderStatus = null; 

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(Long orderedBy) {
		this.orderedBy = orderedBy;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Long getDeliveryLocation() {
		return deliveryLocation;
	}

	public void setDeliveryLocation(Long deliveryLocation) {
		this.deliveryLocation = deliveryLocation;
	}

	public Date getProdPickupDate() {
		return prodPickupDate;
	}

	public void setProdPickupDate(Date prodPickupDate) {
		this.prodPickupDate = prodPickupDate;
	}

	public Date getWhsReachDate() {
		return whsReachDate;
	}

	public void setWhsReachDate(Date whsReachDate) {
		this.whsReachDate = whsReachDate;
	}

	public Date getWhsPickupDate() {
		return whsPickupDate;
	}

	public void setWhsPickupDate(Date whsPickupDate) {
		this.whsPickupDate = whsPickupDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getReceivedBy() {
		return receivedBy;
	}

	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}