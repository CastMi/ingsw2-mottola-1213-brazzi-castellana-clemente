/**
 * 
 */
package it.swimv2.controller;

import java.util.List;

import it.swimv2.controller.remoteController.IRegistrazione;
import it.swimv2.entities.Utente;
import it.swimv2.util.RegistrazioneEnum;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Daniele
 * 
 */
@Stateless
public class ManagerRegistrazione implements IRegistrazione {

	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.swimv2.controller.IRegistrazione#nuovaRegistrazione(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public RegistrazioneEnum nuovaRegistrazione(String nome, String cognome,
			String email, String username, String password) {
		RegistrazioneEnum rEnum = controlloDatiInseriti(nome, cognome, email,
				username, password);
		if (rEnum == RegistrazioneEnum.REGISTRAZIONE_VALIDA) {
			completaRegistrazione(nome, cognome, email, username, password);
		}
		return rEnum;
	}

	/**
	 * @param nome
	 * @param cognome
	 * @param email
	 * @param username
	 * @param password
	 */
	private void completaRegistrazione(String nome, String cognome,
			String email, String username, String password) {
		Utente utente = new Utente();
		utente.setCognome(cognome);
		utente.setNome(nome);
		utente.setEmail(email);
		utente.setPassword(password);
		entityManager.persist(utente);
		return;
	}

	/**
	 * @param nome
	 * @param cognome
	 * @param email
	 * @param username
	 * @param password
	 */
	private RegistrazioneEnum controlloDatiInseriti(String nome,
			String cognome, String email, String username, String password) {
		if (checkNome(nome, cognome) != RegistrazioneEnum.REGISTRAZIONE_VALIDA) {
			return RegistrazioneEnum.ERRORE_NOME_COGNOME;
		}
		if (checkEmail(email) != RegistrazioneEnum.REGISTRAZIONE_VALIDA) {
			return RegistrazioneEnum.ERRORE_EMAIL;
		}
		if (checkPassword(password) != RegistrazioneEnum.REGISTRAZIONE_VALIDA) {
			return RegistrazioneEnum.ERRORE_PASSWORD;
		}
		if (checkId(username) != RegistrazioneEnum.REGISTRAZIONE_VALIDA) {
			return RegistrazioneEnum.ERRORE_NOME_UTENTE;
		}
		return RegistrazioneEnum.REGISTRAZIONE_VALIDA;
	}

	/**
	 * @param username
	 */
	@SuppressWarnings("unchecked")
	private RegistrazioneEnum checkId(String username) {
		Query query = entityManager.createNamedQuery("Utente.getUtentePerId")
				.setParameter("Id", username);
		List<Object> risultatoQuery = (List<Object>) query.getResultList();
		if (risultatoQuery.size() > 0) {
			return RegistrazioneEnum.ERRORE_NOME_UTENTE;
		}
		query = entityManager
				.createNamedQuery("Amministratore.getAmministratore");
		risultatoQuery = (List<Object>) query.getResultList();
		if (risultatoQuery.size() > 0) {
			return RegistrazioneEnum.ERRORE_NOME_UTENTE;
		}
		return RegistrazioneEnum.REGISTRAZIONE_VALIDA;
	}

	/**
	 * @param password
	 */
	private RegistrazioneEnum checkPassword(String password) {
		if (password.isEmpty()) {
			return RegistrazioneEnum.ERRORE_PASSWORD;
		}
		return RegistrazioneEnum.REGISTRAZIONE_VALIDA;
	}

	/**
	 * @param nome
	 * @param cognome
	 */
	private RegistrazioneEnum checkNome(String nome, String cognome) {
		if (nome.isEmpty()) {
			return RegistrazioneEnum.ERRORE_NOME_COGNOME;
		}
		if (cognome.isEmpty()) {
			return RegistrazioneEnum.ERRORE_NOME_COGNOME;
		}
		if (!nome.matches("^[_A-Za-z-\\+]+(\\ [_A-Za-z]))")) {
			return RegistrazioneEnum.ERRORE_NOME_COGNOME;
		}
		if (!cognome.matches("^[_A-Za-z-\\+]+(\\ [_A-Za-z]))")) {
			return RegistrazioneEnum.ERRORE_NOME_COGNOME;
		}
		return RegistrazioneEnum.REGISTRAZIONE_VALIDA;
	}

	/**
	 * @param email
	 */
	private RegistrazioneEnum checkEmail(String email) {

		if (email
				.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$;")) {
			return RegistrazioneEnum.ERRORE_EMAIL;

		}
		return RegistrazioneEnum.REGISTRAZIONE_VALIDA;

	}

}
