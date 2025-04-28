package com.dsa360.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dsa360.api.constants.ApprovalStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dsa_application")
public class DSAApplicationEntity extends  BaseEntity{
	@Id   // DSA-2025-VC652345
	@Column(nullable = false, unique = true)
	private String dsaApplicationId;;

	@Column(nullable = false)
	private String firstName;

	private String middleName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String gender;

	@Column(nullable = false)
	private String dateOfBirth;

	@Column(nullable = false)
	private String nationality;

	@Column(nullable = false, unique = true)
	private String contactNumber;

	@Column(nullable = false, unique = true)
	private String emailAddress;

	@Column(nullable = false)
	private String streetAddress;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String state;

	@Column(nullable = false)
	private String postalCode;

	@Column(nullable = false)
	private String country;

	@Column(nullable = false)
	private String preferredLanguage;

	@Column(nullable = false)
	private String educationalQualifications;

	@Column(nullable = false)
	private String experience;

	@Column(nullable = false)
	private String isAssociatedWithOtherDSA;

	@Column(nullable = false)
	private String associatedInstitutionName;

	@Column(nullable = false)
	private String referralSource;

	@Column(nullable = false)
	private boolean emailVerified=false;
	
	@Column()
	private String emailVerificationToken;
	
	@Column(nullable = false)
	private String approvalStatus = ApprovalStatus.PENDING.getValue();

	public String getDsaApplicationId() {
		return dsaApplicationId;
	}

	public void setDsaApplicationId(String dsaApplicationId) {
		this.dsaApplicationId = dsaApplicationId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getEducationalQualifications() {
		return educationalQualifications;
	}

	public void setEducationalQualifications(String educationalQualifications) {
		this.educationalQualifications = educationalQualifications;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getIsAssociatedWithOtherDSA() {
		return isAssociatedWithOtherDSA;
	}

	public void setIsAssociatedWithOtherDSA(String isAssociatedWithOtherDSA) {
		this.isAssociatedWithOtherDSA = isAssociatedWithOtherDSA;
	}

	public String getAssociatedInstitutionName() {
		return associatedInstitutionName;
	}

	public void setAssociatedInstitutionName(String associatedInstitutionName) {
		this.associatedInstitutionName = associatedInstitutionName;
	}

	public String getReferralSource() {
		return referralSource;
	}

	public void setReferralSource(String referralSource) {
		this.referralSource = referralSource;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	
	
}
