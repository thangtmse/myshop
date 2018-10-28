package com.fpt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.entity.Image;

public interface ImageProductRepository extends JpaRepository<Image, Long> {
	public List<Image> findByProductId(Long id);
}
