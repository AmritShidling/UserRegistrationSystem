package com.user.management.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.management.demo.entity.AuthRequest;
import com.user.management.demo.entity.ProvisionerEntity;
import com.user.management.demo.entity.ProvisionerRequest;
import com.user.management.demo.entity.ProvisionerResponse;
import com.user.management.demo.entity.User;
import com.user.management.demo.entity.UserRequest;
import com.user.management.demo.entity.UserResponse;
import com.user.management.demo.repository.ProvisionerRepository;
import com.user.management.demo.repository.UserRepository;
import com.user.management.demo.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@RequestMapping("/api")
public class UserManagementController {

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	ProvisionerRepository provisionerRepository;

	@Autowired
	UserRepository userRepository;

	@PostMapping("/provisioners")
	public ProvisionerResponse createProvisioner(@RequestBody ProvisionerRequest request) {
		ProvisionerEntity entity = new ProvisionerEntity(request.getUserName(), request.getPassword());
		String provisionerId = String.valueOf(provisionerRepository.save(entity).getId());
		ProvisionerResponse response = new ProvisionerResponse();
		response.setProvisionerId(provisionerId);
		String secret = "THISISRONDOMSECRETFROMHAPPEO";
		response.setSecretKey(secret);
		return response;
	}

	/**
	 * This will create user & return application Id
	 * 
	 * @param orgId
	 * @param provisionerId
	 * @param userRequest
	 * @return
	 */
	@PostMapping("/organisations/{orgId}/provisioner/{provisionerId}/users")
	public UserResponse createUser(@PathVariable("orgId") Integer orgId,
			@PathVariable("provisionerId") Integer provisionerId, @RequestBody UserRequest userRequest) {

		User user = new User(userRequest.getEmail(), orgId, userRequest.getName().getFirstName(),
				userRequest.getName().getLastName(), provisionerId, null, false);

		String applicationId = String.valueOf(userRepository.save(user).getId());

		UserResponse response = new UserResponse(userRequest.getEmail(), userRequest.getName(),
				provisionerId.toString(), applicationId);

		return response;

	}

	/**
	 * api to get JWT token
	 * 
	 * @param authRequest
	 * @return
	 * @throws Exception
	 */

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		String error = null;
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (ExpiredJwtException ex) {
			error = "JWT token Expired. Generate new token.";
		} catch (AuthenticationException ex) {
			error = "Invalid User Name/Password";

		}
		String token = jwtUtil.generateToken(authRequest.getUserName());
		if (token != null)
			return token;
		else
			return error;
	}

	@GetMapping("/users/activate")
	public String activateUsers() {

		List<User> users = userRepository.findByIsActive(false);
		if (users != null && !users.isEmpty()) {
			for (User user : users) {
				user.setIsActive(true);
			}
		}

		userRepository.saveAll(users);
		return "Number Users activated :" + users.size() + " Successfully..";
	}

}
