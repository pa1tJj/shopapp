package com.web.repository.custom;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web.entity.ProductEntity;
import com.web.model.request.ProductRequest;

public interface ProductRepositoryCustom {
	public List<ProductEntity> findAll(ProductRequest productRequest, Pageable pageable);
}
