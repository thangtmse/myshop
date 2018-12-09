package com.fpt.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.entity.Promotion;
import com.fpt.repository.PromotionRepository;

@Service
public class PromotionService {
	@Autowired
	private PromotionRepository promotionRepository;

	public Promotion create(Promotion p) {
		p.setCreateDate(new Date().getTime());
		p.setExprieDate(new Date().getTime());
		return promotionRepository.save(p);
	}

	public Promotion getPromoton(Long id) {
		return promotionRepository.findByproductId(id);
	}

	public Integer getOneByProductId(Long productId) {
		return promotionRepository.getPromotionByProductIdAndDate(productId, new Date().getTime());
	}
}
