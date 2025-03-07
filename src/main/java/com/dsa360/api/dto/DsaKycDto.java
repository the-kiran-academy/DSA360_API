package com.dsa360.api.dto;

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

	private String dsaApplicationId;
	private String bankName;
	private String accountNumber;
	private String ifscCode;

	private String approvalStatus = ApprovalStatus.PENDING.getValue();

	private MultipartFile passportFile;
	private MultipartFile drivingLicenceFile;
	private MultipartFile aadharCardFile;
	private MultipartFile panCardFile;
	private MultipartFile photographFile;
	private MultipartFile addressProofFile;
	private MultipartFile bankPassbookFile;

}
