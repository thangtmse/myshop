package com.fpt.service;

import com.fpt.entity.Category;

public interface CategoryService {
	Iterable<Category> findAll();

	Category findOne(Long id);
}
