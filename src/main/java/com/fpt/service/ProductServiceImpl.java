package com.fpt.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.entity.Product;
import com.fpt.repository.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService {
  @Autowired
  private ProductRepository productRepository;
  
	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public List<Product> findByproductNameContaining(String q) {
		// TODO Auto-generated method stub
		return null;
	}
	
//    public void delete(BigInteger id) {
//    	productRepository.deleteById(id);
//    }
}
