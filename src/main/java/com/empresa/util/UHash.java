package com.empresa.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UHash {
	
	public static String hash(String password) throws NoSuchAlgorithmException{
		
		return Hash.md5(password);
	}

}
class Hash {

	public static String md5(String password) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();

	}
}