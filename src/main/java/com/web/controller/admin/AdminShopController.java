package com.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdminShopController {
	@GetMapping(value = "/home")
	public ModelAndView getMethodName() {
		ModelAndView mav = new ModelAndView("admin/home");
		return mav;
	}
	
}
