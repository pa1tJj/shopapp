package com.web.api.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.model.dto.UserDTO;
import com.web.security.CustomSuccessHandler;
import com.web.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class RegisterAPI {
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomSuccessHandler customSuccessHandler;

	@PostMapping("/register-acc") 
	public void getRegister(@ModelAttribute UserDTO userDTO, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		userService.addOrUpdateUser(userDTO);
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassword());
		Authentication auth = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		HttpSession session = request.getSession();
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
		customSuccessHandler.onAuthenticationSuccess(request, response, auth);
	}
}
