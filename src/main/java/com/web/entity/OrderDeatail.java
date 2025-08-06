package com.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "order_detail")
public class OrderDeatail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "quantity")
	private Long quantity;
	
	@Column(name = "unit_price")
	private Long unitPrice;
	
	@Column(name = "subtotal")
	private Long subTotal;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private OrderEntity order;
	
	@ManyToOne
	@JoinColumn(name = "produict_id")
	private ProductEntity product;
}
