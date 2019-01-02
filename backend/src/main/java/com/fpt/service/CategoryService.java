package com.fpt.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	@Value("${myshop.data.dir}")
	private String imageDir;

	public List<Category> findAll() {
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
//		oldCategory.setImageurl(category.getImageurl());
		String[] strings = category.getImageurl().split(",");
		if (strings.length >= 2) {
			// delete file cu
			byte[] imageByte = Base64.decodeBase64(strings[1]);
			String fileName = imageDir + "cat-" + new Date().getTime() + "" + new Random().nextInt() + ".jpg";
			File file = new File(fileName);
			try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
				outputStream.write(imageByte);
				File oldFile = new File(oldCategory.getImageurl());
				oldFile.delete();
				oldCategory.setImageurl(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return categoryRepository.save(oldCategory);
	}

	public Category create(Category category) {
		// TODO Auto-generated method stub
		String[] strings = category.getImageurl().split(",");
		if (strings.length >= 2) {
			// delete file cu
			byte[] imageByte = Base64.decodeBase64(strings[1]);
			String fileName = imageDir + "cat-" + new Date().getTime() + "" + new Random().nextInt() + ".jpg";
			File file = new File(fileName);
			try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
				outputStream.write(imageByte);
				category.setImageurl(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return categoryRepository.save(category);
	}

	// "../../../images/cat-
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
