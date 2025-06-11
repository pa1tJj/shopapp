package com.web.model.request;

import org.springframework.web.multipart.MultipartFile;

import com.web.model.dto.AbstractDTO;

public class ProductRequest extends AbstractDTO{
	private String name;
	private Long priceFrom;
	private Long priceTo;
	private Long stockQuantity;
	private String category;
	private String isFeatured;
	private MultipartFile image;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPriceFrom() {
		return priceFrom;
	}
	public void setPriceFrom(Long priceFrom) {
		this.priceFrom = priceFrom;
	}
	public Long getPriceTo() {
		return priceTo;
	}
	public void setPriceTo(Long priceTo) {
		this.priceTo = priceTo;
	}
	
	public Long getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(Long stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getIsFeatured() {
		return isFeatured;
	}
	public void setIsFeatured(String isFeatured) {
		this.isFeatured = isFeatured;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	
	
}
