package com.web.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.entity.ProductEntity;
import com.web.enums.CategoryType;
import com.web.model.response.ProductResponse;

@Component
public class ProductConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public ProductResponse toProductResponse (ProductEntity productEntity) {
		ProductResponse productResponse = modelMapper.map(productEntity, ProductResponse.class);
		productResponse.setCategory(CategoryType.valueOf(productEntity.getCategory()).getCategoryName());
		String status = productEntity.getIsFeatured()== true? "Đang bán" : "Ngừng bán";
		productResponse.setIsFeatured(status);
		return productResponse;
	}
}
