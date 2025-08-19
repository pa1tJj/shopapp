package com.web.service.web;

import java.util.List;

import com.web.model.dto.OrderDTO;
import com.web.model.dto.OrderDTOs;
import com.web.model.response.PaymentSuccessResponse;

public interface OrderService {
	public void getPlaceOrder(OrderDTO orderDTO);
	public PaymentSuccessResponse getResponse(Long userId);
	public List<OrderDTOs> getOrdersByUser(Long userId);
}
