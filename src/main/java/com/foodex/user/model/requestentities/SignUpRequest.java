package com.foodex.user.model.requestentities;

public class SignUpRequest {
	private String firstName;
	private String lastname;
	private String emailId;
	private String phoneNumber;
	private String password;
	private String addressLine1;
	private String addressLine2;
	private String state;
	private String city;
	private String pincode;
	private String dob;

	public String getLastname() {
		return lastname;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String toString() {
		return "SignUpRequest [firstName=" + firstName + ", lastname=" + lastname + ", emailId=" + emailId
				+ ", phoneNumber=" + phoneNumber + ", password=" + password + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", state=" + state + ", city=" + city + ", pincode=" + pincode
				+ ", dob=" + dob + "]";
	}
	
	
}
