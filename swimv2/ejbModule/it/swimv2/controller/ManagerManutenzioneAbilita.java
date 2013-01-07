package it.swimv2.controller;

import it.swimv2.entities.Abilita;
import it.swimv2.entities.RichiestaAbilita;
import it.swimv2.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class ManagerManutenzioneAbilita {

	@PersistenceContext(unitName = "swimv2DB")
	protected EntityManager entityManager;

	/**
	 * Controlla l'esistenza della richiesta di abilità che possiede il nome
	 * corrispondente e sia riferita all'utente che possiede quell'id
	 * 
	 * @param idUtente
	 *            - l'id dell'utente che ha fatto la richiesta dell'abilità
	 * @param nomeAbilita
	 *            - il nome della richiesta di abilità da ricercare
	 * @return la richiesta di abilità trovata
	 * @throws EntityNotFoundException
	 *             qualora la richiesta di abilità non venga trovata
	 */
	protected RichiestaAbilita getRichiestaAbilita(Utente utente,
			String nomeAbilita) throws EntityNotFoundException {
		Query query = this.entityManager
				.createNamedQuery("RichiestaAbilita.getRichiestaAbilita");
		query.setParameter("nome", nomeAbilita);
		query.setParameter("utente", utente);
		return (RichiestaAbilita) query.getSingleResult();
	}

	/**
	 * Controlla l'esistenza dell'abilità che possiede il nome corrispondente
	 * 
	 * @param nomeAbilita
	 *            - il nome dell'abilità da ricercare
	 * @return l'abilità trovata
	 * @throws EntityNotFoundException
	 *             qualora l'abilità non venga trovata
	 */
	protected Abilita getAbilitaPerNome(String nomeAbilita)
			throws EntityNotFoundException {
		Query query = this.entityManager
				.createNamedQuery("Abilita.getAbilitaPerNome");
		query.setParameter("nome", nomeAbilita);
		return (Abilita) query.getSingleResult();
	}

	/**
	 * Controlla l'esistenza dell'utente che possiede quell'id
	 * 
	 * @param idUtente
	 *            - l'id dell'utente da ricercare
	 * @return l'utente trovato
	 * @throws EntityNotFoundException
	 *             qualora l'utente non venga trovato
	 */
	protected Utente getUtentePerId(long idUtente)
			throws EntityNotFoundException {
		Query query = this.entityManager
				.createNamedQuery("Utente.getUtentePerId");
		query.setParameter("id", idUtente);
		return (Utente) query.getSingleResult();
	}
}
