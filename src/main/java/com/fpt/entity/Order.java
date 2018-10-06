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
//@Table(name = "oder")
//public class Order {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "orderId", nullable = false)
//	private Integer orderId;
//	@Column(name = "phone")
//	private String phone;
//	@Column(name = "city")
//	private String city;
//	@Column(name = "district")
//	private String district;
//	@Column(name = "address")
//	private String address;
//	@Column(name = "status")
//	private String status;
//	@Column(name = "message")
//	private String message;
//	public Integer getOrderId() {
//		return orderId;
//	}
//	public void setOrderDetailId(Integer orderId) {
//		this.orderId = orderId;
//	}
//	public String getPhone() {
//		return phone;
//	}
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//	public String getCity() {
//		return city;
//	}
//	public void setCity(String city) {
//		this.city = city;
//	}
//	public String getDistrict() {
//		return district;
//	}
//	public void setDistrict(String district) {
//		this.district = district;
//	}
//	public String getAddress() {
//		return address;
//	}
//	public void setAddress(String address) {
//		this.address = address;
//	}
//	public String getStatus() {
//		return status;
//	}
//	public void setStatus(String status) {
//		this.status = status;
//	}
//	public String getMessage() {
//		return message;
//	}
//	public void setMessage(String message) {
//		this.message = message;
//	}
//	public Order(Integer orderId, String phone, String city, String district, String address, String status,
//			String message) {
//		super();
//		this.orderId = orderId;
//		this.phone = phone;
//		this.city = city;
//		this.district = district;
//		this.address = address;
//		this.status = status;
//		this.message = message;
//	}
//          
//}
