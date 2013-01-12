package it.swimv2.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import it.swimv2.controller.remoteController.IManutenzioneAbilitaUtente;
import it.swimv2.entities.Abilita;
import it.swimv2.entities.RichiestaAbilita;
import it.swimv2.entities.Utente;

@Stateless
public final class ManagerManutenzioneAbilitaUtente extends
		ManagerManutenzioneAbilita implements IManutenzioneAbilitaUtente {

	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;

	@Override
	public boolean inviareRichiestaAbilita(String nomeAbilita,
			String descrizione, long idUtente) {
		Utente utente;
		// controllo se l'utente esiste
		try {
			utente = this.getUtentePerId(idUtente);
		} catch (EntityNotFoundException e) {
			return false;
		}
		RichiestaAbilita ra;
		nomeAbilita = nomeAbilita.toLowerCase();
		try {
			ra = getRichiestaAbilita(utente, nomeAbilita);
			return false;
		} catch (EntityNotFoundException e) {
			// non esiste la richiesta di abilità quindi la creo
			ra = new RichiestaAbilita();
			ra.setDescrizione(descrizione.toLowerCase());
			ra.setNome(nomeAbilita);
			ra.setRichiedente(utente);
			try {
				// aggiungo la richiesta di abilità
				entityManager.persist(ra);
				return true;
			} catch (Exception w) {
				return false;
			}
		}
	}

	@Override
	public boolean rimuoverePropriaAbilita(String nomeAbilita, long idUtente) {
		Utente utente;
		Abilita abi;

		// controllo se l'utente esiste
		try {
			utente = this.getUtentePerId(idUtente);
		} catch (EntityNotFoundException e) {
			return false;
		}

		// controllo se l'abilità da rimuovere esiste
		try {
			abi = this.getAbilitaPerNome(nomeAbilita);
		} catch (EntityNotFoundException e) {
			return false;
		}
		if (!utente.possiedeAbilita(abi))
			return false;
		utente.RimuoviAbilità(abi);
		return true;
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}
}
