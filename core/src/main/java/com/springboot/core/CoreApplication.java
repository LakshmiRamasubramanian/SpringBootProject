package com.springboot.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.springboot.service.PaymentService;
import com.springboot.service.PaymentServiceImpl;

@SpringBootApplication(scanBasePackages = { "com.springboot" })
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}
	
	
}
