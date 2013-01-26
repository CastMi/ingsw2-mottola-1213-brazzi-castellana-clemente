package it.swimv2.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import it.swimv2.controller.remoteController.IManutenzioneAbilitaUtente;
import it.swimv2.entities.Abilita;
import it.swimv2.entities.RichiestaAbilita;
import it.swimv2.entities.RichiestaAbilitaPK;
import it.swimv2.entities.Utente;
import it.swimv2.entities.remoteEntities.IAbilita;
import it.swimv2.util.InvioRichiestaAbilitaEnum;
import it.swimv2.util.ManutenzioneAbilitaEnum;

@Stateless
public final class ManagerManutenzioneAbilitaUtente extends
		ManagerManutenzioneAbilita implements IManutenzioneAbilitaUtente {

	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;

	@Override
	public InvioRichiestaAbilitaEnum inviareRichiestaAbilita(
			String nomeRichiestaAbilita, String descrizione, String username) {
		Utente utente;
		RichiestaAbilita ra;

		// controllo se l'utente esiste
		try {
			utente = this.entityManager.find(Utente.class, username);
		} catch (Exception e) {
			return InvioRichiestaAbilitaEnum.UTENTE_INESISTENTE;
		}
		if (utente == null)
			return InvioRichiestaAbilitaEnum.UTENTE_INESISTENTE;

		try {
			ra = this.entityManager.find(RichiestaAbilita.class,
					new RichiestaAbilitaPK(nomeRichiestaAbilita, username));
		} catch (Exception e) {
			return InvioRichiestaAbilitaEnum.ERRORE;
		}
		if (ra != null) {
			return InvioRichiestaAbilitaEnum.RICHIESTAABILITA_DUPLICATA;
		}

		// non esiste la richiesta di abilità quindi la creo
		ra = new RichiestaAbilita();
		ra.setDescrizione(descrizione.toLowerCase());
		ra.setNome(nomeRichiestaAbilita.toLowerCase());
		ra.setRichiedente(utente);
		try {
			// aggiungo la richiesta di abilità
			entityManager.getTransaction().begin();
			entityManager.persist(ra);
		} catch (Exception w) {
			entityManager.getTransaction().rollback();
			return InvioRichiestaAbilitaEnum.ERRORE;
		}
		entityManager.getTransaction().commit();
		return InvioRichiestaAbilitaEnum.OK;
	}

	@Override
	public ManutenzioneAbilitaEnum rimuoverePropriaAbilita(String nomeAbilita,
			String username) {
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
			abi = this.entityManager.find(Abilita.class, nomeAbilita);
		} catch (Exception e) {
			return ManutenzioneAbilitaEnum.ABILITA_INESISTENTE;
		}
		if (abi == null) {
			return ManutenzioneAbilitaEnum.ABILITA_INESISTENTE;
		}

		if (!utente.RimuoviAbilità(abi))
			return ManutenzioneAbilitaEnum.ERRORE;

		try {
			// aggiungo la richiesta di abilità
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
	public IAbilita[] getProprieAbilita(String username) {
		Utente utente;
		// controllo se l'utente esiste
		try {
			utente = this.entityManager.find(Utente.class, username);
		} catch (Exception e) {
			return null;
		}
		if (utente == null)
			return null;
		
		return (IAbilita[]) utente.getAbilita().toArray( new IAbilita[utente.getAbilita().size()]);
	}
	
	@Override
	public IAbilita[] getTutteLeAbilita() {
		Query query = entityManager
				.createNamedQuery("Abilita.getTutteLeAbilita");

		List<Abilita> listaRis = null;
		try {
			listaRis = (List<Abilita>) query.getResultList();
		} catch (Exception e) {
			return null;
		}

		if (listaRis.size() == 0)
			return null;

		return (IAbilita[]) listaRis.toArray(new IAbilita[listaRis.size()]);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}
}
