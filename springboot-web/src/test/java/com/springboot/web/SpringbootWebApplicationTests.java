package com.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.springboot.web.product.Product;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootWebApplicationTests {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Product product;
	
	@Value("${productRestApi.base-url}")
	private String baseURL;

	@Test
	public void testGetProductById() { 
		
		System.out.println(baseURL);
		Product getProductById=restTemplate.getForObject(baseURL+"getProductByID/1", Product.class);
		System.out.println(getProductById.getId());
		assertEquals(1, getProductById.getId());
	}
	
	@Test
	public void testGetProduct() { 
		ResponseEntity<Product[]> getProducts=restTemplate.getForEntity(baseURL+"getProducts/", Product[].class);
		Product[] products=getProducts.getBody();
		System.out.println(products.length);
		assertNotNull(getProducts);
	}
	
	@Test
	public void testCreateProduct() { 
		product.setName("oppo");
		product.setDescription("android");
		product.setPrice(50000);
		
		Product getProduct=restTemplate.postForObject(baseURL+"createProduct/", product,Product.class);
		System.out.println(getProduct);
		assertNotNull(getProduct);
		assertEquals("oppo",getProduct.getName());
	}
	@Test
	public void testUpdateProduct() { 
		
		Product getProductById=restTemplate.getForObject(baseURL+"getProductByID/1", Product.class);
		
		getProductById.setPrice(70000);
		
		restTemplate.put(baseURL+"updateProduct/", getProductById);
		
		assertEquals(70000,getProductById.getPrice());
	}
  
}
