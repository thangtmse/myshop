package com.fpt.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fpt.entity.Product;

public interface ProductService {

	Page<Product> findByproductNameContaining(String q, Pageable pageable);

	Product delete(Long id);

	Product findOne(Long id);

	Product create(Product product);

	Product update(Product product);
}
