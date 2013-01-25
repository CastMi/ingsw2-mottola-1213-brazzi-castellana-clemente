/**
 * 
 */
package it.swimv2.controller;

import it.swimv2.controller.remoteController.ILogin;
import it.swimv2.entities.Amministratore;
import it.swimv2.entities.Utente;
import it.swimv2.util.PasswordCoder;
import it.swimv2.util.UtenteEnum;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	@Override
	public UtenteEnum verificaLogin(String id, String password) {
		if (verificaLoginAmministratore(id, password)) {
			return UtenteEnum.AMMINISTRATORE;
		}
		if (verificaLoginUtente(id, password)) {
			return UtenteEnum.UTENTE;
		}
		return UtenteEnum.LOGIN_NON_VALIDO;
	}

	/**
	 * @param username
	 * @param password
	 * @return
	 */

	private boolean verificaLoginUtente(String username, String password) {
		Query query = entityManager.createNamedQuery("Utente.getUtentePerId")
				.setParameter("username", username);
		Utente risultatoQuery = (Utente) query.getSingleResult();
		if (risultatoQuery == null)
			return false;

		return verificaPassword(password, risultatoQuery.getPassword());
	}

	/**
	 * @param username
	 * @param password
	 * @return
	 */
	private boolean verificaLoginAmministratore(String username, String password) {
		Query query = entityManager
				.createNamedQuery("Amministratore.getAmministratore");
		Amministratore risultatoQuery = (Amministratore) query
				.getSingleResult();
		if (risultatoQuery == null)
			return false;

		return verificaPassword(password, risultatoQuery.getPassword());
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
