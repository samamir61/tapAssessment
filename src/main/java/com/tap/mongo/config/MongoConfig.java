package com.tap.mongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
	
	@Override
	public String getDatabaseName() {
		   return "tap";
		    }
	@Override
	@Bean
	public MongoClient mongoClient() {
		 return new MongoClient("127.0.0.1");	
	}
	


}