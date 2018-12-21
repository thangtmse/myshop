package com.fpt.dto.request;

import java.util.List;

public class PromotionRequest {
private Long promotionId;
private List<Long> productIds;
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
public List<Long> getProductIds() {
	return productIds;
}
public void setProductIds(List<Long> productIds) {
	this.productIds = productIds;
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
}
