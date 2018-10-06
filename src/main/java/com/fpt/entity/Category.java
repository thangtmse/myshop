package com.fpt.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "categoryId", nullable = false)
	private BigInteger categoryID;
	@Column(name="categoryName",nullable=false)
	private String categoryName;
	@Column(name="description",nullable=false)
	private String description;
	
	public BigInteger getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(BigInteger categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
    
	
}
