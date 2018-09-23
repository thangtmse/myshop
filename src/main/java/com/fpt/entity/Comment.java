package com.fpt.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "commentId", nullable = false)
	private Integer commentId;
	@Column(name = "productId", nullable = false)
	private Long productId;
	@Column(name = "userId", nullable = false)
	private Integer userId;
	@Column(name = "createdDate", nullable = false)
	private Date createdDate;
	@Column(name = "content", nullable = false)
	private String content;
	
	public Comment(Integer commentId, Long productId, Integer userId, Date createdDate, String content) {
		super();
		this.commentId = commentId;
		this.productId = productId;
		this.userId = userId;
		this.createdDate = createdDate;
		this.content = content;
	}
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
