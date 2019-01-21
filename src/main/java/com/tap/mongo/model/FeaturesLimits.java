package com.tap.mongo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeaturesLimits {
	private String Status;
	private Map<String, String> featureslimits = new HashMap<>();
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Map<String, String> getFeatureslimits() {
		return featureslimits;
	}
	public void setFeatureslimits(Map<String, String> featureslimits) {
		this.featureslimits = featureslimits;
	}
	@Override
	public String toString() {
		return "FeaturesLimits [Status=" + Status + ", featureslimits=" + featureslimits + "]";
	}
	
	
	

}
