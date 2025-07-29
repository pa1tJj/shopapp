package com.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.web.enums.CategoryType;
import com.web.model.request.ProductRequest;
import com.web.model.response.ProductResponse;
import com.web.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping(value = "/admin/product-list")
	public ModelAndView productList(ProductRequest productRequest, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/product/list");
		List<ProductResponse> products = productService.findAll(productRequest, PageRequest.of(productRequest.getPage() - 1, productRequest.getMaxPageItems()));		
		ProductResponse productResponse = new ProductResponse();
		productResponse.setListResult(products);
		productResponse.setTotalItems(3);
		Page<ProductResponse> result = new PageImpl<>(products, PageRequest.of(productRequest.getPage() - 1, productRequest.getMaxPageItems()), products.size());
		mav.addObject("productList", result.getContent());
		mav.addObject("totalPage", result.getTotalPages());
		mav.addObject("modelSearch", productRequest);
		mav.addObject("categoryType", CategoryType.type());
		return mav;
	}
	
	@GetMapping(value = "/admin/product-edit") 
	public ModelAndView productEdit(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/product/edit");
		mav.addObject("productEdit", new ProductResponse());
		mav.addObject("categoryType", CategoryType.type());
		return mav;
	}
	
	@GetMapping(value = "/admin/product-edit-{id}") 
	public ModelAndView productEdit(@PathVariable Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/product/edit");
		mav.addObject("productEdit", productService.findById(id));
		mav.addObject("categoryType", CategoryType.type());
		return mav;
	}
}
