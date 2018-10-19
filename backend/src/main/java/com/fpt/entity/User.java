//package com.fpt.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name="user")
//public class User {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "userId", nullable = false)
//   private Integer userId;
//	@Column(name="email",nullable=false)
//   private String email;
//	@Column(name="displayName",nullable=false)
//   private String displayName;
//	@Column(name="md5Password",nullable=false)
//   private String md5PassWord;
//	@Column(name="rollId",nullable=false)
//   private Integer rollId;
//	@Column(name="addRess",nullable=false)
//   private String addResss;
//	@Column(name="provider",nullable=false)
//   private String provider;
//	public Integer getUserId() {
//		return userId;
//	}
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getDisplayName() {
//		return displayName;
//	}
//	public void setDisplayName(String displayName) {
//		this.displayName = displayName;
//	}
//	public String getMd5PassWord() {
//		return md5PassWord;
//	}
//	public void setMd5PassWord(String md5PassWord) {
//		this.md5PassWord = md5PassWord;
//	}
//	public Integer getRollId() {
//		return rollId;
//	}
//	public void setRollId(Integer rollId) {
//		this.rollId = rollId;
//	}
//	public String getAddResss() {
//		return addResss;
//	}
//	public void setAddResss(String addResss) {
//		this.addResss = addResss;
//	}
//	public String getProvider() {
//		return provider;
//	}
//	public void setProvider(String provider) {
//		this.provider = provider;
//	}
//	public User(Integer userId, String email, String displayName, String md5PassWord, Integer rollId, String addResss,
//			String provider) {
//		super();
//		this.userId = userId;
//		this.email = email;
//		this.displayName = displayName;
//		this.md5PassWord = md5PassWord;
//		this.rollId = rollId;
//		this.addResss = addResss;
//		this.provider = provider;
//	}
//	
//}
