package com.fpt.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Promotion")

public class Promotion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "promotionId", nullable = false)
	private Long promotionId;

	@Column(name = "productId", nullable = false)
	private Long productId;
	@Column(name = "discountCode", nullable = false)
	private String disountCode;
	@Column(name = "discount", nullable = false)
	private Float discount;
	@Column(name = "createDate", nullable = false)
	private Date createDate;
	@Column(name = "exprieDate", nullable = false)
	private Date exprieDate;

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
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

	

	

	

}
