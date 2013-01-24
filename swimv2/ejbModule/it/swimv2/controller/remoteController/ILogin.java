package it.swimv2.controller.remoteController;

import it.swimv2.util.UtenteEnum;

import javax.ejb.Remote;

@Remote
public interface ILogin {

	/**
	 * @param id
	 * @param password
	 * @return
	 */
	public abstract UtenteEnum verificaLogin(String id, String password);

}