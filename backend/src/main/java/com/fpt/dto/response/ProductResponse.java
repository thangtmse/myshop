package com.fpt.dto.response;

import java.util.Date;
import java.util.List;

import com.fpt.entity.Category;
import com.fpt.entity.Image;
import com.fpt.entity.Supplier;

public class ProductResponse {
	private Long productId;

	private String productName;

	private Category category;

	private Double priceIn;

	private Double priceOut;

	private Integer quantity;

	private String description;

	private Supplier supplier;
	private Date createdDate;
	private List<Image> images;

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "ProductResponse [productId=" + productId + ", productName=" + productName + ", category=" + category
				+ ", priceIn=" + priceIn + ", priceOut=" + priceOut + ", quantity=" + quantity + ", description="
				+ description + ", supplier=" + supplier + ", createdDate=" + createdDate + "]";
	}

}
