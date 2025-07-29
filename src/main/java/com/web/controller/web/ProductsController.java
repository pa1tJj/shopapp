package com.web.controller.web;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.enums.CategoryType;
import com.web.model.request.CartRequest;
import com.web.model.request.ProductSearchRequest;
import com.web.model.response.ProductResponse;
import com.web.security.util.SecurityUtil;
import com.web.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ProductsController {
    
	private ProductService productService;
	@GetMapping("/products-list")
	public ModelAndView getProducts(ProductSearchRequest productSearchRequest) {
		ModelAndView mav = new ModelAndView("web/products");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    boolean isLoggedIn = auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String);
		mav.addObject("isLoggedIn", isLoggedIn);
		if(isLoggedIn) {
			mav.addObject("fullName", SecurityUtil.getPrincipal().getFullName());
		}
		List<ProductResponse> products = productService.findProducts(productSearchRequest);
		mav.addObject("products", products);
		mav.addObject("categoryType", CategoryType.type());
		mav.addObject("productSearch", productSearchRequest);
		return mav;
	}

	@GetMapping("/products-detail-{id}") 
	public ModelAndView productSearch(@PathVariable Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("web/product-details");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    boolean isLoggedIn = auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String);
		mav.addObject("isLoggedIn", isLoggedIn);
		if(isLoggedIn) {
			mav.addObject("fullName", SecurityUtil.getPrincipal().getFullName());
		}
		mav.addObject("cart", new CartRequest());
		mav.addObject("userLogin", SecurityUtil.getPrincipal().getId());
		mav.addObject("productDetails", productService.findById(id));
		return mav;
	}
	
	@GetMapping("/cart")
	public ModelAndView getCart() {
		ModelAndView mav = new ModelAndView("web/cart");
		return mav;
	}
}
