package com.tap.mongo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeaturesLimits {
	private String Status;
	

	private List featureslimits = new ArrayList<String>();
	
	public FeaturesLimits(String Status , List featureslimits) {
		this.Status = Status;
		this.featureslimits = featureslimits;
	}
	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	public List getFeatureslimits() {
		return featureslimits;
	}
	public void setFeatureslimits(List featureslimits) {
		this.featureslimits = featureslimits;
	}
	@Override
	public String toString() {
		return "FeaturesLimits [Status=" + Status + ", featureslimits=" + featureslimits + "]";
	}
	
	
	

}
