package com.web.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductDTO {
	private Long id;
	private String name;
	private String category;
	private String description;
	private Long price;
	private Long discountPrice;
	private MultipartFile imageUrl;
	private Long stockQuantity;
	private Long weight;
	private String flavor;
	private String brand;
	private String isFeatured;

}
