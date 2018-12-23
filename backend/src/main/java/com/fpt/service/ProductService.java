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

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fpt.dto.response.RangOfProductPrice;
import com.fpt.entity.Image;
import com.fpt.entity.Product;
import com.fpt.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ImageService imageService;

	public Product delete(Long id) {
		Product p = productRepository.getOne(id);
		p.setDeleted(true);
		return productRepository.save(p);
	}

	public Page<Product> findProducts(Long id, String q, Double min, Double max, Pageable pageable) throws Exception {
		min = (min == null ? Double.MIN_VALUE : min);
		max = (max == null ? Double.MAX_VALUE : max);
		q = "%" + q.trim() + "%";
		List<Long> cateid = categoryService.findCategoryId(id);
		if (!CollectionUtils.isEmpty(cateid)) {
			return productRepository.findProductByCondition(q, cateid, false, false, min, max, pageable);
		}
		// if (id != null) {
		// return productRepository.findProductByCategoryIdAndDeleted(id, false,
		// pageable);
		// }
		// TODO Auto-generated method stub
		// return productRepository.findByProductNameContainingAndDeleted(q, false,
		// pageable);
		return productRepository.findProductByCondition(q, null, true, false, min, max, pageable);
	}

	public Product findOne(Long id) {
		// TODO Auto-generated method stub
		return productRepository.getOne(id);
	}

	public Product create(Product product) {
		// TODO Auto-generated method stub
		product.setCreatedDate(new Date());
		List<Image> imgs = product.getImages();
		product.setImages(null);
		product = productRepository.save(product);
		List<Image> imgs2 = new ArrayList<>();
		for (Image image : imgs) {
			if (image.getImageId() != null) {
				imgs2.add(image);
				continue;
			}
			String[] strings = image.getImageUrl().split(",");
			byte[] imageByte = Base64.decodeBase64(strings[1]);
			String directory = this.getClass().getClassLoader().getResource("").getPath() + "../../../images/";
			String fileName = directory + new Date().getTime() + "" + new Random().nextInt() + ".jpg";
			File file = new File(fileName);
			try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
				outputStream.write(imageByte);
			} catch (IOException e) {
				e.printStackTrace();
			}
			image = new Image();
			image.setImageUrl(fileName);
			image.setProductId(product.getProductId());
			imgs2.add(image);
		}
		product.setImages(imgs2);
		System.out.println(imgs2);
		product.setCreatedDate(new Date());
		return productRepository.save(product);

	}

	public Product update(Product product) {
		Product p = productRepository.getOne(product.getProductId());
		p.setCreatedDate(new Date());
		p.setCategoryId(product.getCategoryId());
		p.setDescription(product.getDescription());
		p.setPriceIn(product.getPriceIn());
		p.setPriceOut(product.getPriceOut());
		p.setProductName(product.getProductName());
		p.setQuantity(product.getQuantity());
		p.setSupplierId(product.getSupplierId());
		List<Image> imgs = product.getImages();
		p.setImages(null);
		List<Image> imgs2 = new ArrayList<>();
		List<Long> imgIds = new ArrayList<>();
		for (Image image : imgs) {
			if (image.getImageId() != null) {
				imgs2.add(imageService.getById(image.getImageId()));
				imgIds.add(image.getImageId());
				continue;
			}
			String[] strings = image.getImageUrl().split(",");
			byte[] imageByte = Base64.decodeBase64(strings[1]);
			String directory = this.getClass().getClassLoader().getResource("").getPath() + "../../../images/";
			String fileName = directory + new Date().getTime() + "" + new Random().nextInt() + ".jpg";
			File file = new File(fileName);
			try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
				outputStream.write(imageByte);
			} catch (IOException e) {
				e.printStackTrace();
			}
			image = new Image();
			image.setImageUrl(fileName);
			image.setProductId(p.getProductId());
			imgs2.add(image);
		}
		p.setImages(imgs2);
		List<Image> deleteImages = imageService.findIdNotIn(imgIds, p.getProductId());
		for (Image image : deleteImages) {
			File oldFile = new File(image.getImageUrl());
			oldFile.delete();
		}
		imageService.deleteIdNotIn(imgIds, p.getProductId());
		product = productRepository.save(p);
		System.out.println("=================");
		System.out.println(deleteImages);
		return productRepository.save(p);
	}

	public RangOfProductPrice getRankOfPrice() {

		return productRepository.getRankOfPrice();
	}

	// public Page<Product> findProductByCategory(Long id, Pageable pageable) {
	// Category cate = categoryRepository.findBycategoryNameAndDeleted(q, false);
	// Long id = cate.getCategoryId();
	//
	// return productRepository.findProductByCategoryIdAndDeleted(id, false,
	// pageable);
	// }

}
