package com.fpt.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpt.entity.Product;
import com.fpt.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public Product delete(Long id) {
		Product p = productRepository.getOne(id);
		p.setDeleted(true);
		return productRepository.save(p);
	}

	public Page<Product> findByproductNameContaining(String q, Pageable pageable) {
		// TODO Auto-generated method stub
		return productRepository.findByproductNameContainingAndDeleted(q, false, pageable);
	}

	public Product findOne(Long id) {
		// TODO Auto-generated method stub
		return productRepository.getOne(id);
	}

	public Product create(Product product) {
		// TODO Auto-generated method stub
		product.setCreatedDate(new Date());
		return productRepository.save(product);

	}

	public Product update(Product product) {
		Product p = productRepository.getOne(product.getProductId());
		product.setCreatedDate(p.getCreatedDate());

		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

}
