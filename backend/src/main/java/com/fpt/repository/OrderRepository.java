package com.fpt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findOrderByUserIdOrderByAddAtDesc(Integer id);

}
