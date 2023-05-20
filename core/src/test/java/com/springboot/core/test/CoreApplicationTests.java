package com.springboot.core.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.service.PaymentService;
import com.springboot.service.PaymentServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoreApplicationTests {
	
	 @Autowired
	 PaymentServiceImpl service;
	 
	 
	 @Autowired
	 PaymentService serviceSum;

	@Test
	 public void testDepedndencyInjections() {
		System.out.print(" check object " + service.getDao());
		assertNotNull(service.getDao());
	}
	
	//assignment 1: Spring boot project
	
	@Test
	 public void checkPaymentSum() {
		System.out.print(" check sum");
		assertEquals(50, serviceSum.paymentSum(300,30));
	}
	
	

}
