package com.fpt.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.fpt.dto.request.ProductRequest;
import com.fpt.dto.response.ProductResponse;
import com.fpt.entity.Product;
import com.fpt.service.CategoryService;
import com.fpt.service.PromotionService;
import com.fpt.service.SupplierService;

@Component
public class ProductMapper {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PromotionService promotionService;
	@Autowired
	private SupplierService supplierService;

	public Product toProduct(ProductRequest pr) {
		if (pr == null) {
			return null;
		}
		Product p = new Product();
		p.setProductId(pr.getProductId());
		p.setProductName(pr.getProductName());
		p.setCategoryId(pr.getCategoryID());
		p.setDescription(pr.getDescription());
		p.setPriceIn(pr.getPriceIn());
		p.setPriceOut(pr.getPriceOut());
		p.setQuantity(pr.getQuantity());
		p.setSupplierId(pr.getSupplierId());
		p.setImages(pr.getImages());
		p.setDeleted(false);
		return p;
	}

	public List<Product> toProduct(List<ProductRequest> prl) {
		if (prl == null) {
			return null;
		}
		List<Product> pl = new ArrayList<Product>();
		for (ProductRequest pr : prl) {
			pl.add(toProduct(pr));
		}
		return pl;
	}

	public Page<Product> toProduct(Page<ProductRequest> page) {
		if (page == null)
			return null;
		List<Product> pl = toProduct(page.getContent());
		Page<Product> reponse = new PageImpl<Product>(pl, page.getPageable(), page.getTotalElements());
		return reponse;
	}

	public ProductResponse toProductResponse(Product p) {
		if (p == null) {
			return null;
		}
		ProductResponse pr = new ProductResponse();
		pr.setCategory(categoryService.findOne(p.getCategoryId()));
		pr.setProductId(p.getProductId());
		pr.setProductName(p.getProductName());
		pr.setCreatedDate(p.getCreatedDate());
		pr.setDescription(p.getDescription());
		pr.setPriceIn(p.getPriceIn());
		pr.setPriceOut(p.getPriceOut());
		pr.setQuantity(p.getQuantity());
		pr.setDiscount(promotionService.getOneByProductId(p.getProductId()));
		pr.setSupplier(supplierService.findOne(p.getSupplierId()));
		pr.setImages(p.getImages());
		return pr;
	}

	public List<ProductResponse> toProductResponse(List<Product> prl) {
		if (prl == null) {
			return null;
		}
		List<ProductResponse> pl = new ArrayList<ProductResponse>();
		for (Product pr : prl) {
			pl.add(toProductResponse(pr));
		}
		return pl;
	}

	public Page<ProductResponse> toProductResponse(Page<Product> page) {
		if (page == null)
			return null;
		List<ProductResponse> pl = toProductResponse(page.getContent());
		Page<ProductResponse> reponse = new PageImpl<ProductResponse>(pl, page.getPageable(), page.getTotalElements());
		return reponse;
	}
}
