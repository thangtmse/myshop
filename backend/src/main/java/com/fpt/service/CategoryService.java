package com.fpt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fpt.controller.error.APIException;
import com.fpt.entity.Category;
import com.fpt.repository.CategoryRepository;

@Service("categoryService")
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}

	public List<Long> findCategoryId(Long id) throws Exception {
		try {
			if (id == null || categoryRepository.findBycategoryIdAndDeleted(id, false) == null)
				return new ArrayList<>();
			List<Long> lc = categoryRepository.findBycategoryIdOrcategoryParentId(id).stream()
					.map(Category::getCategoryId).collect(Collectors.toList());
			return lc;
		} catch (Exception e) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Cannot get  category: " + e.getMessage());
		}
	}

	public Category findOne(Long id) {
		return categoryRepository.getOne(id);
	}

	public Category update(Category category) {
		Category oldCategory = categoryRepository.getOne(category.getCategoryId());
		// TODO Auto-generated method stub
		oldCategory.setCategoryName(category.getCategoryName());
		oldCategory.setCategoryParentId(category.getCategoryParentId());
		oldCategory.setDescription(category.getDescription());
		return categoryRepository.save(oldCategory);
	}

	public Category create(Category category) {
		// TODO Auto-generated method stub
		return categoryRepository.save(category);
	}

	public Category delete(Long id) {
		Category category = categoryRepository.getOne(id);
		category.setDeleted(true);
		// TODO Auto-generated method stub
		return categoryRepository.save(category);
	}

	public Page<Category> findByNameContaining(String q, Pageable pageable) {
		return categoryRepository.findBycategoryNameContainingAndDeleted(q, false, pageable);
	}
	
	public List<Category> listCategoryParent(Long categoryId) {
		List<Category> response = null;
		response = categoryRepository.listCategoryParent(categoryId);
		return response;
	}
}
