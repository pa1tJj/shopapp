package com.web.model.request;

import lombok.Data;

@Data
public class CartRequest {
	private Long userId;
	private Long productId;
	private Long quantity;
}
