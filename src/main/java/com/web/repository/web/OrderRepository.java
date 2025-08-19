package com.web.repository.web;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	Optional<OrderEntity> findByIdAndUserId(Long id, Long userId);
	OrderEntity findTopByOrderByIdDesc();
	List<OrderEntity> findByUserId(Long userId);
}
