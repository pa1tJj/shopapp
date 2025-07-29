package com.web.repository.custom;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web.entity.ProductEntity;
import com.web.model.dto.ProductDTO;
import com.web.model.request.ProductRequest;
import com.web.model.request.ProductSearchRequest;

public interface ProductRepositoryCustom {
	public List<ProductEntity> findAll(ProductRequest productRequest, Pageable pageable);

	public List<ProductEntity> findProducts(ProductSearchRequest productSearchRequest);
}
