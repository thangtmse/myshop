package com.fpt.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fpt.dto.response.OrderDetailResponse;
import com.fpt.entity.OrderDetail;
import com.fpt.repository.ProductRepository;

@Component
public class OrderDetailMapper {
	@Autowired
	private ProductRepository productRepository;

	public OrderDetailResponse toOrderDetailResponse(OrderDetail o) {
		if (o == null) {
			return null;
		}
		OrderDetailResponse or = new OrderDetailResponse();
		or.setId(o.getId());
		or.setOrderId(o.getOrderId());
		or.setPrice(o.getPrice());
		or.setProduct(productRepository.getOne(o.getProductId()));
		or.setQuantity(o.getQuantity());
		or.setTotalprice(o.getTotalPrice());
		return or;
	}

	public List<OrderDetailResponse> toOrderDetailResponse(List<OrderDetail> orl) {
		if (orl == null) {
			return null;
		}
		List<OrderDetailResponse> pl = new ArrayList<OrderDetailResponse>();
		for (OrderDetail or : orl) {
			pl.add(toOrderDetailResponse(or));
		}
		return pl;
	}
}
