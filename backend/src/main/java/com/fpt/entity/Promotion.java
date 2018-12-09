package com.fpt.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "promotion")
@Proxy(lazy = false)
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
	private Integer discount;
	@Column(name = "createDate", nullable = false)
	private Long createDate;
	@Column(name = "exprieDate", nullable = false)
	private Long exprieDate;

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
