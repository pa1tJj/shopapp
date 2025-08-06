package com.web.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "order")
@Entity
public class OrderEntity extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "total_amout")
	private Long total_amout;
	@Column(name = "shipping_address")
	private String shippingAddress;
	@Column(name = "payment_method")
	private String paymentMethod;
	@Column(name = "order_status")
	private String orderStatus;
	@Column(name = "notes")
	private String note;
	
	@OneToMany(mappedBy = "order")
	private List<OrderDeatail> orderDeatails = new ArrayList<>();
	
	@ManyToOne
	private UserEntity user;
}
