package com.user.management.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "provisioner")
public class ProvisionerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "password")
	private String password;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public ProvisionerEntity(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public ProvisionerEntity() {
		super();
	}
	
	

}
