package com.fpt.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.dto.response.RangOfProductPrice;
import com.fpt.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public Page<Product> findByProductNameContainingAndDeleted(String name, Boolean deleted, Pageable pageable);

	public Page<Product> findByProductNameContainingAndCategoryIdInAndDeleted(String name, List<Long> id,
			Boolean deleted, Pageable pageable);

	@Query("from Product p where p.productName like :name and (p.categoryId in (:id) or :other = true) and p.deleted =:deleted "
			+ "and p.priceOut > :minPrice and p.priceOut < :maxPrice")
	public Page<Product> findProductByCondition(@Param("name") String name, @Param("id") List<Long> id,
			@Param("other") Boolean other, @Param("deleted") Boolean deleted, @Param("minPrice") Double minPrice,
			@Param("maxPrice") Double maxPrice, Pageable pageable);

	@Query(value = " select max(p.priceOut) as maxPrice, min(p.priceOut) as minPrice from Product p")
	public RangOfProductPrice getRankOfPrice();

}
