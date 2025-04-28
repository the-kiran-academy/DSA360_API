package com.dsa360.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dsa_kyc")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DsaKycEntity extends  BaseEntity{

	@Id // KYC-2025-VC763456
	private String dsaKycId;

	@OneToOne
	@JoinColumn(name = "dsa_reg_id")
	private DSAApplicationEntity dsaApplicationId;

	private String bankName;
	private String accountNumber;
	private String ifscCode;

	private String passport;
	private String drivingLicence;
	private String aadharCard;
	private String panCard;
	private String photograph;
	private String addressProof;
	private String bankPassbook;

	private String approvalStatus;
	private int attempt=1;
	public String getDsaKycId() {
		return dsaKycId;
	}
	public void setDsaKycId(String dsaKycId) {
		this.dsaKycId = dsaKycId;
	}
	public DSAApplicationEntity getDsaApplicationId() {
		return dsaApplicationId;
	}
	public void setDsaApplicationId(DSAApplicationEntity dsaApplicationId) {
		this.dsaApplicationId = dsaApplicationId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getDrivingLicence() {
		return drivingLicence;
	}
	public void setDrivingLicence(String drivingLicence) {
		this.drivingLicence = drivingLicence;
	}
	public String getAadharCard() {
		return aadharCard;
	}
	public void setAadharCard(String aadharCard) {
		this.aadharCard = aadharCard;
	}
	public String getPanCard() {
		return panCard;
	}
	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}
	public String getPhotograph() {
		return photograph;
	}
	public void setPhotograph(String photograph) {
		this.photograph = photograph;
	}
	public String getAddressProof() {
		return addressProof;
	}
	public void setAddressProof(String addressProof) {
		this.addressProof = addressProof;
	}
	public String getBankPassbook() {
		return bankPassbook;
	}
	public void setBankPassbook(String bankPassbook) {
		this.bankPassbook = bankPassbook;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public int getAttempt() {
		return attempt;
	}
	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}

	
	
}
