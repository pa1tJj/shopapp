package com.web.model.dto;

import lombok.Data;

@Data
public class OrderDetailsDTO {

	private Long quantity;
	private Long unitPrice;
	private Long subTotal;
}
