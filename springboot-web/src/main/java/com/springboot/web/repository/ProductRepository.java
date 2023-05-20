package com.springboot.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.web.product.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
