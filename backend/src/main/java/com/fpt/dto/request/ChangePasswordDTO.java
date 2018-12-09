package com.fpt.dto.request;

public class ChangePasswordDTO {
	private Long userId;
	private String username;
	private String curPassword;
	private String newPassword;

	public Long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCurPassword() {
		return curPassword;
	}

	public void setCurPassword(String curPassword) {
		this.curPassword = curPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
