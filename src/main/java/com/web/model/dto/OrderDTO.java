package com.web.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.web.entity.OrderDetail;
import com.web.model.response.OrderResponse;

import lombok.Data;

@Data
public class OrderDTO extends AbstractDTO {
	private OrderResponse orderResponse;
	private Long userId;
	private Long id;
	private String recipentName;
	private String recipentPhone;
	private String shippingAddress;
	private Long totalAmount = orderResponse.totalQuantity;
	private String paymentMethod;
	private String orderStatus;
	private String notes;
	private List<OrderDetail> orderDeatails = new ArrayList<OrderDetail>();
}
