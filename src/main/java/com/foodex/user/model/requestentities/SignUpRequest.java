package com.foodex.user.model.requestentities;

public class SignUpRequest {
	private String firstName;
    private String lastname;
    private String emailId;
    private String phoneNumber;
    private String password;
    private String confirmPassword;

    public String getLastname() {
        return lastname;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
				+ ", phoneNumber=" + phoneNumber + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ "]";
	}

}
