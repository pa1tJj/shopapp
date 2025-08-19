package com.web.controller.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.web.security.util.SecurityUtil;
import com.web.service.web.OrderService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class OrderController {
	private OrderService orderService;

	@GetMapping("/order-success-{id}")
	public ModelAndView getOrderPlaced(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("web/order_placed");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    boolean isLoggedIn = auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String);
		mav.addObject("isLoggedIn", isLoggedIn);
		if(isLoggedIn) {
			mav.addObject("fullName", SecurityUtil.getPrincipal().getFullName());
		    mav.addObject("userId", SecurityUtil.getPrincipal().getId());
		}
		mav.addObject("orders", orderService.getOrdersByUser(id));
		return mav;
	}
	
	@GetMapping("/payment-success-{id}")
	public ModelAndView getPaymentSuccess(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("web/payment_success");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    boolean isLoggedIn = auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String);
		mav.addObject("isLoggedIn", isLoggedIn);
		if(isLoggedIn) {
			mav.addObject("userId", SecurityUtil.getPrincipal().getId());
		}
		mav.addObject("payment", orderService.getResponse(id));
		return mav;
	}
}
