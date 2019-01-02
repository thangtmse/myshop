package com.fpt.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.dto.response.StatiticDTO;
import com.fpt.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findOrderByUserIdOrderByAddAtDesc(Long id);

	List<Order> findOrderByphone(String phone);

	Page<Order> findByPhoneAndStatusContaining(String phone, String status, Pageable pageable);
	
	Page<Order> findByPhoneContainingAndStatusContainingOrderByAddAtAsc(String phone, String status, Pageable pageable);

	@Query("select count(o.orderId) from Order o where o.status =:status")
	Integer getAmountOfProductByStatus(@Param("status") String status);

	@Query("select count(o.orderId) from Order o where o.status =:status and o.userId =:userId")
	Integer getAmountOfProductByStatus(@Param("status") String string, @Param("userId") Long userId);

	@Query(value = " SELECT from_unixtime((ord.`add_at`/1000), :pattern ) AS dow, from_unixtime((ord.`add_at`/1000), :labels ) AS labels, count(ord.order_id) AS sl, SUM(total_price) AS total\r\n"
			+ "	FROM `order` AS ord WHERE ord.status='Hoàn thành' and ord.`add_at` >= (UNIX_TIMESTAMP()*1000)-(86400000*:numberofday) group by dow order by ord.`add_at`;", nativeQuery = true)
	public List<StatiticDTO> getAllStatitc(@Param("pattern") String pattern, @Param("labels") String labels,
			@Param("numberofday") Integer days);
}
