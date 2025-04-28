package com.dsa360.api.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author RAM
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemUserDto {

	@NotBlank(message = "user name is mandatory")
	private String userName;

	@NotBlank(message = "Password is mandatory")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;

	@NotBlank(message = "Security question is mandatory")
	private String question;

	@NotBlank(message = "Answer is mandatory")
	private String answer;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private String createdDate;

	@NotNull(message = "Roles are mandatory")
	private List<String> roles;

	@NotNull(message = "Regions are mandatory")
	private List<String> regions;

	@NotBlank(message = "Status is mandatory")
	private String status = "Active"; //Suspended  Deactivated  Active

	@NotBlank(message = "Status Reason is mandatory")
	private String statusReason = "KYC Verified";

	@NotBlank(message = "DSA Application ID is mandatory")
	@Pattern(regexp = "[A-Z]{3}-\\d{4}-[A-Z]{2}\\d{6}", message = "Application ID must follow the format XXX-YYYY-XX######")
	private String dsaApplicationId;

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getRegions() {
		return regions;
	}

	public void setRegions(List<String> regions) {
		this.regions = regions;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public String getDsaApplicationId() {
		return dsaApplicationId;
	}

	public void setDsaApplicationId(String dsaApplicationId) {
		this.dsaApplicationId = dsaApplicationId;
	}

	
	
}
