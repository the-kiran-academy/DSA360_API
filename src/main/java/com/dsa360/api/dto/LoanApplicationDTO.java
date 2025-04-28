package com.dsa360.api.dto;

import lombok.Data;

import javax.validation.constraints.*;

import com.dsa360.api.constants.ApprovalStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LoanApplicationDTO {

    private String id; // Loan application ID as String

    @NotEmpty(message = "Customer ID is required.")
    private String customerId; // ID of the customer applying for the loan

    @NotNull(message = "Loan amount is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Loan amount must be greater than zero.")
    private BigDecimal loanAmount; // The amount of loan being requested

    @NotNull(message = "Loan tenure is required.")
    @Min(value = 1, message = "Loan tenure must be at least 1 month.")
    private Integer loanTenure; // Loan tenure in months

    @NotEmpty(message = "Loan purpose is required.")
    private String loanPurpose; // Purpose of the loan (e.g., Home, Car, Personal)

    private String status=ApprovalStatus.PENDING.getValue(); // Current status of the loan application (e.g., "Pending", "Approved", "Rejected")

    @DecimalMin(value = "0.0", message = "Interest rate must be a positive value.")
    private BigDecimal interestRate; // Interest rate for the loan

    private BigDecimal emi; // Estimated Monthly Installment

    // Additional relevant fields

    @NotNull(message = "Application date is required.")
    @PastOrPresent(message = "Application date must be today or in the past.")
    private LocalDate applicationDate; // Date of application submission

    @NotEmpty(message = "Payment frequency is required.")
    private String paymentFrequency; // Frequency of loan payments (e.g., monthly, bi-weekly)

    private String incomeDetails; // Information about applicant's income

    private String employmentStatus; // Employment status (e.g., employed, self-employed)

    private String loanPurposeDescription; // Detailed description of loan purpose
    
    @Min(value = 300, message = "Credit score must be at least 300.")
    @Max(value = 850, message = "Credit score cannot exceed 850.")
    private Integer creditScore; // Applicant's credit score

    private String rejectionReason; // Reason for rejection (if applicable)

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Integer getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(Integer loanTenure) {
		this.loanTenure = loanTenure;
	}

	public String getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public BigDecimal getEmi() {
		return emi;
	}

	public void setEmi(BigDecimal emi) {
		this.emi = emi;
	}

	public LocalDate getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getPaymentFrequency() {
		return paymentFrequency;
	}

	public void setPaymentFrequency(String paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}

	public String getIncomeDetails() {
		return incomeDetails;
	}

	public void setIncomeDetails(String incomeDetails) {
		this.incomeDetails = incomeDetails;
	}

	public String getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public String getLoanPurposeDescription() {
		return loanPurposeDescription;
	}

	public void setLoanPurposeDescription(String loanPurposeDescription) {
		this.loanPurposeDescription = loanPurposeDescription;
	}

	public Integer getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(Integer creditScore) {
		this.creditScore = creditScore;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
    
    
}
