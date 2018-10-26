package com.fpt.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpt.entity.Product;
import com.fpt.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryService categoryService;

	public Product delete(Long id) {
		Product p = productRepository.getOne(id);
		p.setDeleted(true);
		return productRepository.save(p);
	}

	public Page<Product> findProducts(Long id, String q, Pageable pageable) throws Exception {
		List<Long> cateid = categoryService.findCategoryId(id);
		if (cateid != null || !cateid.isEmpty()) {
			return productRepository.findProductByCategoryIdAndDeleted(cateid, false, pageable);
		}
//		if (id != null) {
//			return productRepository.findProductByCategoryIdAndDeleted(id, false, pageable);
//		}
		// TODO Auto-generated method stub
		return productRepository.findByProductNameContainingAndDeleted(q, false, pageable);
	}

	public Product findOne(Long id) {
		// TODO Auto-generated method stub
		return productRepository.getOne(id);
	}

	public Product create(Product product) {
		// TODO Auto-generated method stub
		product.setCreatedDate(new Date());
		return productRepository.save(product);

	}

	public Product update(Product product) {
		Product p = productRepository.getOne(product.getProductId());
		product.setCreatedDate(p.getCreatedDate());

		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

//	public Page<Product> findProductByCategory(Long id, Pageable pageable) {
//		Category cate = categoryRepository.findBycategoryNameAndDeleted(q, false);
//		Long id = cate.getCategoryId();
//
//		return productRepository.findProductByCategoryIdAndDeleted(id, false, pageable);
//	}

}
