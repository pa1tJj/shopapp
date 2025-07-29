package com.web.repository.custom;

import java.util.List;

import com.web.entity.UserEntity;
import com.web.model.request.UserRequest;

public interface UserRepositoryCustom {
	public List<UserEntity> findAll(UserRequest userRequest);
}
