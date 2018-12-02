package com.fpt.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.dto.request.OrderDTO;
import com.fpt.entity.Order;
import com.fpt.entity.OrderDetail;
import com.fpt.entity.Product;
import com.fpt.repository.OrderDetailRepository;
import com.fpt.repository.OrderRepository;
import com.fpt.repository.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private ProductRepository productRepository;

	public Order create(OrderDTO orderdto) {
		Order order = new Order();
		order.setAddress(orderdto.getAddress());
		order.setCity(orderdto.getCity());
		order.setDistrict(orderdto.getDistrict());
		order.setMessage(orderdto.getMessage());
		order.setPhone(orderdto.getPhone());
		order.setUserId(orderdto.getUserId());
		order.setStatus("PENDING");
		order.setAddAt(new Date().getTime());
		order.setDeliveryMethod(orderdto.getDeliveryMethod());
		order.setAmount(orderdto.getOrderDetails().size());
		double totalPrice = 0l;
		order = orderRepository.save(order);
		for (OrderDetail orderDetail : orderdto.getOrderDetails()) {
			Product product = productRepository.getOne(orderDetail.getProductId());
			orderDetail.setOrderId(order.getOrderId());
			orderDetail.setPrice(product.getPriceOut());
			orderDetail.setTotalPrice(product.getPriceOut() * orderDetail.getQuantity());
			totalPrice += orderDetail.getTotalPrice();
		}
		order.setTotalPrice(totalPrice);
		orderDetailRepository.saveAll(orderdto.getOrderDetails());
		// TODO Auto-generated method stub
		return orderRepository.save(order);
	}

	public List<Order> findOrdersByUserId(Integer id) {
		// TODO Auto-generated method stub
		return orderRepository.findOrderByUserIdOrderByAddAtDesc(id);
	}

	public List<OrderDetail> findOrderDetailsByOrderId(Integer id) {
		// TODO Auto-generated method stub
		return orderDetailRepository.findOrderDetailByOrderId(id);
	}

}
