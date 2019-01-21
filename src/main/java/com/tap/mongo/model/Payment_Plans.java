package com.tap.mongo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Payment_Plans {
	private String status = "success";
	private List paymentPlans = new ArrayList<String>();
	
	public Payment_Plans(List plans) {
		this.paymentPlans = plans;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public List getPaymentPlans() {
		return paymentPlans;
	}

	public void setPaymentPlans(List paymentPlans) {
		this.paymentPlans = paymentPlans;
	}

	@Override
	public String toString() {
		return "Payment_Plans [status=" + status + ", paymentPlans=" + paymentPlans + "]";
	}
	
	
	
	
	
	
}
