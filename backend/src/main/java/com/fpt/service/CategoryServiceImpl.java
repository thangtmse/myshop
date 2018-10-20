package com.fpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpt.entity.Category;
import com.fpt.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

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

	@Override
	public Category delete(Long id) {
		Category category = categoryRepository.getOne(id);
		category.setDeleted(true);
		// TODO Auto-generated method stub
		return categoryRepository.save(category);
	}

	@Override
	public Page<Category> findByNameContaining(String q, Pageable pageable) {
		// TODO Auto-generated method stub
		return categoryRepository.findBycategoryNameContainingAndDeleted(q, false, pageable);
	}

}
