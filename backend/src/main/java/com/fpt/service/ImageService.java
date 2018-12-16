package com.fpt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.entity.Image;
import com.fpt.repository.ImageProductRepository;

@Service
public class ImageService {

	@Autowired
	private ImageProductRepository imageProductRepository;

	public List<Image> getListImage(Long id) {
		return imageProductRepository.findByProductId(id);
	}

	public Image getById(Long id) {
		return imageProductRepository.getOne(id);
	}
	public Image create(Image image) {
		return imageProductRepository.save(image);
	}
	public void deleteIdNotIn(List<Long> ids, Long pid) {
		imageProductRepository.deleteAllByImageIdNotInAndProductId(ids, pid);
	}

	public List<Image> findIdNotIn(List<Long> imgIds, Long productId) {
		return imageProductRepository.findAllByImageIdNotInAndProductId(imgIds, productId);
	}
}
