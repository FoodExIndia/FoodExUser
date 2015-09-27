package com.foodex.user.model.dataentities;

import java.sql.Date;

public class UsersEntity {
	private int clientKey;
	private String emailId;
	private long mobileNum;
	private String password;
	private int recentOTP;
	private Date insertDate;
	private Date updateDate;

	@Override
	public String toString() {
		return "UsersEntity [clientKey=" + clientKey + ", emailId=" + emailId + ", mobileNum=" + mobileNum
				+ ", password=" + password + ", recentOTP=" + recentOTP + ", insertDate=" + insertDate + ", updateDate="
				+ updateDate + "]";
	}

	public int getClientKey() {
		return clientKey;
	}

	public void setClientKey(int clientKey) {
		this.clientKey = clientKey;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(long mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRecentOTP() {
		return recentOTP;
	}

	public void setRecentOTP(int recentOTP) {
		this.recentOTP = recentOTP;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
