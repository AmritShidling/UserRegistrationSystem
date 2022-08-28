package com.user.management.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.user.management.demo.entity.ProvisionerEntity;
import com.user.management.demo.repository.ProvisionerRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	ProvisionerRepository provisionerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ProvisionerEntity provisioner = provisionerRepository.findByUserName(username);
		return new User(provisioner.getUserName(), provisioner.getPassword(), new ArrayList<>());
	}

}
