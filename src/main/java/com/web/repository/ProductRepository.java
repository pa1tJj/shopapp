package com.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.entity.ProductEntity;
import com.web.repository.custom.ProductRepositoryCustom;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, ProductRepositoryCustom{

}
