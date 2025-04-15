package com.dsa360.api.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.security.SecureRandom;

public class DynamicID {
	
	private static final SecureRandom random = new SecureRandom();
	 
	public static String generateUniqueId(String type, String firstName, String lastName) {

		int currentYear = LocalDate.now().getYear(); 
		String initials = (firstName.substring(0, 1) + lastName.substring(0, 1)).toUpperCase(); 
		
		int uniqueNumber = 100000 + random.nextInt(900000); 

		return type + "-" + currentYear + "-" + initials + uniqueNumber;
	}

	public static String getGeneratedId() {
		String id = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
		return id;

	}

}
