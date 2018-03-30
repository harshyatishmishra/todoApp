package com.example.todoapp.controllers;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@org.springframework.context.annotation.Configuration

public class Configuration  extends WebMvcConfigurerAdapter {

	
	@Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/");
		
	    }

}
