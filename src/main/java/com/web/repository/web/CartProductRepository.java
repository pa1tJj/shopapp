package com.web.repository.web;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.entity.CartEntity;
import com.web.entity.CartProductEntity;
import com.web.entity.ProductEntity;
import com.web.model.request.OrderRequest;

public interface CartProductRepository extends JpaRepository<CartProductEntity, Long>{
	Optional<CartProductEntity> findByCartAndProduct(CartEntity cartEntity, ProductEntity productEntity);
	List<CartProductEntity> findByCart(CartEntity cartEntity);
	List<CartProductEntity> findByIdIn(List<Long> ids);
}
