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
	private Long categoryId;
	@Column(name = "categoryparentid", nullable = true)
	private Long categoryParentId;
	@Column(name = "hassubcategory", nullable = false)
	private Boolean hasSubCategory;
	@Column(name = "categoryname", nullable = false)
	private String categoryName;
	@Column(name = "description", nullable = false,columnDefinition = "TEXT")
	private String description;
	@Column(name = "imageurl", nullable = false)
	private String imageurl;
	@Column(name = "deleted", nullable = false)
	private Boolean deleted;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	public Boolean getHasSubCategory() {
		return hasSubCategory;
	}

	public void setHasSubCategory(Boolean hasSubCategory) {
		this.hasSubCategory = hasSubCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
