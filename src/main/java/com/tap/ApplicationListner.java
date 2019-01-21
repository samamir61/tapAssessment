package com.tap;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class ApplicationListner implements ApplicationListener<ApplicationEvent> {
	public void onApplicationEvent(ApplicationEvent event) {
        if(event.toString().contains("dispatcher-servlet")) {
   		
        }
    };

}