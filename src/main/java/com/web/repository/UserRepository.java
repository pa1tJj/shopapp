package com.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.entity.UserEntity;
import com.web.repository.custom.UserRepositoryCustom;

public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom{
	public void deleteByIdIn(List<Long> ids);
	Optional<UserEntity> findByUserName(String userName);
}
