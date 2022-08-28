package com.user.management.demo.entity;

public class UserResponse {
	
	private String email;
	private Name name;
	private String id;
	private String applicationId;
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the name
	 */
	public Name getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(Name name) {
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}
	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public UserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserResponse(String email, Name name, String id, String applicationId) {
		super();
		this.email = email;
		this.name = name;
		this.id = id;
		this.applicationId = applicationId;
	}
	
	
}
