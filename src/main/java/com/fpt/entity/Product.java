package com.fpt.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Product")

public class Product implements Serializable {
	 private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "productId", nullable = false)
	private BigInteger productId;
	
	@Column(name = "productName", nullable = false)
	private String productName;
	
	@Column(name = "categoryID", nullable = false)
	private BigInteger categoryID;
	
	@Column(name = "priceIn", nullable = false)
	private Double priceIn;
	
	@Column(name = "priceOut", nullable = false)
	private Double priceOut;
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "createdDate", nullable = false)
	private Date createdDate;
	@Column(name = "supplierId", nullable = false)
	private BigInteger supplierId;
	public BigInteger getProductId() {
		return productId;
	}
	public void setProductId(BigInteger productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigInteger getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(BigInteger categoryID) {
		this.categoryID = categoryID;
	}
	public Double getPriceIn() {
		return priceIn;
	}
	public void setPriceIn(Double priceIn) {
		this.priceIn = priceIn;
	}
	public Double getPriceOut() {
		return priceOut;
	}
	public void setPriceOut(Double priceOut) {
		this.priceOut = priceOut;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public BigInteger getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(BigInteger supplierId) {
		this.supplierId = supplierId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
     
	
}
