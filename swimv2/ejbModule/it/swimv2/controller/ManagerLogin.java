/**
 * 
 */
package it.swimv2.controller;

import it.swimv2.controller.remoteController.ILogin;
import it.swimv2.entities.Amministratore;
import it.swimv2.entities.Utente;
import it.swimv2.entities.remoteEntities.IAmministratore;
import it.swimv2.entities.remoteEntities.IUtente;
import it.swimv2.util.PasswordCoder;
import it.swimv2.util.UtenteEnum;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * @author Daniele
 * 
 */

@Stateless
public class ManagerLogin implements ILogin {

	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.swimv2.controller.ILogin#verificaLogin(java.lang.String,
	 * java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see it.swimv2.controller.ILogin#verificaLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public UtenteEnum verificaLogin(String username, String password) {
		if (verificaLoginAmministratore(username, password)) {
			return UtenteEnum.AMMINISTRATORE;
		}
		if (verificaLoginUtente(username, password)) {
			return UtenteEnum.UTENTE;
		}
		return UtenteEnum.LOGIN_NON_VALIDO;
	}
	
	/* (non-Javadoc)
	 * @see it.swimv2.controller.ILogin#getAmministratore(java.lang.String)
	 */
	@Override
	public IAmministratore getAmministratore(String nomeUtente){
		try {
			return entityManager.find(Amministratore.class, nomeUtente);
		} catch (Exception e) {
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see it.swimv2.controller.ILogin#getUtente(java.lang.String)
	 */
	@Override
	public IUtente getUtente(String nomeUtente){
		try {
			return entityManager.find(Utente.class, nomeUtente);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @param username
	 * @param password
	 * @return
	 */

	private boolean verificaLoginUtente(String username, String password) {
		Utente utente = null;
		try {
			utente = entityManager.find(Utente.class, username);
		} catch (Exception e) {
			return false;
		}
		if (utente == null)
			return false;

		return verificaPassword(password, utente.getPassword());
	}

	/**
	 * @param username
	 * @param password
	 * @return
	 */
	private boolean verificaLoginAmministratore(String username, String password) {
		Amministratore amministratore = null;
		try {
			amministratore = entityManager.find(Amministratore.class, username);
		} catch (Exception e) {
			return false;
		}
		if (amministratore == null)
			return false;

		return verificaPassword(password, amministratore.getPassword());
	}

	/**
	 * @param password
	 * @param passwordCodificata
	 * @return
	 */
	private boolean verificaPassword(String password, String passwordCodificata) {
		return PasswordCoder.verificaPassword(password, passwordCodificata);
	}
}
