package com.dsa360.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "loan_condition")
public class LoanConditioEntity {

	@Id
	private Long id;

	private String bankName;
	private String loanType;
	private double interestRate;
	private String minCreditScore;
	private double processingFee;
}
