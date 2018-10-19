package com.fpt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	public Page<Product> findByproductNameContainingAndDeleted(String name, Boolean deleted, Pageable pageable);
}
