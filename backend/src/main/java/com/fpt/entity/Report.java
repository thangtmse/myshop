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
@Table(name = "Report")
@Proxy(lazy = false)
public class Report {
	

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "reportid", nullable = false)
		private Long reportId;
		@Column(name = "userid", nullable = true)
		private Long userId;
		@Column(name = "createddate", nullable = false)
		private Date createdDate;
		@Column(name = "content", nullable = false)
		private String content;
		public Long getReportId() {
			return reportId;
		}
		public void setReportId(Long reportId) {
			this.reportId = reportId;
		}
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
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
