package com.fpt.dto.response;

import com.fpt.entity.Product;

public class PromotionResponse {
     private Long promotionId;
     private Product product;
     private String discountCode;
     private Integer discount;
     private Long createDate;
     private Long exprieDate;
	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
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

	@Override
	public String toString() {
		return "PromotionResponse [promotionId=" + promotionId + ", product=" + product + ", discountCode="
				+ discountCode + ", discount=" + discount + ", createDate=" + createDate + ", exprieDate=" + exprieDate
				+ "]";
	}

}
