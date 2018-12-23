package com.fpt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpt.dto.request.OrderDTO;
import com.fpt.entity.Order;
import com.fpt.entity.OrderDetail;
import com.fpt.mapper.OrderDetailMapper;
import com.fpt.mapper.OrderMapper;
import com.fpt.service.OrderService;

@Controller
@RequestMapping("/api/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderDetailMapper orderDetailMapper;

	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<Order> create(@RequestBody OrderDTO orderdto) {
		return new ResponseEntity<>(orderService.create(orderdto), HttpStatus.OK);
	}

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<?> getall(Pageable pageable,
			@RequestParam(value = "search", defaultValue = "", required = false) String phone,
			@RequestParam(value = "status", defaultValue = "", required = false) String status) {
		Page<Order> list = orderService.getall(phone, status, pageable);
		return new ResponseEntity<>(orderMapper.toOrderResponse(list), HttpStatus.OK);
	}

	@RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findOrdersByUserId(@PathVariable("id") Long id) {
		List<Order> pd = orderService.findOrder(id);
		return new ResponseEntity<>(orderMapper.toOrderResponse(pd), HttpStatus.OK);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		Order order = orderService.getById(id);
		return new ResponseEntity<>(orderMapper.toOrderResponse(order), HttpStatus.OK);
	}

	@RequestMapping(path = "{id}/detail", method = RequestMethod.GET)
	public ResponseEntity<?> findOrderDetailsByOrderId(@PathVariable("id") Long id) {
		List<OrderDetail> orderDetail = orderService.findOrderDetailsByOrderId(id);
		return new ResponseEntity<>(orderDetailMapper.toOrderDetailResponse(orderDetail), HttpStatus.OK);
	}

	@RequestMapping(path = "{id}/status", method = RequestMethod.POST)
	public ResponseEntity<?> changeStatus(@PathVariable("id") Long id, String status) {
		Order order = orderService.changeStatus(id, status);
		return new ResponseEntity<>(orderMapper.toOrderResponse(order), HttpStatus.OK);
	}

	@RequestMapping(path = "{id}/orderDetail", method = RequestMethod.GET)
	public ResponseEntity<?> findOrderDetailByOrderId(@PathVariable("id") Long id) {
		List<OrderDetail> orderDetails = orderService.findOrderDetailsByOrderId(id);
		return new ResponseEntity<>(orderDetailMapper.toListODResponse(orderDetails), HttpStatus.OK);
	}

	@RequestMapping(path = "{id}/review", method = RequestMethod.GET)
	public ResponseEntity<?> getReviewOrder(@PathVariable("id") Long userId) {

		return new ResponseEntity<>(orderService.getReviewOrder(userId), HttpStatus.OK);
	}

}
