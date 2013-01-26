package it.swimv2.controller.remoteController;

import javax.ejb.Remote;

import it.swimv2.entities.Amministratore;
import it.swimv2.entities.Utente;
import it.swimv2.util.UtenteEnum;

@Remote
public interface ILogin {

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.swimv2.controller.ILogin#verificaLogin(java.lang.String,
	 * java.lang.String)
	 */
	public abstract UtenteEnum verificaLogin(String username, String password);

	public abstract Amministratore getAmministratore(String nomeUtente);

	public abstract Utente getUtente(String nomeUtente);

}