package com.tap.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tap.mongo.model.FeaturesLimits;
import com.tap.mongo.model.Payment_Plans;
import com.tap.mongo.model.payment_plan;
import com.tap.mongoRepos.PlansRepo;
import com.tap.services.PaymentPlansServices;



@RestController
@RequestMapping("/plan")
public class PaymentPlansController {

	@Autowired 
	private PaymentPlansServices dataServices;
	@Autowired
	payment_plan plans;
	@PostMapping("/create")
	public payment_plan createplane(@RequestHeader(value="X-Auth-Token", required=false) String token ,@RequestBody Map<String, Object> object) {
		if(!object.containsKey("name")  || !object.containsKey("price")  || !object.containsKey("features") ) {
			throw new RuntimeException("there is some missing data");
		}
		if(((Map<String, Object>) object.get("price")).get("amount") == null || 
				((Map<String, Object>) object.get("price")).get("currency") == null){
			throw new RuntimeException("pricing is not valid");
			}
		return this.dataServices.registerNewUser(object);
	}
	
	@PutMapping("{plan_id}/update")
	public payment_plan updateplan(@RequestHeader(value="X-Auth-Token", required=false) String token , @PathVariable String plan_id , @RequestBody Map<String, Object> object){
		 
		return this.dataServices.UpdatePlan(plan_id , object);
	}
	
	@GetMapping("/list_all")
	public Payment_Plans listplans(@RequestHeader(value="X-Auth-Token", required=false) String token){
		 return this.dataServices.ListAllPlans();
	}
	
	@GetMapping("{user_id}/plan")
	public payment_plan UserPlans(@RequestHeader(value="X-Auth-Token", required=false) String token , @PathVariable String user_id){
		 
		return this.dataServices.UserPlans(user_id);
	}
	
	@GetMapping("{user_id}/plan/feature1/limit")
	public FeaturesLimits limits(@RequestHeader(value="X-Auth-Token", required=false) String token , @PathVariable String user_id){
		 
		return this.dataServices.Featureslimits(user_id);
	}
	
	@PutMapping("{user_id}/plan/feature1/limit/update")
	public payment_plan updatelimit(@RequestHeader(value="X-Auth-Token", required=false) String token , @PathVariable String user_id , @RequestBody Map<String, Object> object){
		 
		return this.dataServices.Updatelimit(user_id , object);
	}
	
	
	


}
		
	

