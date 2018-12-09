package com.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpt.entity.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

	@Query("select p.discount from Promotion p where p.productId =:productId and p.createDate <= :date and p.exprieDate >=:date ")
	public Integer getPromotionByProductIdAndDate(@Param("productId") Long productId, @Param("date") Long date);
      Promotion findByproductId(Long id);
}
