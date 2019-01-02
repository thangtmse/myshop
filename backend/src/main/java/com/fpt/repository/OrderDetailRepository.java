package com.fpt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

	@Query("from OrderDetail o where o.orderId=:id")
	List<OrderDetail> findOrderDetailByOrderId(@Param("id") Long id);
	
	@Query("SELECT o.productId FROM OrderDetail o group by productId order by sum(o.quantity) desc")
	List<Long> findProductIdHotTop5();
}
