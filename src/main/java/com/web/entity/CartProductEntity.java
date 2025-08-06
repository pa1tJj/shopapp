package com.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "cart_product")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class CartProductEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private CartEntity cart;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity product;
	
	@Column(name = "quantity")
	private Long quantity;
}
