package com.dsa360.api.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDTO {
	private String id;

	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Phone number must be a valid Indian number (10 digits starting with 6-9)")
	private String phoneNumber;

	@NotBlank(message = "Permanent address is required")
	@Size(max = 100, message = "Permanent address cannot exceed 100 characters")
	private String permanentAddress;

	@NotBlank(message = "Current address is required")
	@Size(max = 100, message = "Current address cannot exceed 100 characters")
	private String currentAddress;

	@NotBlank(message = "DSA Agent ID is required")
	private String dsaAgentId;
	private List<DocumentDTO> documents;
	private List<LoanApplicationDTO> loanApplications;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public String getDsaAgentId() {
		return dsaAgentId;
	}
	public void setDsaAgentId(String dsaAgentId) {
		this.dsaAgentId = dsaAgentId;
	}
	public List<DocumentDTO> getDocuments() {
		return documents;
	}
	public void setDocuments(List<DocumentDTO> documents) {
		this.documents = documents;
	}
	public List<LoanApplicationDTO> getLoanApplications() {
		return loanApplications;
	}
	public void setLoanApplications(List<LoanApplicationDTO> loanApplications) {
		this.loanApplications = loanApplications;
	}
	
	
}
