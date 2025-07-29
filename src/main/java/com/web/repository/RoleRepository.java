package com.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity,  Long>{
	List<RoleEntity> findByCode(String code);
}
