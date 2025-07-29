package com.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.security.util.SecurityUtil;

@Controller
public class AdminShopController {
	@GetMapping(value = "/admin-home")
	public ModelAndView getMethodName() {
		ModelAndView mav = new ModelAndView("admin/home");
		mav.addObject("username", SecurityUtil.getPrincipal().getFullName());
		return mav;
	}
	
}
