package com.dsa360.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.dsa360.api.constants.ApprovalStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author RAM
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DSAApplicationDTO {
	
	
	private String dsaApplicationId;

	@NotBlank(message = "First name is mandatory")
	private String firstName;

	@NotBlank(message = "Middle name is mandatory")
	private String middleName;

	@NotBlank(message = "Last name is mandatory")
	private String lastName;

	@NotBlank(message = "Gender is mandatory")
	@Pattern(regexp = "Male|Female|Other", message = "Gender must be Male, Female, or Other")
	private String gender;

	@NotNull(message = "Date of birth is mandatory")
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date of Birth must be in YYYY-MM-DD format")
	private String dateOfBirth;

	@NotBlank(message = "Nationality is mandatory")
	private String nationality;

	@NotBlank(message = "Contact number is mandatory")
	@Pattern(regexp = "^[1-9]\\d{9}$", message = "Contact number must be a 10-digit number and cannot start with 0")
	private String contactNumber;

	@NotBlank(message = "Email address is mandatory")
	@Email(message = "Email should be valid")
	private String emailAddress;

	@NotBlank(message = "Street address is mandatory")
	private String streetAddress;

	@NotBlank(message = "City is mandatory")
	private String city;

	@NotBlank(message = "State is mandatory")
	private String state;

	@NotBlank(message = "Postal code is mandatory")
	@Pattern(regexp = "\\d{6}", message = "Postal code must be a 6-digit number")
	private String postalCode;

	@NotBlank(message = "Country is mandatory")
	private String country;

	@NotBlank(message = "Preferred language is mandatory")
	private String preferredLanguage;

	@NotBlank(message = "Educational Qualification is mandatory")
	private String educationalQualifications;

	@NotBlank(message = "experience is mandatory  YES/NO")
	private String experience;

	@NotBlank(message = "Associated With Other DSA is mandatory  YES/NO")
	private String isAssociatedWithOtherDSA;

	@NotBlank(message = "Institution Name is mandatory - if no (NA)")
	private String associatedInstitutionName;

	@NotBlank(message = "Referral Source is mandatory - if no (NA)")
	private String referralSource;
	
	private String approvalStatus = ApprovalStatus.PENDING.getValue();
	private boolean emailVerified;
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
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public boolean isEmailVerified() {
		return emailVerified;
	}
	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}
	
	

}
