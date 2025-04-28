package com.dsa360.api.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class CustomerEntity extends  BaseEntity {

	@Id
	private String id;

	private String name;
	private String email;
	private String phoneNumber;
	private String permanentAddress;
	private String currentAddress;

	// Many Customers belong to one DSAAgent
	@ManyToOne
	@JoinColumn(name = "dsa_agent_id", nullable = false)
	private DSAApplicationEntity dsaAgentId;

	@OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
	private Set<LoanApplicationEntity> loanApplications;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<DocumentEntity> documents;

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

	public DSAApplicationEntity getDsaAgentId() {
		return dsaAgentId;
	}

	public void setDsaAgentId(DSAApplicationEntity dsaAgentId) {
		this.dsaAgentId = dsaAgentId;
	}

	public Set<LoanApplicationEntity> getLoanApplications() {
		return loanApplications;
	}

	public void setLoanApplications(Set<LoanApplicationEntity> loanApplications) {
		this.loanApplications = loanApplications;
	}

	public Set<DocumentEntity> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<DocumentEntity> documents) {
		this.documents = documents;
	}


	
	
}
