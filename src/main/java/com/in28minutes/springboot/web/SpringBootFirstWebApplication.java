package com.in28minutes.springboot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.in28minutes.springboot.web")
public class SpringBootFirstWebApplication {

	/**
	* Main entry point for the Spring Boot application.
	* This method initializes and runs the Spring Boot application.
	* 
	* @param args Command-line arguments passed to the application
	* @return void This method does not return a value
	*/
	public static void main(String[] args) {
		SpringApplication.run(SpringBootFirstWebApplication.class, args);
	}
}
