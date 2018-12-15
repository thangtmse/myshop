package com.fpt.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpt.entity.Category;


@Repository("categoryRepository ")
public interface CategoryRepository extends JpaRepository<Category, Long> {
	public Page<Category> findBycategoryNameContainingAndDeleted(String q, Boolean deleted, Pageable pageable);

	// public Category findBycategoryNameAndDeleted(String q, Boolean deleted);
	public Category findBycategoryIdAndDeleted(Long id, Boolean deleted);

	@Query("select c from Category c where c.categoryId = :id or c.categoryParentId = :id")
	public List<Category> findBycategoryIdOrcategoryParentId(@Param("id") Long id);
	
	@Query("Select c from Category c where (c.categoryId != :categoryId and (c.categoryParentId is null or c.categoryParentId=0))")
	public List<Category> listCategoryParent(@Param("categoryId")Long categoryId);
}
