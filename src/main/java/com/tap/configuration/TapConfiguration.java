package com.tap.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.tap.mongo.model.payment_plan;
import com.tap.mongoRepos.PlansRepo;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.util.EncodingUtils;



@Configuration
@EnableWebMvc
@EnableWebSecurity
@ComponentScan(basePackages = "com.tap" )
public class TapConfiguration extends WebSecurityConfigurerAdapter{
//	@Bean
//	public AuthServicesImp authServicesImp() {
//		return new AuthServicesImp();
//		
//	}
	@Bean 
	public PlansRepo mongoDBPOperation() {
		return new PlansRepo();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean 
	public BCrypt passEncode() {
		return new BCrypt();
	}
	
	@Bean 
	public payment_plan plans() {
		return new payment_plan();
	}
	


}