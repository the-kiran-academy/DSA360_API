package com.dsa360.api.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class CustomerEntity {

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


}
