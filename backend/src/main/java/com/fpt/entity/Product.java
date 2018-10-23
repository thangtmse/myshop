package com.fpt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "Product")
@Proxy(lazy = false)
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productid", nullable = false)
	private Long productId;

	@Column(name = "productname", nullable = false)
	private String productName;

	@Column(name = "categoryid", nullable = false)
	private Long categoryID;

	@Column(name = "pricein", nullable = false)
	private Double priceIn;

	@Column(name = "priceout", nullable = false)
	private Double priceOut;
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "createddate", nullable = false)
	private Date createdDate;
	@Column(name = "supplierid", nullable = false)
	private Long supplierId;
	@Column(name = "deleted", nullable = false)
	private boolean deleted;

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Long categoryID) {
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

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
