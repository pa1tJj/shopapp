package com.web.model.dto;

import com.web.entity.ProductEntity;

import lombok.Data;

@Data
public class CartProductDTO {

	private ProductEntity productEntity;
	private Long quantity;
	private String priceTotal;
}
