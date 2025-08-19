package com.web.repository.web;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.entity.OrderDetail;
import com.web.entity.OrderEntity;
import com.web.entity.ProductEntity;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
	Optional<OrderDetail> findByOrderAndProduct(OrderEntity orderEntity, ProductEntity productEntity);
	List<OrderDetail> findByOrderId(Long id);
}
