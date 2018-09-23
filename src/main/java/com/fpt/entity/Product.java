package com.fpt.entity;

import java.io.Serializable;

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
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String productName;
	
	@Column(name = "categoryID", nullable = false)
	private Integer categoryID;
	
	@Column(name = "ipriceIn", nullable = false)
	private Double priceIn;
	
	@Column(name = "priceOut", nullable = false)
	private Double priceOut;
	
	@Column(name = "priceSale", nullable = false)
	private Double privateSale;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "imageID", nullable = false)
	private Integer imageID;
	
	
	
	public Product(Long id, String productName, Integer categoryID, Double priceIn, Double priceOut, Double privateSale,
			Integer quantity, String description, Integer imageID) {
		super();
		this.id = id;
		this.productName = productName;
		this.categoryID = categoryID;
		this.priceIn = priceIn;
		this.priceOut = priceOut;
		this.privateSale = privateSale;
		this.quantity = quantity;
		this.description = description;
		this.imageID = imageID;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Integer categoryID) {
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
	public Double getPrivateSale() {
		return privateSale;
	}
	public void setPrivateSale(Double privateSale) {
		this.privateSale = privateSale;
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
	public Integer getImageID() {
		return imageID;
	}
	public void setImageID(Integer imageID) {
		this.imageID = imageID;
	}
     
	
}
