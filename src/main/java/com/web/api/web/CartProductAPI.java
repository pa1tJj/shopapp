package com.web.api.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.model.request.CartRequest;
import com.web.model.request.OrderRequest;
import com.web.service.web.CartService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CartProductAPI {
	private CartService cartService;
	@PostMapping("/add-to-cart")
    public ResponseEntity<String> orderProducts(@RequestBody CartRequest cartRequest) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null || auth instanceof AnonymousAuthenticationToken) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("chưa đăng nhập");
		} else {
			cartService.addProductToCart(cartRequest);
			return ResponseEntity.ok("thành cônng");
		}	    		
	}
	
	@PostMapping("/cart-delete")
	public ResponseEntity<?> deleteProductInCart(@RequestBody CartRequest cartRequest) {
		cartService.deleteByCartAndProduct(cartRequest);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/cart-select")
	public ResponseEntity<?> totalAmount(@RequestBody OrderRequest orderRequest) {
		String total = cartService.getTotalAmount(orderRequest);
		Map<String, Object> response = new HashMap<>();
	    response.put("totalAmount", total);
		return ResponseEntity.ok(response);
	}
}
	
