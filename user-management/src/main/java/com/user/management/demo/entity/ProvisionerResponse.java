package com.user.management.demo.entity;

public class ProvisionerResponse {

	private String provisionerId;
	private String secretKey;

	/**
	 * @return the provisionerId
	 */
	public String getProvisionerId() {
		return provisionerId;
	}

	/**
	 * @param provisionerId the provisionerId to set
	 */
	public void setProvisionerId(String provisionerId) {
		this.provisionerId = provisionerId;
	}

	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

}
