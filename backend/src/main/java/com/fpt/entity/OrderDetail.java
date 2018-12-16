package com.fpt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OrderDetail")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "orderId", nullable = false)
	private Long orderId;

	@Column(name = "productId", nullable = false)
	private Long productId;
	@Column(name = "priceSale", nullable = false)
	private Double price;
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	@Column(name = "totalPrice", nullable = false)
	private Double totalPrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderDetail(Long id, Long orderId, Long productId, Double price, Integer quantity, Double totalPrice) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public OrderDetail() {
	}

}
