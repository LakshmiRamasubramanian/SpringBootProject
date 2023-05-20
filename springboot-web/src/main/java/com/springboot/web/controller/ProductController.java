package com.springboot.web.controller;

import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.web.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.springboot.web.product.Product;

@RestController
public class ProductController {
	
    @Autowired
	ProductRepository productRepository;
    
    private static final Logger LOGGER=LoggerFactory.getLogger(ProductController.class);
    
    @RequestMapping(value="/getProducts/" , method=RequestMethod.GET)
  
    public  List<Product> getProducts() {
    	LOGGER.info("Get ALL PRODUCTS ");
    	return productRepository.findAll();
    }

   
    @RequestMapping(value="/getProductByID/{id}" , method=RequestMethod.GET)
    @Transactional(readOnly=true)
    @Cacheable("product-cache")
    public  Product getProductById(@PathVariable int id) {
    	LOGGER.info("Get PRODUCTS By ID "+ id);
    	return productRepository.findById(id).get();
    }
    
    @RequestMapping(value="/updateProduct/" , method=RequestMethod.PUT)
    public  Product updateProductById(@RequestBody Product product) {
    	LOGGER.info("Update PRODUCTS By ID ");
    	return productRepository.save(product);
    }
    
    @RequestMapping(value="/createProduct/" , method=RequestMethod.POST)
    public  Product createProduct(@RequestBody Product product) {
    	LOGGER.info("Create PRODUCT ");
    	return productRepository.save(product);
    }
    
    @RequestMapping(value="/deleteProduct/" , method=RequestMethod.DELETE)
    @CacheEvict("product-cache")
    public  void deleteProduct(@RequestBody Product product) {
    	LOGGER.info("Delete Product ");
    productRepository.deleteById(product.getId());
     
    }
	
}
