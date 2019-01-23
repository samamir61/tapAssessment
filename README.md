# tapAssessment

# Technologies 

* 1- Java JDK 8 
* 2- Maven  3.2
* 3- Mongo db v3.6.3 
* 4- Spring framework 5.1.2.RELEASE
* 5- Apatceh tomcat v9.0

# setup 

Install the previous technologies on the machiene 

# run the project 

* 1- run the project as maven install to install the required depdependencies
* 2- run the project on tomcat server

# IDE 
Eclipse Oxygen  

# Notes about DB 

* You need to manually insert user as the following 
{"_id":"5c45e7321267ab423e5b3236",
"userName":"String",
"email":"String",
"firstName":"String"
,"lastName":"String",
"password":"String",
"payment_plan":{"plan_id":"String","name":"String",
"features":[{"id":"String","name":"String","Balance":String}}]
}
}

* you can you use MongoDB compass to ease the proccess of checking and creating tables and modefying data in MongoDB

# APIS URLS


# Create plan : 

# Request : 
* http://localhost:8080/tapAssessment/api/plan/create
* Method : POST
* RequestBody: {
	  "name": String,
	  "price": {
	    "amount": int,
	    "currency": String
	  },
	  "features": [
	    {
	      "id": String,
	      "name": String,
	      "limit": {
		"amount": int,
		"unit": String,
		"Balance": int,
		"extraCharge": {
		  "amount": String,
		  "currency": String,
		  "unit": String
		}
	      }
	    },
	    {
	      "id": String,
	      "name": String,
	      "limit": {
		"amount": int,
		"unit": String,
		"Balance": int,
		"extraCharge": {
		  "amount": String,
		  "currency": String,
		  "unit": String
		}
	      }
	    }
	  ]
	}

* Response : {
		"id": "5c4855337f230a37486a9f4a",
		"name": "plan 1",
		"price": {
		"amount": 10,
		"currency": "KWD"
		},
		"features": [
		  {
		"id": "1.1",
		"name": "Calls",
		"limit": {
		"amount": 100,
		"unit": "Minutes",
		"Balance": 100,
		"extraCharge": {
		"amount": "0.001",
		"currency": "KWD",
		"unit": "minutes"
		}
		}
		},
		  {
		"id": "1.2",
		"name": "Messages",
		"limit": {
		"amount": 250,
		"unit": "Messages",
		"Balance": 250,
		"extraCharge": {
		"amount": "0.025",
		"currency": "KWD",
		"unit": "messages"
		}
		}
		}
		],
		"status": "success"
	  }


# Update Plan : 

* Request : http://localhost:8080/tapAssessment/api/plan/{plan_id}/update
* Method : PUT

* RequestBody : {"" : ""}

* Response: 
	{
	 .....
	 
	  "status": ""
	 .....
	}


# List All Plans 

* Request : http://localhost:8080/tapAssessment/api/plan/list_all
* Method :GET



# User Plan 

* Request : http://localhost:8080/tapAssessment/api/{user_id}/plan
* Method : GET



# plan feature limit and usage

* Request : http://localhost:8080/tapAssessment/api/{user_id}/plan/feature1/limit
* Method : GET 

* Response: {
		"featureslimits": [
		  {
		"usage": 0,
		"name": "String",
		"limit": Int,
		"id": "String"
		}
		],
		"status": "success"
	 }


# update feature limit : 

* Reuqest : http://localhost:8080/tapAssessment/api/{user_id}/plan/feature1/limit/update
* Method : PUT 

* Request : {
	    "id" : "String", 
	    "limit" : Int
	  }
 
* Respone : {
	
	   .....
	 
	    "status": ""
	   .....
	 }

 
  
