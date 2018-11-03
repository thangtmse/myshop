package com.fpt.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
		return productRepository.save(product);

	}

	public Product update(Product product) {
		Product p = productRepository.getOne(product.getProductId());
		product.setCreatedDate(p.getCreatedDate());

		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	// public Page<Product> findProductByCategory(Long id, Pageable pageable) {
	// Category cate = categoryRepository.findBycategoryNameAndDeleted(q, false);
	// Long id = cate.getCategoryId();
	//
	// return productRepository.findProductByCategoryIdAndDeleted(id, false,
	// pageable);
	// }

}
