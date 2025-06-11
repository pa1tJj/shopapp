package com.web.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web.model.request.ProductRequest;
import com.web.model.response.ProductResponse;

public interface ProductService {
	public List<ProductResponse> findAll(ProductRequest productRequest, Pageable pageable);
}
