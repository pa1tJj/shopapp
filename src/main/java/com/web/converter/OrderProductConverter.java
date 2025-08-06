package com.web.converter;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.entity.CartEntity;
import com.web.model.request.CartRequest;

@Component
public class OrderProductConverter {
	@Autowired
	private ModelMapper mapper;
	
	public CartEntity toCartEntity(CartRequest cartRequest) {
		CartEntity cartEntity = mapper.map(cartRequest, CartEntity.class);
		cartEntity.getUser().setId(cartRequest.getUserId());
		return cartEntity;
	}
}
