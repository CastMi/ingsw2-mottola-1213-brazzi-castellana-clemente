package it.swimv2.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * @author Daniele
 * 
 */
public class PasswordCoder {

	/**
	 * @param password
	 * @return
	 */
	public static String getPasswordCodificata(String password) {

		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		byte[] hashBytes = digest.digest(password.getBytes());
		String hashString = Base64.encode(hashBytes);
		return hashString;
	}

	/**
	 * @param password
	 * @param passwordCodificata
	 * @return
	 */
	public static boolean verificaPassword(String password,
			String passwordCodificata) {

		return (passwordCodificata.equals(getPasswordCodificata(password)));
	}
}
