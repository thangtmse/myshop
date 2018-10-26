package com.fpt.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	public Page<Product> findByProductNameContainingAndDeleted(String name, Boolean deleted, Pageable pageable);

	public Page<Product> findByProductNameContainingAndCategoryIdInAndDeleted(String name, List<Long> id,
			Boolean deleted, Pageable pageable);

}
