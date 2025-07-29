package com.web.repository.web;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

}
