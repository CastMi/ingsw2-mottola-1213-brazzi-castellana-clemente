package it.swimv2.controller.util;

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
	 * @throws NoSuchAlgorithmException
	 */
	public static String getPasswordCodificata(String password)
			throws NoSuchAlgorithmException {

		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hashBytes = digest.digest(password.getBytes());
		String hashString = Base64.encode(hashBytes);
		return hashString;
	}

	/**
	 * @param password
	 * @param passwordCodificata
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static boolean verificaPassword(String password,
			String passwordCodificata) throws NoSuchAlgorithmException {

		return (passwordCodificata.equals(getPasswordCodificata(password)));
	}
}
