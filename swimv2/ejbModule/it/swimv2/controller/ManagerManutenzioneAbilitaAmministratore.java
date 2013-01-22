package it.swimv2.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import it.swimv2.controller.remoteController.IManutenzioneAbilitaAmministratore;
import it.swimv2.entities.Abilita;
import it.swimv2.entities.RichiestaAbilita;
import it.swimv2.entities.RichiestaAbilitaPK;
import it.swimv2.entities.Utente;
import it.swimv2.entities.remoteEntities.IRichiestaAbilita;
import it.swimv2.util.ManutenzioneAbilitaEnum;
import it.swimv2.util.ManutenzioneRichiestaAbilitaEnum;

@Stateless
public final class ManagerManutenzioneAbilitaAmministratore extends
		ManagerManutenzioneAbilita implements
		IManutenzioneAbilitaAmministratore {

	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;

	@Override
	public ManutenzioneRichiestaAbilitaEnum accettareRichiestaAbilita(
			String nomeRichiestaAbilita, String username) {
		Utente utente;
		RichiestaAbilita richiestaAbilita;
		Abilita abi;
		boolean esisteAbilita = true;

		// controllo se l'utente esiste
		try {
			utente = this.entityManager.find(Utente.class, username);
		} catch (Exception e) {
			return ManutenzioneRichiestaAbilitaEnum.UTENTE_INESISTENTE;
		}
		if (utente == null)
			return ManutenzioneRichiestaAbilitaEnum.UTENTE_INESISTENTE;

		// controllo se esiste la richiesta di abilità
		try {
			richiestaAbilita = this.entityManager.find(RichiestaAbilita.class,
					new RichiestaAbilitaPK(nomeRichiestaAbilita, username));
		} catch (Exception x) {
			// non esiste una richiesta di abilità con tale nome associata a
			// tale utente
			return ManutenzioneRichiestaAbilitaEnum.RICHIESTAABILITA_INESISTENTE;
		}
		if (richiestaAbilita == null)
			return ManutenzioneRichiestaAbilitaEnum.RICHIESTAABILITA_INESISTENTE;

		// controllo se l'abilità richiesta esiste già
		try {
			abi = this.entityManager.find(Abilita.class, nomeRichiestaAbilita);
		} catch (Exception e) {
			return ManutenzioneRichiestaAbilitaEnum.ERRORE;
		}
		if (abi == null) {
			// dato che l'abilità non esiste, la creo
			abi = new Abilita();
			abi.setNome(nomeRichiestaAbilita);
			abi.setDescrizione(richiestaAbilita.getDescrizione());
			esisteAbilita = false;
		}

		// se fallisce l'inserimento dell'abilità è inutile continuare
		if (!utente.AggiungiAbilità(abi))
			return ManutenzioneRichiestaAbilitaEnum.ERRORE;

		try {
			// rendo persistenti i cambiamenti con una transazione
			entityManager.getTransaction().begin();
			if (!esisteAbilita)
				entityManager.persist(abi);
			entityManager.persist(utente);
			entityManager.remove(richiestaAbilita);
		} catch (Exception w) {
			// faccio un rollback e restituisco errore se qualcosa è andato male
			entityManager.getTransaction().rollback();
			return ManutenzioneRichiestaAbilitaEnum.ERRORE;
		}
		// committo se tutto è andato bene
		entityManager.getTransaction().commit();
		return ManutenzioneRichiestaAbilitaEnum.OK;
	}

	@Override
	public ManutenzioneRichiestaAbilitaEnum rifiutareRichiestaAbilita(
			String nomeRichiestaAbilita, String username) {
		Utente utente;
		RichiestaAbilita ra;

		// controllo se l'utente esiste
		try {
			utente = this.entityManager.find(Utente.class, username);
		} catch (Exception e) {
			return ManutenzioneRichiestaAbilitaEnum.UTENTE_INESISTENTE;
		}
		if (utente == null)
			return ManutenzioneRichiestaAbilitaEnum.UTENTE_INESISTENTE;

		// controllo se l'abilità richiesta esiste
		try {
			ra = this.entityManager.find(RichiestaAbilita.class,
					new RichiestaAbilitaPK(nomeRichiestaAbilita, username));
		} catch (Exception e) {
			return ManutenzioneRichiestaAbilitaEnum.RICHIESTAABILITA_INESISTENTE;
		}
		if (ra == null)
			return ManutenzioneRichiestaAbilitaEnum.RICHIESTAABILITA_INESISTENTE;

		try {
			// rendo persistenti i cambiamenti con una transazione
			entityManager.getTransaction().begin();
			entityManager.remove(ra);
		} catch (Exception w) {
			entityManager.getTransaction().rollback();
			return ManutenzioneRichiestaAbilitaEnum.ERRORE;
		}
		entityManager.getTransaction().commit();
		return ManutenzioneRichiestaAbilitaEnum.OK;
	}

	@Override
	public ManutenzioneAbilitaEnum rimuovereAbilita(
			String nomeRichiestaAbilita, String username) {
		Utente utente;
		Abilita abi;

		// controllo se l'utente esiste
		try {
			utente = this.entityManager.find(Utente.class, username);
		} catch (Exception e) {
			return ManutenzioneAbilitaEnum.UTENTE_INESISTENTE;
		}
		if (utente == null)
			return ManutenzioneAbilitaEnum.UTENTE_INESISTENTE;

		// controllo se l'abilità da rimuovere esiste
		try {
			abi = this.entityManager.find(Abilita.class, nomeRichiestaAbilita);
		} catch (Exception e) {
			return ManutenzioneAbilitaEnum.ABILITA_INESISTENTE;
		}
		if (abi == null)
			return ManutenzioneAbilitaEnum.ABILITA_INESISTENTE;

		if (!utente.possiedeAbilita(abi) || !utente.RimuoviAbilità(abi))
			return ManutenzioneAbilitaEnum.ERRORE;

		try {
			// rendo persistenti i cambiamenti
			entityManager.getTransaction().begin();
			entityManager.persist(utente);
		} catch (Exception w) {
			entityManager.getTransaction().rollback();
			return ManutenzioneAbilitaEnum.ERRORE;
		}
		entityManager.getTransaction().commit();
		return ManutenzioneAbilitaEnum.OK;
	}

	@Override
	public IRichiestaAbilita[] getTutteLeRichiesteDiAbilita() {
		Query query = entityManager
				.createNamedQuery("RichiestaAbilita.getTutteLeRichiesteDiAbilita");
		List<?> risultatoQuery = query.getResultList();
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

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}
}