/**
 * 
 */
package it.swimv2.controller;

import it.swimv2.controller.remoteController.ILogin;
import it.swimv2.entities.Amministratore;
import it.swimv2.entities.Utente;
import it.swimv2.util.PasswordCoder;
import it.swimv2.util.UtenteEnum;

import java.security.NoSuchAlgorithmException;
import java.util.List;


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

	/* (non-Javadoc)
	 * @see it.swimv2.controller.ILogin#verificaLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public UtenteEnum verificaLogin(String id, String password)
			throws NoSuchAlgorithmException {
		UtenteEnum enum1 = new UtenteEnum();
		if (verificaLoginAmministratore(id, password)) {
			enum1.setAmministratore(true);
			return enum1;
		}
		if (verificaLoginUtente(id, password)) {
			enum1.setUtente(true);
			return enum1;
		}
		return enum1;
	}

	/**
	 * @param id
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	
	private boolean verificaLoginUtente(String id, String password)
			throws NoSuchAlgorithmException {
		Query query = entityManager.createNamedQuery("Utente.getUtentePerId")
				.setParameter("id", id);
		List<?> risultatoQuery = query.getResultList();
		if (risultatoQuery.size() > 0) { // e' stato trovato il codicePersona
											// nel database
			Utente utente = (Utente) risultatoQuery.get(0);
			if (verificaPassword(password, utente.getPassword())) {
				return true;
			}
		}
		// codice persona non trovato
		return false;
	}

	/**
	 * @param id
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private boolean verificaLoginAmministratore(String id, String password)
			throws NoSuchAlgorithmException {
		Query query = entityManager
				.createNamedQuery("Amministratore.getAmministratore");
		List<?> risultatoQuery = query.getResultList();
		if (risultatoQuery.size() > 0) { // e' stato trovato il codicePersona
											// nel database
			Amministratore amministratore = (Amministratore) risultatoQuery
					.get(0);
			if (verificaPassword(password, amministratore.getPassword())) {
				return true;
			}
		}
		// codice persona non trovato
		return false;
	}

	/**
	 * @param password
	 * @param passwordCodificata
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private boolean verificaPassword(String password, String passwordCodificata)
			throws NoSuchAlgorithmException {

		return PasswordCoder.verificaPassword(password, passwordCodificata);
	}
}
