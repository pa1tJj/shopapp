package com.web.model.response;

import com.web.model.dto.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductResponse extends AbstractDTO {

	private Long id;
	private String name;
	private String category;
	private String description;
	private String price;
	private String discountPrice;
	private String imageUrl;
	private Long stockQuantity;
	private Long weight;
	private String flavor;
	private String brand;
	private String isFeatured;

}
