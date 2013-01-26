package it.swimv2.controller.remoteController;

import javax.ejb.Remote;

import it.swimv2.entities.remoteEntities.IAmministratore;
import it.swimv2.entities.remoteEntities.IUtente;
import it.swimv2.util.UtenteEnum;

@Remote
public interface ILogin {

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.swimv2.controller.ILogin#verificaLogin(java.lang.String,
	 * java.lang.String)
	 */
	public UtenteEnum verificaLogin(String username, String password);

	public IAmministratore getAmministratore(String nomeUtente);

	public IUtente getUtente(String nomeUtente);

}