package com.fpt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "[Order]")
@Proxy(lazy = false)
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderId", nullable = false)
	private Long orderId;
	@Column(name = "phone")
	private String phone;
	@Column(name = "city")
	private String city;
	@Column(name = "district")
	private String district;
	@Column(name = "address")
	private String address;
	@Column(name = "status")
	private String status;
	@Column(name = "message")
	private String message;

	@Column(name = "userId")
	private Long userId;

	@Column(name = "deliveryMethod")
	private String deliveryMethod;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "totalPrice")
	private Double totalPrice;

	@Column(name = "addAt")
	private Long addAt;

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	
	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Integer getAmount() {
		return amount;
	}


	public void setAmount(Integer amount) {
		this.amount = amount;
	}


	public Double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public Long getAddAt() {
		return addAt;
	}


	public void setAddAt(Long addAt) {
		this.addAt = addAt;
	}


	public String getAddress() {
		return address;
	}


	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	

}
