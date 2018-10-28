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
}
