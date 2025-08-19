package com.web.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDTOs extends AbstractDTO{
	private Long id;
    private String recipentName;
    private String recipentPhone;
    private String shippingAddress;
    private String paymentMethod;
    private String orderStatus;
    private Long totalAmount;
    private List<OrderDetailDTO> orderDetails;

}
