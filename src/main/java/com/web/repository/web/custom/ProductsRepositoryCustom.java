package com.web.repository.web.custom;

import java.util.List;

import com.web.model.dto.ProductDTO;
import com.web.model.request.ProductRequest;

public interface ProductsRepositoryCustom {

	public List<ProductDTO> findAll(ProductRequest productRequest);
}
