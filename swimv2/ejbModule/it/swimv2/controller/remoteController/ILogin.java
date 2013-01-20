package it.swimv2.controller.remoteController;

import java.security.NoSuchAlgorithmException;

public interface ILogin {

	/**
	 * @param id
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public abstract boolean verificaLogin(String id, String password)
			throws NoSuchAlgorithmException;

}