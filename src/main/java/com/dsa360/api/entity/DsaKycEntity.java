package com.dsa360.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dsa_kyc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DsaKycEntity {

	@Id
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
	private int attempt=0;

}
