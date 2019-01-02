package com.fpt.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpt.dto.request.OrderDTO;
import com.fpt.dto.response.ChartDTO;
import com.fpt.dto.response.ReviewOrderResponse;
import com.fpt.dto.response.StatiticDTO;
import com.fpt.entity.Order;
import com.fpt.entity.OrderDetail;
import com.fpt.entity.Product;
import com.fpt.repository.OrderDetailRepository;
import com.fpt.repository.OrderRepository;
import com.fpt.repository.ProductRepository;
import com.fpt.repository.UserRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PromotionService promotionService;

	public Order create(OrderDTO orderdto) {
		Order order = new Order();
		order.setAddress(orderdto.getAddress());
		order.setCity(orderdto.getCity());
		order.setDistrict(orderdto.getDistrict());
		order.setMessage(orderdto.getMessage());
		order.setPhone(orderdto.getPhone());
		order.setUserId(orderdto.getUserId());
		order.setStatus("Chờ xử lí");
		order.setAddAt(new Date().getTime());
		order.setDeliveryMethod(orderdto.getDeliveryMethod());
		order.setAmount(orderdto.getOrderDetails().size());
		double totalPrice = 0l;
		order = orderRepository.save(order);
		for (OrderDetail orderDetail : orderdto.getOrderDetails()) {
			Product product = productRepository.getOne(orderDetail.getProductId());
			Integer discount = promotionService.getOneByProductId(product.getProductId());

			orderDetail.setOrderId(order.getOrderId());
			if (discount != null) {
				orderDetail.setPrice(product.getPriceOut() * ((double) (100 - discount) / 100));
			} else {
				orderDetail.setPrice(product.getPriceOut());
			}
			orderDetail.setTotalPrice(orderDetail.getPrice() * orderDetail.getQuantity());
			totalPrice += orderDetail.getTotalPrice();

			Integer quantity = product.getQuantity() - orderDetail.getQuantity();
			product.setQuantity(quantity);
			productRepository.save(product);

		}
		order.setTotalPrice(totalPrice);
		orderDetailRepository.saveAll(orderdto.getOrderDetails());
		// TODO Auto-generated method stub
		System.out.println(orderRepository.save(order).toString());
		return orderRepository.save(order);
		
	}

	public Page<Order> getall(Pageable pageable) {
		return orderRepository.findAll(pageable);
	}

	public List<Order> findOrder(Long id) {
		// TODO Auto-generated method stub

		return orderRepository.findOrderByUserIdOrderByAddAtDesc(id);
	}

	public List<OrderDetail> findOrderDetailsByOrderId(Long id) {
		// TODO Auto-generated method stub
		return orderDetailRepository.findOrderDetailByOrderId(id);
	}

	public Page<Order> getall(String phone, String status, Pageable pageable) {
		return orderRepository.findByPhoneContainingAndStatusContainingOrderByAddAtAsc(phone, status, pageable);
	}

	public Order getById(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.getOne(id);
	}

	public Order changeStatus(Long id, String status) {
		Order order = getById(id);
		order.setStatus(status);
		if (status.equals("Hủy")) {
			List<OrderDetail> orderDetails = orderDetailRepository.findOrderDetailByOrderId(id);
			for (OrderDetail orderDetail : orderDetails) {
				Product product = productRepository.getOne(orderDetail.getProductId());
				Integer quantity = product.getQuantity() + orderDetail.getQuantity();
				product.setQuantity(quantity);
				productRepository.save(product);
			}
		}
		order = orderRepository.save(order);
		return order;
	}

	public Object getReviewOrder(Long userId) {
		ReviewOrderResponse orderResponse = new ReviewOrderResponse();
		orderResponse.setDone(orderRepository.getAmountOfProductByStatus("Hoàn thành", userId));
		orderResponse.setDelivering(orderRepository.getAmountOfProductByStatus("Đang giao hàng", userId));
		orderResponse.setProcessing(orderRepository.getAmountOfProductByStatus("Đang xử lý", userId));
		orderResponse.setCancel(orderRepository.getAmountOfProductByStatus("Hủy", userId));
		return orderResponse;
	}

	public ChartDTO getStaticsLine(int type) {
		ChartDTO chartDTO = new ChartDTO();
		List<StatiticDTO> list = null;
		switch (type) {
		case 0: chartDTO.setLabels(initTimeLine("dd-MM-yyyy",Calendar.DAY_OF_MONTH,-9));
				list = orderRepository.getAllStatitc("%Y/%m/%d","%d-%m-%Y", 7);
			break;
		case 1: chartDTO.setLabels(initTimeLine("MM-yyyy",Calendar.MONTH,-13));
				list = orderRepository.getAllStatitc("%Y/%m","%m-%Y", 365);
			break;
		case 2: chartDTO.setLabels(initTimeLine("yyyy",Calendar.YEAR,-10));
				list = orderRepository.getAllStatitc("%Y","%Y", 3650);
			break;
		default: return null;
		}
		if(list==null) return null;
		
		chartDTO.setDatasets(new ArrayList<List>());
		
		List<Long> listOrderNum = new ArrayList();
		List<Float> listIncome = new ArrayList();
		
		int index =0;
		int orderTotal=0;
		float incomeTotal=0F;
		for (String labels : chartDTO.getLabels()) {
			StatiticDTO statiticDTO = index < list.size()?list.get(index):null;
			if(statiticDTO!=null && statiticDTO.getlabels().equals(labels)) {
				orderTotal+=statiticDTO.getSl();
				incomeTotal+=statiticDTO.getTotal();
				listOrderNum.add(statiticDTO.getSl());
				listIncome.add(statiticDTO.getTotal());
				index++;
			}else {
				listOrderNum.add(0L);
				listIncome.add(0F);
			}
		}
		
		chartDTO.getDatasets().add(listOrderNum);
		chartDTO.getDatasets().add(listIncome);
		chartDTO.setOrderTotal(orderTotal);
		chartDTO.setIncomeTotal(incomeTotal);
		return chartDTO;
	}

	private List<String> initTimeLine(String pattern,int type,int numberdate){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		List<String> list = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.add(type, numberdate+1);
		
		for (int i = 1; i < -numberdate; i++) {
			calendar.add(type, 1);
			list.add(sdf.format(calendar.getTime()));
		}
		return list;
	}
}
