package com.tap.services;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoOperations;

import com.tap.mongo.model.FeaturesLimits;
import com.tap.mongo.model.Payment_Plans;
import com.tap.mongo.model.payment_plan;
import com.tap.mongoRepos.PlansRepo;

public abstract interface PaymentPlansServices {
	
	
	public abstract payment_plan registerNewUser(Map<String, Object> object);
	
	public abstract Payment_Plans ListAllPlans();
	
	public abstract payment_plan UpdatePlan(String plan_id , Map<String, Object> object);
	
	public abstract payment_plan UserPlans(String user_id);
	
	public abstract FeaturesLimits Featureslimits(String user_id);

	public abstract payment_plan Updatelimit(String user_id, Map<String, Object> object);
	

	
	
	
}
