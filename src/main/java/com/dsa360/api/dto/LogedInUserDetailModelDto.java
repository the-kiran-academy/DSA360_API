package com.dsa360.api.dto;

import java.util.List;

/**
 * @author RAM
 *
 */
public class LogedInUserDetailModelDto {

	private String userName;
	private List<String> roles;
	private String status;
	private String token;

	public LogedInUserDetailModelDto() {
		// Default constructor
	}

	public LogedInUserDetailModelDto(String userName, List<String> roles, String status) {
		super();
		this.userName = userName;
		this.roles = roles;
		this.status = status;
	}

	public LogedInUserDetailModelDto(String userName, List<String> roles, String status, String token) {
		super();
		this.userName = userName;
		this.roles = roles;
		this.status = status;
		this.token = token;
	}

	public LogedInUserDetailModelDto(String userName, List<String> roles) {
		super();
		this.userName = userName;
		this.roles = roles;
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
