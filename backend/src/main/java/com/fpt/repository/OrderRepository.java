package com.fpt.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findOrderByUserIdOrderByAddAtDesc(Long id);
    List<Order> findOrderByphone(String phone);
	Page<Order> findByPhoneAndStatusContaining(String phone,String status, Pageable pageable);
	Page<Order> findByPhoneContainingAndStatusContaining(String phone, String status, Pageable pageable);
}
