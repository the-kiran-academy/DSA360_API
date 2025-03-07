package com.dsa360.api.utility;

import java.text.SimpleDateFormat;

public class DynamicID {

	public static String getDynamicId() {
		String id = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
		return id;

	}

}
