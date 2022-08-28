package com.user.management.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.management.demo.entity.ProvisionerEntity;


public interface ProvisionerRepository extends JpaRepository<ProvisionerEntity, Integer>{

	ProvisionerEntity findByUserName(String username);
	
}
