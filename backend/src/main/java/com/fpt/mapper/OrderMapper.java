package com.fpt.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.fpt.dto.response.OrderResponse;
import com.fpt.entity.Order;
import com.fpt.repository.UserRepository;

@Component
public class OrderMapper {
	@Autowired
	private UserRepository userRepository;
	public OrderResponse toOrderResponse(Order o) {
		if (o == null) {
			return null;
		}
		OrderResponse or = new OrderResponse();
		or.setOrderId(o.getOrderId());
		or.setAddress(o.getAddress());
		or.setAddAt(o.getAddAt());
		or.setAmount(o.getAmount());
		or.setCity(o.getCity());
		or.setDistrict(o.getDistrict());
		or.setMessage(o.getMessage());
		or.setPhone(o.getPhone());
		or.setStatus(o.getStatus());
		or.setUser(userRepository.getOne(o.getUserId()));
		or.setTotalPrice(o.getTotalPrice());
		return or;
	}

	public List<OrderResponse> toOrderResponse(List<Order> orl) {
		if (orl == null) {
			return null;
		}
		List<OrderResponse> pl = new ArrayList<OrderResponse>();
		for (Order or : orl) {
			pl.add(toOrderResponse(or));
		}
		return pl;
	}

	public Page<OrderResponse> toOrderResponse(Page<Order> page) {
		if (page == null)
			return null;
		List<OrderResponse> pl = toOrderResponse(page.getContent());
		Page<OrderResponse> reponse = new PageImpl<OrderResponse>(pl, page.getPageable(), page.getTotalElements());
		return reponse;
	}
	
}
