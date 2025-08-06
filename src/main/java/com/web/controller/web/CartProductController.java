package com.web.controller.web;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.web.model.request.OrderRequest;
import com.web.repository.web.CartRepository;
import com.web.security.util.SecurityUtil;
import com.web.service.web.CartService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CartProductController {
	private CartService cartService;

	@GetMapping("/cart-{id}")
	public ModelAndView getCart(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("web/cart");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    boolean isLoggedIn = auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String);
		mav.addObject("isLoggedIn", isLoggedIn);
		if(isLoggedIn) {
			mav.addObject("fullName", SecurityUtil.getPrincipal().getFullName());
			mav.addObject("cartDetails", cartService.getUserCartProducts(id));
			mav.addObject("userId", id);
		}
		return mav;
	}
	
	@GetMapping("/order-payment")
	public ModelAndView getPayment(@RequestParam Long userId, @RequestParam List<Long> productId) {
		ModelAndView mav = new ModelAndView("web/order_payment");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    boolean isLoggedIn = auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String);
		mav.addObject("isLoggedIn", isLoggedIn);
		if(isLoggedIn) {
			mav.addObject("fullName", SecurityUtil.getPrincipal().getFullName());
			mav.addObject("userId", userId);
		}
		mav.addObject("product", cartService.findProductOrder(userId, productId));
		return mav;
	}
}
