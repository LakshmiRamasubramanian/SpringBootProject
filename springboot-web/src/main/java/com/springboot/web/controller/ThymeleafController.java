package com.springboot.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.web.product.Product;

@Controller
public class ThymeleafController {

	@RequestMapping("/thymeleaf")
	public String Hello() {
		return "hello";
	}
	
	@RequestMapping("/getProductMsg")
	public ModelAndView getProductMsg() {
		ModelAndView mav=new ModelAndView("message");
		mav.addObject("message","Product Stock is available");
		return mav;
		
	}
	@RequestMapping("/getProductMsgObject")
	public ModelAndView getProductMsgObject() {
		ModelAndView mav=new ModelAndView("product");
	    Product product=new Product();
	    product.setName("oppo");
	    product.setPrice(12000);
	    mav.addObject("product", product);
	    return mav;
		
		
	}
	
	@RequestMapping("/getProductMsgObjects")
	public ModelAndView getProductMsgObjectFromHtml() {
		ModelAndView mav=new ModelAndView("productTable");
	    Product product=new Product();
	    product.setName("oppo");
	    product.setPrice(12000);
	    
	    Product product1=new Product();
	    product1.setName("Redmi");
	    product1.setPrice(12000);
	    List<Product> list=Arrays.asList(product,product1);
	    mav.addObject("productList",list);
	    return mav;
		
	}
	
	@RequestMapping("/getProductMsgObjectsFromForm")
	public ModelAndView getProductMsgObjectsFromForm() {
		ModelAndView mav=new ModelAndView("productSubmit");
	    Product product=new Product();
	    product.setName("oppo");
	    product.setPrice(12000);
	    mav.addObject("product",product);
	    return mav;
		
	}
	@RequestMapping("/saveProduct")
	public ModelAndView getProductMsgObjectsSubmitData(@ModelAttribute Product product) {
		ModelAndView mav=new ModelAndView("productResult");
	   System.out.println(product.getName());
	   System.out.println(product.getPrice());
	    return mav;
		
	}
	
}
