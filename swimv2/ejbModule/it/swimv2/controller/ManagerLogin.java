/**
 * 
 */
package it.swimv2.controller;

import it.swimv2.entities.Amministratore;
import it.swimv2.entities.Utente;
import it.swimv2.util.PasswordCoder;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Daniele
 * 
 */

public class ManagerLogin {

	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;

	/**
	 * @param id
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public boolean verificaLogin(String id, String password)
			throws NoSuchAlgorithmException {
		if (verificaLoginAmministratore(id, password)) {
			return true;
		}
		if (verificaLoginUtente(id, password)) {
			return true;
		}
		return false;
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
				.setParameter("Id", id);
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
