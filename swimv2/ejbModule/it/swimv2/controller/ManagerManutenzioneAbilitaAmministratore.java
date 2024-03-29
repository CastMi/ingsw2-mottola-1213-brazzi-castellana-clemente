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
import it.swimv2.util.ManutentoreRichiesteAbilitaEnum;

@Stateless
public final class ManagerManutenzioneAbilitaAmministratore extends
		ManagerManutenzioneAbilita implements
		IManutenzioneAbilitaAmministratore {

	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;

	@Override
	public ManutentoreRichiesteAbilitaEnum accettareRichiestaAbilita(
			String nomeRichiestaAbilita, String username) {
		Utente utente;
		RichiestaAbilita richiestaAbilita;
		Abilita abi;
		boolean esisteAbilita = true;

		// controllo se l'utente esiste
		try {
			utente = this.entityManager.find(Utente.class, username);
		} catch (Exception e) {
			return ManutentoreRichiesteAbilitaEnum.UTENTE_INESISTENTE;
		}
		if (utente == null)
			return ManutentoreRichiesteAbilitaEnum.UTENTE_INESISTENTE;

		// controllo se esiste la richiesta di abilit�
		try {
			richiestaAbilita = this.entityManager.find(RichiestaAbilita.class,
					new RichiestaAbilitaPK(nomeRichiestaAbilita, username));
		} catch (Exception x) {
			// non esiste una richiesta di abilit� con tale nome associata a
			// tale utente
			return ManutentoreRichiesteAbilitaEnum.RICHIESTAABILITA_INESISTENTE;
		}
		if (richiestaAbilita == null)
			return ManutentoreRichiesteAbilitaEnum.RICHIESTAABILITA_INESISTENTE;

		// controllo se l'abilit� richiesta esiste gi�
		try {
			abi = this.entityManager.find(Abilita.class, nomeRichiestaAbilita);
		} catch (Exception e) {
			return ManutentoreRichiesteAbilitaEnum.ERRORE;
		}
		if (abi == null) {
			// dato che l'abilit� non esiste, la creo
			abi = new Abilita(nomeRichiestaAbilita,
					richiestaAbilita.getDescrizione());
			esisteAbilita = false;
		}

		try {
			if (!esisteAbilita) {
				entityManager.persist(abi);
				entityManager.flush();
			}
			utente.aggiungiAbilita(abi);
			entityManager.persist(utente);
			entityManager.remove(richiestaAbilita);
			entityManager.flush();
		} catch (Exception w) {
			w.printStackTrace();
			return ManutentoreRichiesteAbilitaEnum.ERRORE;
		}
		return ManutentoreRichiesteAbilitaEnum.OK;
	}

	@Override
	public ManutentoreRichiesteAbilitaEnum rifiutareRichiestaAbilita(
			String nomeRichiestaAbilita, String username) {
		Utente utente;
		RichiestaAbilita ra;

		// controllo se l'utente esiste
		try {
			utente = this.entityManager.find(Utente.class, username);
		} catch (Exception e) {
			return ManutentoreRichiesteAbilitaEnum.UTENTE_INESISTENTE;
		}
		if (utente == null)
			return ManutentoreRichiesteAbilitaEnum.UTENTE_INESISTENTE;

		// controllo se l'abilit� richiesta esiste
		try {
			ra = this.entityManager.find(RichiestaAbilita.class,
					new RichiestaAbilitaPK(nomeRichiestaAbilita, username));
		} catch (Exception e) {
			return ManutentoreRichiesteAbilitaEnum.RICHIESTAABILITA_INESISTENTE;
		}
		if (ra == null)
			return ManutentoreRichiesteAbilitaEnum.RICHIESTAABILITA_INESISTENTE;

		try {
			// rendo persistenti i cambiamenti con una transazione
			entityManager.remove(ra);
			entityManager.flush();
		} catch (Exception w) {
			return ManutentoreRichiesteAbilitaEnum.ERRORE;
		}
		return ManutentoreRichiesteAbilitaEnum.OK;
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

		// controllo se l'abilit� da rimuovere esiste
		try {
			abi = this.entityManager.find(Abilita.class, nomeRichiestaAbilita);
		} catch (Exception e) {
			return ManutenzioneAbilitaEnum.ABILITA_INESISTENTE;
		}
		if (abi == null)
			return ManutenzioneAbilitaEnum.ABILITA_INESISTENTE;

		if (!utente.RimuoviAbilit�(abi))
			return ManutenzioneAbilitaEnum.ERRORE;

		try {
			// rendo persistenti i cambiamenti
			entityManager.persist(utente);
			entityManager.flush();
		} catch (Exception w) {
			return ManutenzioneAbilitaEnum.ERRORE;
		}
		return ManutenzioneAbilitaEnum.OK;
	}

	@Override
	public IRichiestaAbilita[] getTutteLeRichiesteDiAbilita() {
		Query query = entityManager
				.createNamedQuery("RichiestaAbilita.getTutteLeRichiesteDiAbilita");
		List<?> risultatoQuery = query.getResultList();

		if (risultatoQuery.size() <= 0)
			return null;

		return (IRichiestaAbilita[]) risultatoQuery
				.toArray(new IRichiestaAbilita[risultatoQuery.size()]);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}
}
