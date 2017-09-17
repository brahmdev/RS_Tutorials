package com.devops.dev.util;

import java.util.Random;

public class UserNamePassswordUtil {

	static public String getUserName(String firstName, String lastName) {
		System.out.println("Hello World");
        String username = firstName.charAt(0) + lastName.substring(0, Math.min(2, lastName.length()));
        username = username + genRandomString(); 
        return username.toLowerCase();
	}
	
	static public String getUserPassword(String firstName, String lastName) {
		System.out.println("Hello World");
        String username = firstName.charAt(0) + lastName.substring(0, Math.min(2, lastName.length()));
        username = username + genRandomString(); 
        String password = username + "@RS";
        return password.toLowerCase();
	}
	
	static String genRandomString() {
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 4;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	    return generatedString;
	}
}
