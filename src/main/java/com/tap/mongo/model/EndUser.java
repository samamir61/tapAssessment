package com.tap.mongo.model;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "end_user")
public class EndUser {
	private String userName;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String payment_plan;
	public String getPayment_plan() {
		return payment_plan;
	}
	public void setPayment_plan(String payment_plan) {
		this.payment_plan = payment_plan;
	}
	private String access_token;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	@Override
	public String toString() {
		return "EndUser [userName=" + userName + ", email=" + email + ", firstName=" + firstName + ", lastName="
				+ lastName + ", password=" + password + ", payment_plan=" + payment_plan + ", access_token="
				+ access_token + "]";
	}
	
	
	
	
	
	
	
	

}
