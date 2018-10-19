package com.fpt.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
