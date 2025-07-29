package com.web.converter;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.web.entity.RoleEntity;
import com.web.entity.UserEntity;
import com.web.enums.DistrictType;
import com.web.model.dto.UserDTO;
import com.web.model.response.UserResponse;
import com.web.repository.RoleRepository;

@Component
public class UserConverter {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private RoleRepository roleRepository;
	
	public UserResponse covertToUserResponse(UserEntity userEntity) {
		UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);
		userResponse.setAddress(userEntity.getWard() + ", " + userEntity.getStreet() + ", " + DistrictType.valueOf(userEntity.getDistrict()).getName());
		return userResponse;
	}
	
	public UserDTO covertToUserDTO(UserEntity userEntity) {
		UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
		return userDTO;
	}
	
	public UserEntity convertToUserEntity(UserDTO userDTO) {
		UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
		userEntity.setPassword(passwordEncoder(userDTO.getPassword()));
		List<RoleEntity> role = roleRepository.findByCode("USER");		
		userEntity.setRoles(role);
		return userEntity;
	}
	
	public String passwordEncoder(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
}
