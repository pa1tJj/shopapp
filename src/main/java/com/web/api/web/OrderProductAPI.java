package com.web.api.web;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.model.request.CartRequest;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class OrderProductAPI {
	@PostMapping("/add-to-cart")
    public ResponseEntity<String> orderProducts(@ModelAttribute CartRequest cartRequest) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null || auth instanceof AnonymousAuthenticationToken) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("chưa đăng nhập");
		} else {
			return ResponseEntity.ok("thành cônng");
		}	    		
	}
}
	
