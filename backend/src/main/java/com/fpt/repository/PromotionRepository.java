package com.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.entity.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
      Promotion findByproductId(Long id);
}
