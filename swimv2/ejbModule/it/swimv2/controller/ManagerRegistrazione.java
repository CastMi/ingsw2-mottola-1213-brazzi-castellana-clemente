/**
 * 
 */
package it.swimv2.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.swimv2.controller.remoteController.IRegistrazione;
import it.swimv2.entities.Abilita;
import it.swimv2.entities.Utente;
import it.swimv2.util.PasswordCoder;
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
			String email, String username, String password, String abilita) {
		RegistrazioneEnum rEnum = controlloDatiInseriti(nome, cognome, email,
				username, password);
		if (rEnum == RegistrazioneEnum.REGISTRAZIONE_VALIDA) {
			try{
				completaRegistrazione(nome, cognome, email, username, password, abilita);
			}
			catch (Exception e){
				return RegistrazioneEnum.ERRORE_EMAIL;
			}
		}
		return rEnum;
	}

	/**
	 * @param nome
	 * @param cognome
	 * @param email
	 * @param username
	 * @param password
	 * @param abilita 
	 */
	private void completaRegistrazione(String nome, String cognome,
			String email, String username, String password, String abilita) {
		Utente utente = new Utente(nome, cognome, username,
				PasswordCoder.getPasswordCodificata(password), email, costruisciSetAbilita(abilita));
		
		entityManager.persist(utente);
		return;
	}

	private Set<Abilita> costruisciSetAbilita(String abilita) {
		HashSet<Abilita> setAbilita = new HashSet<Abilita>();
		try {
		setAbilita.add((Abilita) entityManager.find(Abilita.class, abilita));
		} catch (Exception e){
			return null;
		}
		return setAbilita;
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
		if (checkNomeUtente(username) != RegistrazioneEnum.REGISTRAZIONE_VALIDA) {
			return RegistrazioneEnum.ERRORE_NOME_UTENTE;
		}
		return RegistrazioneEnum.REGISTRAZIONE_VALIDA;
	}

	/**
	 * @param nomeUtente
	 */
	private RegistrazioneEnum checkNomeUtente(String nomeUtente) {
		Utente utente = null;
		if (nomeUtente.equals("admin")) {
			return RegistrazioneEnum.ERRORE_NOME_UTENTE;
		}
		try {
			utente = entityManager.find(Utente.class, nomeUtente);
		} catch (Exception e) {
			return RegistrazioneEnum.ERRORE_NOME_UTENTE;
		}
		if (utente == null)
			return RegistrazioneEnum.REGISTRAZIONE_VALIDA;

		return RegistrazioneEnum.ERRORE_NOME_UTENTE;
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
		return RegistrazioneEnum.REGISTRAZIONE_VALIDA;
	}

	/**
	 * @param email
	 */
	@SuppressWarnings("unchecked")
	private RegistrazioneEnum checkEmail(String email) {

		if (email
				.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$;")) {
			return RegistrazioneEnum.ERRORE_EMAIL;

		}
		Query query = entityManager.createNamedQuery("Utente.getUtentePerEmail");
		query.setParameter("email", email);
		List<Object> risultatoQuery=(List<Object>)query.getResultList();
		if (risultatoQuery.size() > 0) {
			return RegistrazioneEnum.ERRORE_EMAIL;
		}
		return RegistrazioneEnum.REGISTRAZIONE_VALIDA;
		

	}

}
