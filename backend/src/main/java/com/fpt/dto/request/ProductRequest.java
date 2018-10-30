package com.fpt.dto.request;

import java.util.List;

import com.fpt.entity.Image;

public class ProductRequest {
	private Long productId;

	private String productName;

	private Long categoryID;

	private Double priceIn;

	private Double priceOut;

	private Integer quantity;

	private String description;

	private Long supplierId;
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

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "ProductRequest [productId=" + productId + ", productName=" + productName + ", categoryID=" + categoryID
				+ ", priceIn=" + priceIn + ", priceOut=" + priceOut + ", quantity=" + quantity + ", description="
				+ description + ", supplierId=" + supplierId + "]";
	}

}
