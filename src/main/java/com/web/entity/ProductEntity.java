package com.web.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
public class ProductEntity extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private Long price;
	
	@Column(name = "discount_price")
	private Long discountPrice;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "stock_quantity")
	private Long stockQuantity;
	
	@Column(name = "weight")
	private Long weight;
	
	@Column(name = "flavor")
	private String flavor;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "is_featured")
	private Boolean isFeatured;
	
	@OneToMany(mappedBy = "product", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<CartProductEntity> cartItems = new ArrayList<>();
	
	@OneToMany(mappedBy = "product", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private List<OrderDetail> orderDeatails = new ArrayList<>();
	
}
