package com.tap.mongoRepos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.DBObject;
import com.mongodb.client.result.UpdateResult;
import com.tap.mongo.config.MongoConfig;
import com.tap.mongo.model.EndUser;
import com.tap.mongo.model.FeaturesLimits;
import com.tap.mongo.model.Payment_Plans;
import com.tap.mongo.model.payment_plan;
import com.tap.services.PaymentPlansServices;





public class PlansRepo implements PaymentPlansServices { 
	ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
	
	
	public payment_plan registerNewUser(Map<String, Object> object) {
		
		payment_plan plan = new payment_plan();
		plan.setName((String) object.get("name"));
		plan.setPrice((Map<String, Object>) object.get("price"));
		plan.setFeatures((ArrayList) object.get("features"));
		mongoOperation.save(plan);
		String status = "success";
		return new payment_plan(plan.getId(),plan.getName() , plan.getPrice() , plan.getFeatures() , status);
	}

	@Override
	public Payment_Plans ListAllPlans() {
		List<payment_plan> plans =  mongoOperation.findAll(payment_plan.class);
		return new Payment_Plans(plans);
	}

	@Override
	public payment_plan UpdatePlan(String plan_id, Map<String, Object> object) {
		Query query = new Query(Criteria.where("_id").is(new ObjectId(plan_id)));
		payment_plan plan = mongoOperation.findOne(query, payment_plan.class);
		String Status = "";
		if(!(plan == null)) {
		Update update = new Update();
		for ( String key : object.keySet() ) {
			update.set(key, object.get(key));
		}
		payment_plan updatePlan = mongoOperation.findAndModify(query, update, payment_plan.class);
		Status = "updated succefully";
		}else {
		Status = "payment plan was not found";
		}
		
		return new payment_plan(Status);
	}

	@Override
	public payment_plan UserPlans(String user_id) {
		Query query = new Query(Criteria.where("_id").is(new ObjectId(user_id)));
		EndUser user = mongoOperation.findOne(query, EndUser.class);
		String Status = "";
		if(!(user == null)) {
			Status = "success";	
			Query planQuery = new Query(Criteria.where("_id").in(user.getPayment_plan()));
			payment_plan plan = mongoOperation.findOne(planQuery, payment_plan.class);
			return new payment_plan( plan.getId() , plan.getName() , plan.getPrice() , plan.getFeatures() , Status );
		}
		Status = "user not found";
		return new payment_plan(Status);
	}

	@Override
	public FeaturesLimits Featureslimits(String user_id) {
		payment_plan plan = UserPlans(user_id);
		int i = 0;
		
		while(i <= plan.getFeatures().size()) {
			
		}
		return null;
	}
	
	

	

	



	
	

}
