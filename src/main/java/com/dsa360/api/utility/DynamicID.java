package com.dsa360.api.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;

public class DynamicID {
	
	private static final SecureRandom random = new SecureRandom();
	
	private static final Logger logger = LoggerFactory.getLogger(DynamicID.class);
	 
	public static String generateUniqueId(String type, String firstName, String lastName) {

		int currentYear = LocalDate.now().getYear(); 
		String initials = (firstName.substring(0, 1) + lastName.substring(0, 1)).toUpperCase(); 
		
		int uniqueNumber = 100000 + random.nextInt(900000); 

		return type + "-" + currentYear + "-" + initials + uniqueNumber;
	}

	public static void main(String[] args) {
		String dsaCode = generateUniqueId("DSA", "John", "Doe");
		String kycCode = generateUniqueId("KYC", "Jane", "Smith");

		logger.debug("DSA Code: {}", dsaCode); // Example: DSA-2025-JD123456 (if the year is 2025)
		logger.debug("KYC Code: {}", kycCode); // Example: KYC-2025-JS654321

		 }

	public static String getGeneratedId() {
		 return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
		

	}

}
