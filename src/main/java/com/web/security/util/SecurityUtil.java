package com.web.security.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.web.model.dto.MyUserDetails;

public class SecurityUtil {
	public static MyUserDetails getPrincipal() {
		return (MyUserDetails) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
	}
}
