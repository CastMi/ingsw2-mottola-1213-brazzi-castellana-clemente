package it.swimv2.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import it.swimv2.controller.remoteController.IManutenzioneAbilitaUtente;
import it.swimv2.entities.Abilita;
import it.swimv2.entities.RichiestaAbilita;
import it.swimv2.entities.Utente;
import it.swimv2.entities.util.ManutenzioneAbilitaEnum;
import it.swimv2.entities.util.ManutenzioneRichiestaAbilitaEnum;

@Stateless
public final class ManagerManutenzioneAbilitaUtente extends
		ManagerManutenzioneAbilita implements IManutenzioneAbilitaUtente {

	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;

	@Override
	public ManutenzioneRichiestaAbilitaEnum inviareRichiestaAbilita(String nomeAbilita,
			String descrizione, long idUtente) {
		Utente utente;
		// controllo se l'utente esiste
		try {
			utente = this.getUtentePerId(idUtente);
		} catch (EntityNotFoundException e) {
			return ManutenzioneRichiestaAbilitaEnum.UTENTE_INESISTENTE;
		}
		RichiestaAbilita ra;
		nomeAbilita = nomeAbilita.toLowerCase();
		try {
			ra = getRichiestaAbilita(utente, nomeAbilita);
			return ManutenzioneRichiestaAbilitaEnum.RICHIESTAABILITA_INESISTENTE;
		} catch (EntityNotFoundException e) {
			// non esiste la richiesta di abilità quindi la creo
			ra = new RichiestaAbilita();
			ra.setDescrizione(descrizione.toLowerCase());
			ra.setNome(nomeAbilita);
			ra.setRichiedente(utente);
			try {
				// aggiungo la richiesta di abilità
				entityManager.persist(ra);
				return ManutenzioneRichiestaAbilitaEnum.OK;
			} catch (Exception w) {
				return ManutenzioneRichiestaAbilitaEnum.ERRORE;
			}
		}
	}

	@Override
	public ManutenzioneAbilitaEnum rimuoverePropriaAbilita(String nomeAbilita, long idUtente) {
		Utente utente;
		Abilita abi;

		// controllo se l'utente esiste
		try {
			utente = this.getUtentePerId(idUtente);
		} catch (EntityNotFoundException e) {
			return ManutenzioneAbilitaEnum.UTENTE_INESISTENTE;
		}

		// controllo se l'abilità da rimuovere esiste
		try {
			abi = this.getAbilitaPerNome(nomeAbilita);
		} catch (EntityNotFoundException e) {
			return ManutenzioneAbilitaEnum.ABILITA_INESISTENTE;
		}
		if (!utente.possiedeAbilita(abi))
			return ManutenzioneAbilitaEnum.ERRORE;
		utente.RimuoviAbilità(abi);
		return ManutenzioneAbilitaEnum.OK;
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}
}
