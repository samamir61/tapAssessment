package com.tap.mongoRepos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.BSONObject;
import org.bson.types.ObjectId;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
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
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.lookup;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;




public class PlansRepo implements PaymentPlansServices { 
	ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
	
	
	public payment_plan CreateNewPlan(Map<String, Object> object) {
		
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
			Query planQuery = new Query(Criteria.where("_id").in(user.getPayment_plan().get("plan_id")));
			payment_plan plan = mongoOperation.findOne(planQuery, payment_plan.class);
			return new payment_plan( plan.getId() , plan.getName() , plan.getPrice() , plan.getFeatures() , Status );
		}
		Status = "user not found";
		return new payment_plan(Status);
	}

	
	
	@Override
	public FeaturesLimits Featureslimits(String user_id) {
		payment_plan plan = UserPlans(user_id);
		Query query = new Query(Criteria.where("_id").is(new ObjectId(user_id)));
		EndUser user = mongoOperation.findOne(query, EndUser.class);
		List <Object> userPlanFeatures = new ArrayList<Object>();
		String Status = "user not found";
		if(!(user == null)) {
			Status = "success";
			ArrayList<Object> UserPlan =  (ArrayList<Object>) user.getPayment_plan().get("features");
			ArrayList<Object> PlanFeatures = (ArrayList<Object>) plan.getFeatures();
			
			for(int i = 0 ; i < PlanFeatures.size(); i++) {
				
				Map<String, Object> PlanFeat = (Map<String, Object>) PlanFeatures.get(i);
				Map <String,Object> userFeaturesMap= new HashMap<>();
				Map<String, Object> FeatBalance = (Map<String, Object>) PlanFeat.get("limit");
				Integer FeatureBalance =   Integer.parseInt(FeatBalance.get("Balance").toString());	
				
				for(int j=0; j < UserPlan.size(); j++) {
					Map<String, Object> usPlan = (Map<String, Object>) UserPlan.get(j);
					
					if(usPlan.get("id").toString().equals(PlanFeat.get("id").toString()) ) {
					Integer userBalance =   Integer.parseInt(usPlan.get("Balance").toString());	
					Integer usage = Math.abs(userBalance - FeatureBalance);
					userFeaturesMap.put("id", usPlan.get("id"));
					userFeaturesMap.put("name", usPlan.get("name"));
					userFeaturesMap.put("limit", FeatureBalance);
					userFeaturesMap.put("usage", usage);
					userPlanFeatures.add(userFeaturesMap);
					}
				}
			}
			
			
			
			
		}
		
		return new FeaturesLimits(Status, userPlanFeatures);
	}

	@Override
	public payment_plan Updatelimit(String user_id, Map<String, Object> object) {
			Query query = new Query(Criteria.where("_id").is(new ObjectId(user_id)));
			EndUser user = mongoOperation.findOne(query, EndUser.class);
			payment_plan plan = UserPlans(user_id);
			ArrayList<Object> PaymentPlanFeatures =  (ArrayList<Object>) plan.getFeatures();
			System.out.println(PaymentPlanFeatures);
			ArrayList<Object> UserPlan =  (ArrayList<Object>) user.getPayment_plan().get("features");
			
			
			String Status = "";
			if(!(user == null)) {
			Update update = new Update();
				for(int i = 0; i < UserPlan.size(); i++ ) {
					Map<String, Object> usPlan = (Map<String, Object>) UserPlan.get(i);
					if(usPlan.get("id").equals(object.get("id"))) {
						for(int j = 0; j < PaymentPlanFeatures.size(); j++) {
							Map <String, Object> planFeatures = (Map<String, Object>) PaymentPlanFeatures.get(i);
							if(object.get("id").equals(planFeatures.get("id"))){
								System.out.println(planFeatures.get("limit"));
								Map <String, Object> FeatureLimit = (Map<String, Object>) planFeatures.get("limit");
								Integer FeatureBalance =   Integer.parseInt(FeatureLimit.get("Balance").toString());
								Integer updatedBalance =   Integer.parseInt(object.get("limit").toString());
								if(updatedBalance <= FeatureBalance) {
									usPlan.put("Balance", object.get("limit"));
									update.set(	"payment_plan.features", UserPlan);		
									EndUser updatePlan = mongoOperation.findAndModify(query, update, EndUser.class);
									Status = "updated succefully";
								}else {
									Status = "updated balance exceeded the feature limit";
								}
							}
						}
					}
				}
			
			
			}else {
			Status = "payment plan was not found";
			}
			
		return new payment_plan(Status);
	}
	
	

	

	



	
	

}
