package com.web.service.web;

import java.util.List;

import com.web.model.dto.CartProductDTO;
import com.web.model.request.CartRequest;
import com.web.model.request.OrderRequest;

public interface CartService {
	public void addProductToCart(CartRequest cartRequest);
	public List<CartProductDTO> getUserCartProducts(Long id);
	public void deleteByCartAndProduct(CartRequest cartRequest);
	public String getTotalAmount(OrderRequest orderRequest);
	public List<CartProductDTO> findProductOrder(Long userId, List<Long> productId);
}
