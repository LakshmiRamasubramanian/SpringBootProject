package com.springboot.web.health;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.springboot.web.controller.ProductController;
import com.springboot.web.product.Product;
import com.springboot.web.repository.ProductRepository;

@Component
public class CustomHealthIndicator implements HealthIndicator {
	
	@Autowired
	private  ProductRepository productRepo;

	@Override
	public Health health() {
		
	Product getAllProducts=productRepo.findById(1).get();
		System.out.println("custom health indicator "+getAllProducts);
		
		if(getAllProducts.getId()!=1) {
			return Health.down().withDetail("No product stocks", getAllProducts).build();
		}
	   return Health.up().withDetail("product stock available", getAllProducts).build();
	
	}
}
