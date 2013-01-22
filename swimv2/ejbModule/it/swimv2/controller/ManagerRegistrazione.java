/**
 * 
 */
package it.swimv2.controller;

import java.util.List;

import it.swimv2.controller.remoteController.IRegistrazione;
import it.swimv2.entities.Utente;
import it.swimv2.util.ErroriRegistrazione;

import javax.ejb.Remote;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Daniele
 * 
 */

@Remote(IRegistrazione.class)
public class ManagerRegistrazione implements IRegistrazione {

	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;
	private ErroriRegistrazione status = new ErroriRegistrazione();
	private Utente utente;

	/* (non-Javadoc)
	 * @see it.swimv2.controller.IRegistrazione#nuovaRegistrazione(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ErroriRegistrazione nuovaRegistrazione(String nome, String cognome,
			String email, String id, String password) {

		controlloDatiInseriti(nome, cognome, email, id, password);
		if (status.registrazioneValida()) {
			completaRegistrazione(nome, cognome, email, id, password);
		}

		return status;
	}

	/**
	 * @param nome
	 * @param cognome
	 * @param email
	 * @param id
	 * @param password
	 */
	private void completaRegistrazione(String nome, String cognome,
			String email, String id, String password) {

		utente = new Utente();
		utente.setCognome(cognome);
		utente.setNome(nome);
		utente.setEmail(email);
		utente.setPassword(password);
//TODO		utente.setId(id);
		entityManager.persist(utente);
		return;

	}

	/**
	 * @param nome
	 * @param cognome
	 * @param email
	 * @param nomeUtente
	 * @param password
	 */
	private void controlloDatiInseriti(String nome, String cognome,
			String email, String nomeUtente, String password) {

		checkNome(nome, cognome);
		checkEmail(email);
		checkPassword(password);
		checkId(nomeUtente);
	}

	/**
	 * @param nomeUtente
	 */
	@SuppressWarnings("unchecked")
	private void checkId(String nomeUtente) {

		Query query = entityManager.createNamedQuery("Utente.getUtentePerId")
				.setParameter("Id", nomeUtente);
		List<Object> risultatoQuery = (List<Object>) query.getResultList();
		if (risultatoQuery.size() > 0) {
			status.ErroreId = true;
			return;
		}
		query = entityManager
				.createNamedQuery("Amministratore.getAmministratore");
		risultatoQuery = (List<Object>) query.getResultList();
		if (risultatoQuery.size() > 0) {
			status.ErroreId = true;
			return;
		}
	}

	/**
	 * @param password
	 */
	private void checkPassword(String password) {

		if (password.isEmpty()) {
			status.ErrorePassword = true;
		}

	}

	/**
	 * @param nome
	 * @param cognome
	 */
	private void checkNome(String nome, String cognome) {
		if (nome.isEmpty()) {
			status.ErroreNome = true;
		}
		if (cognome.isEmpty()) {
			status.ErroreCognome = true;
		}
		if (!nome.matches("^[_A-Za-z-\\+]+(\\ [_A-Za-z]))")) {
			status.ErroreNome = true;
		}
		if (!cognome.matches("^[_A-Za-z-\\+]+(\\ [_A-Za-z]))")) {
			status.ErroreCognome = true;
		}

	}

	/**
	 * @param email
	 */
	private void checkEmail(String email) {

		if (email
				.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$;")) {
			status.ErroreEmail = true;
		}

	}

}
