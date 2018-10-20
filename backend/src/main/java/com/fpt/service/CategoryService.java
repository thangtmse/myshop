package com.fpt.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fpt.entity.Category;

public interface CategoryService {
	Page<Category> findByNameContaining(String q, Pageable pageable);

	Category findOne(Long id);

	Category update(Category category);

	Category create(Category category);

	Category delete(Long id);
}
