package com.fpt.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpt.entity.Product;
import com.fpt.entity.Promotion;
import com.fpt.repository.PromotionRepository;

@Service
public class PromotionService {
	@Autowired
	private PromotionRepository promotionRepository;
	@Autowired
	private ProductService productService;

	public void create(List<Promotion> lp) {

		for (Promotion p : lp) {
			Promotion promotion = new Promotion();
			promotion.setCreateDate(p.getCreateDate());
			promotion.setDiscount(p.getDiscount());
			promotion.setDisountCode(p.getDisountCode());
			promotion.setExprieDate(p.getExprieDate());
			promotion.setProductId(p.getProductId());
			promotionRepository.save(promotion);
		}

	}

	public Promotion getPromoton(Long id) {
		return promotionRepository.findByproductId(id);
	}

	public Promotion getOnePromotion(Long id) {
		return promotionRepository.getOne(id);
	}

	public Integer getOneByProductId(Long productId) {
		return promotionRepository.getPromotionByProductIdAndDate(productId, new Date().getTime());
	}

	public Page<Promotion> findAll(String q,Pageable pageable) throws Exception {
		promotionRepository.removeOutDatePromotions(new Date().getTime());
		Page<Product> ps = productService.findProducts(null, q, null,null, false, pageable);
		List<Long> ids = new ArrayList<>();
		for (Product product : ps.getContent()) {
			ids.add(product.getProductId());
		}
		return promotionRepository.findAllByProductIdIn(ids, pageable);
	}

	public Promotion update(Promotion p) {
		Promotion op = promotionRepository.getOne(p.getPromotionId());
		op.setCreateDate(1L);
		op.setExprieDate(2L);
		op.setDiscount(p.getDiscount());
		op.setDisountCode(p.getDisountCode());
		op.setProductId(p.getProductId());
		return op;
	}

	public Promotion getpromotion(String code) {
		return promotionRepository.findBydiscountCode(code);
	}

	public void delete(Long id) {
		Promotion p = promotionRepository.getOne(id);
		System.out.println(p);
		promotionRepository.delete(p);
	}
}
