package com.fpt.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpt.entity.Product;
import com.fpt.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product delete(Long id) {
		Product p = productRepository.getOne(id);
		p.setDeleted(true);
		return productRepository.save(p);
	}

	@Override
	public Page<Product> findByproductNameContaining(String q, Pageable pageable) {
		// TODO Auto-generated method stub
		return productRepository.findByproductNameContainingAndDeleted(q, false, pageable);
	}

	@Override
	public Product findOne(Long id) {
		// TODO Auto-generated method stub
		return productRepository.getOne(id);
	}

	@Override
	public Product create(Product product) {
		// TODO Auto-generated method stub
		product.setCreatedDate(new Date());
		return productRepository.save(product);

	}

	@Override
	public Product update(Product product) {
		Product p = productRepository.getOne(product.getProductId());
		product.setCreatedDate(p.getCreatedDate());

		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

}
