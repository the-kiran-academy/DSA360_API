package com.dsa360.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.dsa360.api.constants.ApprovalStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DsaKycDto {

	private String dsaKycId;

	@NotBlank(message = "DSA application ID is required")
    private String dsaApplicationId;

    @NotBlank(message = "Bank name is required")
    @Size(min = 2, max = 50, message = "Bank name should be between 2 and 50 characters")
    private String bankName;

    @NotBlank(message = "Account number is required")
    @Pattern(regexp = "\\d{9,18}", message = "Account number must be between 9 and 18 digits")
    private String accountNumber;

    @NotBlank(message = "IFSC code is required")
    @Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "Invalid IFSC code format")
    private String ifscCode;


	private String approvalStatus = ApprovalStatus.PENDING.getValue();
	private int attempt=1;

	private MultipartFile passportFile;
	private MultipartFile drivingLicenceFile;
	private MultipartFile aadharCardFile;
	private MultipartFile panCardFile;
	private MultipartFile photographFile;
	private MultipartFile addressProofFile;
	private MultipartFile bankPassbookFile;
	public String getDsaKycId() {
		return dsaKycId;
	}
	public void setDsaKycId(String dsaKycId) {
		this.dsaKycId = dsaKycId;
	}
	public String getDsaApplicationId() {
		return dsaApplicationId;
	}
	public void setDsaApplicationId(String dsaApplicationId) {
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
	public MultipartFile getPassportFile() {
		return passportFile;
	}
	public void setPassportFile(MultipartFile passportFile) {
		this.passportFile = passportFile;
	}
	public MultipartFile getDrivingLicenceFile() {
		return drivingLicenceFile;
	}
	public void setDrivingLicenceFile(MultipartFile drivingLicenceFile) {
		this.drivingLicenceFile = drivingLicenceFile;
	}
	public MultipartFile getAadharCardFile() {
		return aadharCardFile;
	}
	public void setAadharCardFile(MultipartFile aadharCardFile) {
		this.aadharCardFile = aadharCardFile;
	}
	public MultipartFile getPanCardFile() {
		return panCardFile;
	}
	public void setPanCardFile(MultipartFile panCardFile) {
		this.panCardFile = panCardFile;
	}
	public MultipartFile getPhotographFile() {
		return photographFile;
	}
	public void setPhotographFile(MultipartFile photographFile) {
		this.photographFile = photographFile;
	}
	public MultipartFile getAddressProofFile() {
		return addressProofFile;
	}
	public void setAddressProofFile(MultipartFile addressProofFile) {
		this.addressProofFile = addressProofFile;
	}
	public MultipartFile getBankPassbookFile() {
		return bankPassbookFile;
	}
	public void setBankPassbookFile(MultipartFile bankPassbookFile) {
		this.bankPassbookFile = bankPassbookFile;
	}
	
	

}
