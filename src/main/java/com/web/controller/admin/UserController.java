package com.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.web.enums.DistrictType;
import com.web.model.request.UserRequest;
import com.web.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/admin/user-list")
	public ModelAndView userList(@ModelAttribute UserRequest userRequest) {
		ModelAndView mav = new ModelAndView("/admin/user/list");
		mav.addObject("modelSearch", userRequest);
		mav.addObject("userList", userService.findAll(userRequest));
		mav.addObject("districtType", DistrictType.type());
		return mav;
	}
	
	@GetMapping("/admin/user-edit-{id}")
	public ModelAndView userEdit(@PathVariable Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/admin/user/edit");
		mav.addObject("userEdit", userService.findById(id));
		mav.addObject("districtType", DistrictType.type());
		return mav;
	}
}
