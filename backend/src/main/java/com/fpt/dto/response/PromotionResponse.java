package com.fpt.dto.response;

import java.util.Date;

import com.fpt.entity.Product;

public class PromotionResponse {
     private Long promotionId;
     private Product product;
     private String discountCode;
     private Float discount;
     private Date createDate;
     private Date exprieDate;
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
	public Float getDiscount() {
		return discount;
	}
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getExprieDate() {
		return exprieDate;
	}
	public void setExprieDate(Date exprieDate) {
		this.exprieDate = exprieDate;
	}
	@Override
	public String toString() {
		return "PromotionResponse [promotionId=" + promotionId + ", product=" + product + ", discountCode="
				+ discountCode + ", discount=" + discount + ", createDate=" + createDate + ", exprieDate=" + exprieDate
				+ "]";
	}
     
}
