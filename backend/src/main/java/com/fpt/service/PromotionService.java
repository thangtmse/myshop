package com.fpt.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpt.entity.Promotion;
import com.fpt.repository.PromotionRepository;

@Service
public class PromotionService {
	@Autowired
	private PromotionRepository promotionRepository;

	public Promotion create(Promotion p) {
		p.setCreateDate(1L);
		p.setExprieDate(2L);
		return promotionRepository.save(p);
	}

	public Promotion getPromoton(Long id) {
		return promotionRepository.findByproductId(id);
	}

	public Integer getOneByProductId(Long productId) {
		return promotionRepository.getPromotionByProductIdAndDate(productId, new Date().getTime());
	}
	public Page<Promotion> findAll(Pageable pageable){
		return promotionRepository.findAll(pageable);
	}
	public Promotion update(Promotion p) {
		Promotion op =promotionRepository.getOne(p.getPromotionId());
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
}
