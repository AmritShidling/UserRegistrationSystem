package com.user.management.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.management.demo.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, String>{

	List<User> findByIsActive(boolean isActive);
	
}
