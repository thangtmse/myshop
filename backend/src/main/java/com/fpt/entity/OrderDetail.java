//package com.fpt.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "oderDetail")
//public class OrderDetail {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "id", nullable = false)
//	private Integer orderId;
//	@Column(name="productId",nullable=false)
//	private Long productId;
//	@Column(name="quantity",nullable=false)
//	 private Integer quantity;
//	@Column(name="totalPrice",nullable=false)
//	 private Double totalPrice;
//	public Integer getOrderId() {
//		return orderId;
//	}
//	public void setOrderId(Integer orderId) {
//		this.orderId = orderId;
//	}
//	public Long getProductId() {
//		return productId;
//	}
//	public void setProductId(Long productId) {
//		this.productId = productId;
//	}
//	public Integer getQuantity() {
//		return quantity;
//	}
//	public void setQuantity(Integer quantity) {
//		this.quantity = quantity;
//	}
//	public Double getTotalPrice() {
//		return totalPrice;
//	}
//	public void setTotalPrice(Double totalPrice) {
//		this.totalPrice = totalPrice;
//	}
//	public OrderDetail(Integer orderId, Long productId, Integer quantity, Double totalPrice) {
//		super();
//		this.orderId = orderId;
//		this.productId = productId;
//		this.quantity = quantity;
//		this.totalPrice = totalPrice;
//	}
//	
//	
//	 
//	 
//}
