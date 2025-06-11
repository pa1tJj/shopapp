package com.web.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public ModelAndView productList(@ModelAttribute ProductRequest productRequest, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/product/list");
		List<ProductResponse> products = productService.findAll(productRequest, PageRequest.of(productRequest.getPage() - 1, productRequest.getMaxPageItems()));
		ProductResponse productResponse = new ProductResponse();
		productResponse.setListResult(products);
		productResponse.setTotalItems(6);
		mav.addObject("productList", productResponse.getListResult());
		mav.addObject("modelSearch", productRequest);
		List<Integer> totalPage = new ArrayList<>();
		for(int i = 1; i <= productResponse.getTotalItems(); i++) {
			totalPage.add(i);
		}
		mav.addObject("totalPage", totalPage);
		mav.addObject("categoryType", CategoryType.type());
		return mav;
	}
}
