package com.tap.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tap.mongo.model.FeaturesLimits;
import com.tap.mongo.model.payment_plan;
import com.tap.services.PaymentPlansServices;

@RestController
@RequestMapping("/{user_id}")
public class UserPlansController {
	@Autowired 
	private PaymentPlansServices PlansServices;
	
	@GetMapping("/plan")
	public payment_plan UserPlans(@RequestHeader(value="X-Auth-Token", required=false) String token , @PathVariable String user_id){
		
		return this.PlansServices.UserPlans(user_id);
	}
	
	@GetMapping("/plan/feature1/limit")
	public FeaturesLimits limits(@RequestHeader(value="X-Auth-Token", required=false) String token , @PathVariable String user_id){
		 
		return this.PlansServices.Featureslimits(user_id);
	}
	
	@PutMapping("/plan/feature1/limit/update")
	public payment_plan updatelimit(@RequestHeader(value="X-Auth-Token", required=false) String token , @PathVariable String user_id , @RequestBody Map<String, Object> object){
		 
		return this.PlansServices.Updatelimit(user_id , object);
	}

}
