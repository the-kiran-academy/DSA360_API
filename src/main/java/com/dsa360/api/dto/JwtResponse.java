package com.dsa360.api.dto;

/**
 * @author RAM
 *
 */
public class JwtResponse {
	
	private String token;
	
	public JwtResponse() {
		// Not Implemented
	}

	public JwtResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
