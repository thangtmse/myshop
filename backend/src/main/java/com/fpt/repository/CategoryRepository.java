package com.fpt.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	public Page<Category> findBycategoryNameContainingAndDeleted(String q, Boolean deleted, Pageable pageable);

	// public Category findBycategoryNameAndDeleted(String q, Boolean deleted);
	public Category findBycategoryIdAndDeleted(Long id, Boolean deleted);

	@Query("select c from Category c where c.categoryId = :id or c.categoryParentId = :id")
	public List<Category> findBycategoryIdOrcategoryParentId(@Param("id") Long id);
}
