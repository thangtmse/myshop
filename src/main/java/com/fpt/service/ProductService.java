package com.fpt.service;

import java.util.List;

import com.fpt.entity.Product;

public interface ProductService {
Iterable<Product> findAll();
List<Product> findByproductNameContaining(String q);

}
