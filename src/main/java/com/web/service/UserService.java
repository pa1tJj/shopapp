package com.web.service;

import java.util.List;

import com.web.model.dto.UserDTO;
import com.web.model.request.UserRequest;
import com.web.model.response.UserResponse;

public interface UserService {
	public List<UserResponse> findAll(UserRequest userRequest);
	public UserDTO findById(Long id);
	public UserDTO addOrUpdateUser(UserDTO userDTO);
	public void deleteUser(List<Long> ids);
}
