package com.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fpt.dto.request.OrderDTO;
import com.fpt.entity.Order;
import com.fpt.service.OrderService;

@Controller
@RequestMapping("/api/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<Order> create(@RequestBody OrderDTO orderdto) {
		return new ResponseEntity<>(orderService.create(orderdto), HttpStatus.OK);
	}

	@RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findOrdersByUserId(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(orderService.findOrdersByUserId(id), HttpStatus.OK);
	}

	@RequestMapping(path = "{id}/detail", method = RequestMethod.GET)
	public ResponseEntity<?> findOrderDetailsByOrderId(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(orderService.findOrderDetailsByOrderId(id), HttpStatus.OK);
	}
}
