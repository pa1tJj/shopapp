package com.web.model.response;

import lombok.Data;

@Data
public class PaymentSuccessResponse {
	private String orderStatus;
	private String paymentMethod;
	private String price;
	private String discountPrice;
	private String totalPrice;
	private String recipientName;
	private String recipientPhone;
	private String shippingAddress;
}
