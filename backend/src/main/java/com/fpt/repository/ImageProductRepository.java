package com.fpt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.entity.Image;

public interface ImageProductRepository extends JpaRepository<Image, Long> {
	public List<Image> findByProductId(Long id);

	@Transactional
	public void deleteAllByImageIdNotInAndProductId(List<Long> ids, Long pid);

	public List<Image> findAllByImageIdNotInAndProductId(List<Long> imgIds, Long productId);
}
