package com.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springboot.Dao.PaymentDao;
import com.springboot.Dao.PaymentDaoImpl;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentDao dao;
	

	public PaymentDao getDao() {
		return dao;
	}

	public void setDao(PaymentDao dao) {
		this.dao = dao;
		//System.out.println(dao);
	}
	//assignment 1: Spring boot project
	
	public int paymentSum(int payment1, int payment2) {
		return payment1+payment2;
	}
	
	
}
