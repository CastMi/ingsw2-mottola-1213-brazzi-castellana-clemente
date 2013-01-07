package it.swimv2.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import it.swimv2.controller.remoteController.IManutenzioneAbilitaAmministratore;
import it.swimv2.entities.Abilita;
import it.swimv2.entities.RichiestaAbilita;
import it.swimv2.entities.Utente;
import it.swimv2.entities.remoteEntities.IRichiestaAbilita;

@Stateless
public class ManagerManutenzioneAbilitaAmministratore extends
		ManagerManutenzioneAbilita implements
		IManutenzioneAbilitaAmministratore {

	@Override
	public boolean accettareRichiestaAbilita(String nomeRichiestaAbilita,
			long idUtente) {
		Utente utente;
		RichiestaAbilita temp;
		Abilita abi;
		boolean esisteAbilita = true;

		// controllo se l'utente esiste
		try {
			utente = this.getUtentePerId(idUtente);
		} catch (EntityNotFoundException e) {
			return false;
		}

		// controllo se esiste la richiesta di abilit�
		try {
			temp = this.getRichiestaAbilita(utente, nomeRichiestaAbilita);
		} catch (EntityNotFoundException x) {
			// non esiste una richiesta di abilit� con tale nome associata a
			// tale utente
			return false;
		}

		// controllo se l'abilit� richiesta esiste gi�
		try {
			abi = this.getAbilitaPerNome(nomeRichiestaAbilita);
		} catch (EntityNotFoundException e) {
			// dato che l'abilit� non esiste, la creo
			abi = new Abilita();
			abi.setNome(nomeRichiestaAbilita);
			abi.setDescrizione(temp.getDescrizione());
			esisteAbilita = false;
		}
		// se fallisce l'inserimento dell'abilit� � inutile continuare
		if (!utente.AggiungiAbilit�(abi))
			return false;

		try {
			// rendo persistenti i cambiamenti
			if (!esisteAbilita)
				entityManager.persist(abi);
			entityManager.persist(utente);
		} catch (Exception w) {
			return false;
		}
		// rimuovo la richiesta di abilit�
		this.rimuoviRichiestaAbilita(utente, nomeRichiestaAbilita);
		return true;
	}

	@Override
	public boolean rifiutareRichiestaAbilita(String nomeRichiestaAbilita,
			long idUtente) {
		Utente utente;
		RichiestaAbilita ra;

		// controllo se l'utente esiste
		try {
			utente = this.getUtentePerId(idUtente);
		} catch (EntityNotFoundException e) {
			return false;
		}

		// controllo se l'abilit� richiesta esiste
		try {
			ra = this.getRichiestaAbilita(utente, nomeRichiestaAbilita);
		} catch (EntityNotFoundException e) {
			return false;
		}
		this.rimuoviRichiestaAbilita(utente, ra.getNome());
		return true;
	}

	@Override
	public boolean rimuovereAbilita(String nomeAbilita, long idUtente) {
		Utente utente;
		Abilita abi;

		// controllo se l'utente esiste
		try {
			utente = this.getUtentePerId(idUtente);
		} catch (EntityNotFoundException e) {
			return false;
		}

		// controllo se l'abilit� da rimuovere esiste
		try {
			abi = this.getAbilitaPerNome(nomeAbilita);
		} catch (EntityNotFoundException e) {
			return false;
		}
		if (!utente.possiedeAbilita(abi))
			return false;
		utente.RimuoviAbilit�(abi);
		return true;
	}

	/**
	 * Cancella la richiesta la richiesta di abilit� che possiede il nome
	 * corrispondente e sia riferita all'utente che possiede quell'id
	 * 
	 * @param utente
	 *            - l'id dell'utente che ha fatto la richiesta dell'abilit�
	 * @param nomeAbilita
	 *            - il nome della richiesta di abilit� da cancellare
	 */
	private void rimuoviRichiestaAbilita(Utente utente, String nomeAbilita) {
		Query query = entityManager
				.createNamedQuery("RichiestaAbilita.Elimina");
		query.setParameter("nome", nomeAbilita);
		query.setParameter("utente", utente);
		query.executeUpdate();
	}

	@Override
	public IRichiestaAbilita[] getTutteLeRichiesteDiAbilita() {
		Query query = entityManager
				.createNamedQuery("RichiestaAbilita.getTutteLeRichiesteDiAbilita");
		List<Object> risultatoQuery = query.getResultList();
		if (risultatoQuery.size() > 0) {
			IRichiestaAbilita[] richieste = new RichiestaAbilita[risultatoQuery
					.size()];
			for (int i = 0; i < richieste.length; i++) {
				richieste[i] = (RichiestaAbilita) risultatoQuery.get(i);
			}
			return richieste;
		}
		return null;
	}
}