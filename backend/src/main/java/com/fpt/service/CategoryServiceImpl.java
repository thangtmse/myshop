package com.fpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.entity.Category;
import com.fpt.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Iterable<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	public Category findOne(Long id) {
		return categoryRepository.getOne(id);
	}

	@Override
	public Category update(Category category) {
		category = categoryRepository.getOne(category.getCategoryID());
		// TODO Auto-generated method stub
		return categoryRepository.save(category);
	}

	@Override
	public Category create(Category category) {
		// TODO Auto-generated method stub
		return categoryRepository.save(category);
	}

}
