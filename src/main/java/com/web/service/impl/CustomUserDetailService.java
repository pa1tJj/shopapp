package com.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.entity.RoleEntity;
import com.web.entity.UserEntity;
import com.web.model.dto.MyUserDetails;
import com.web.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));	
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (RoleEntity roleEntity : userEntity.getRoles()) {
			 authorities.add(new SimpleGrantedAuthority("ROLE_" + roleEntity.getCode()));
		}
		return new MyUserDetails(userEntity.getUserName(), userEntity.getPassword(), userEntity.getFullName(), true, true, true, true, authorities);
	}

}
