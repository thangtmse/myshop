package com.fpt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "Category")
@Proxy(lazy = false)
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoryid", nullable = false)
	private Long categoryID;
	@Column(name = "categoryparentid", nullable = false)
	private Long categoryParentId;
	@Column(name = "categoryname", nullable = false)
	private String categoryName;
	@Column(name = "hassubcategory", nullable = false)
	private Long hasSubCategory;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "imageurl", nullable = false)
	private Boolean imageurl;
	@Column(name = "deleted", nullable = false)
	private Boolean deleted;

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}

	public Long getCategoryParentId() {
		return categoryParentId;
	}

	public void setCategoryParentId(Long categoryParentId) {
		this.categoryParentId = categoryParentId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getHasSubCategory() {
		return hasSubCategory;
	}

	public void setHasSubCategory(Long hasSubCategory) {
		this.hasSubCategory = hasSubCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getImageurl() {
		return imageurl;
	}

	public void setImageurl(Boolean imageurl) {
		this.imageurl = imageurl;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
