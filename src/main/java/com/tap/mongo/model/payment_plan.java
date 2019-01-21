package com.tap.mongo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "payment_plans")
public class payment_plan {
	@Id
	private String id;
	private String name;
	private Map<String, Object> price = new HashMap<>();
	private ArrayList features = new ArrayList();
	
	private String Status; 
	

	public payment_plan() {
		
	}
	
	public payment_plan(String status) {
		this.Status = status;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public payment_plan( String id , String name , Map<String, Object> price , ArrayList features , String  status){
		this.id = id;
		this.name = name;
		this.price = price;
		this.features = features;
		this.Status = status;
		
	}
	

	public ArrayList getFeatures() {
		return features;
	}
	public void setFeatures(ArrayList features) {
		this.features = features;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, Object> getPrice() {
		return price;
	}
	public void setPrice(Map<String, Object> price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "payment_plan [id=" + id + ", name=" + name + ", price=" + price + ", features=" + features + ", Status="
				+ Status + "]";
	}
	
	
	

}
