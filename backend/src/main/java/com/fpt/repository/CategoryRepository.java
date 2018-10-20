package com.fpt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	public Page<Category> findBycategoryNameContainingAndDeleted(String q, Boolean deleted, Pageable pageable);
}
