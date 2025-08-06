package com.web.repository.web;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.entity.CartEntity;
import com.web.entity.CartProductEntity;
import com.web.entity.ProductEntity;
import com.web.entity.UserEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
	CartEntity findByUser(UserEntity userEntity);
	CartEntity findByUserId(Long id);
}
