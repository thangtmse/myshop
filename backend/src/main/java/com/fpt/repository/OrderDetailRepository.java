package com.fpt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

	List<OrderDetail> findOrderDetailByOrderId(Long id);

}
