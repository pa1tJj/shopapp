package com.web.model.dto;

import lombok.Data;

@Data
public class OrderDTO {
	private String recipientName;
	private String recipientPhoe;
	private String shippingAddress;
	private Long totalAmount;
	private String paymentMethod;
	private String orderSStatus;
	private String notes;
	private Long quantity;
	private Long unitPrice;
	private Long subTotal;
}
