package com.fpt.entity;

public class Promotion {
	private Integer promotionId;
	private Integer productId;
	private String disountCode;
	private Float discount;
	private Long createDate;
	private Long exprieDate;

	public Integer getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getDisountCode() {
		return disountCode;
	}

	public void setDisountCode(String disountCode) {
		this.disountCode = disountCode;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Long getExprieDate() {
		return exprieDate;
	}

	public void setExprieDate(Long exprieDate) {
		this.exprieDate = exprieDate;
	}

}
