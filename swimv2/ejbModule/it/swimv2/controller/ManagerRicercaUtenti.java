package it.swimv2.controller;

import java.util.List;

import it.swimv2.controller.remoteController.IRicercaUtenti;
import it.swimv2.entities.Domanda;
import it.swimv2.entities.Utente;
import it.swimv2.entities.remoteEntities.IUtente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ManagerRicercaUtenti implements IRicercaUtenti {

	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public IUtente[] ricercaUtentiPerNome(String stringa) {
		Query query = entityManager.createNamedQuery("Utente.getUtentiPerNome");
		query.setParameter("nome", stringa);

		List<Object> risultatoQuery = (List<Object>) query.getResultList();
		if (risultatoQuery.size() > 0) {
			// ci sono progetti creati dal professore
			Utente[] utentiTrovati = new Utente[risultatoQuery.size()];
			// copia del risultato
			for (int i = 0; i < risultatoQuery.size(); i++) {
				utentiTrovati[i] = (Utente) risultatoQuery.get(i);
			}
			return utentiTrovati;
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public IUtente[] ricercaUtentiPerCognome(String stringa) {
		Query query = entityManager
				.createNamedQuery("Utente.getUtentiPerCognome");
		query.setParameter("cognome", stringa);
		List<Object> risultatoQuery = (List<Object>) query.getResultList();
		if (risultatoQuery.size() > 0) {
			// ci sono progetti creati dal professore
			Utente[] utentiTrovati = new Utente[risultatoQuery.size()];
			// copia del risultato
			for (int i = 0; i < risultatoQuery.size(); i++) {
				utentiTrovati[i] = (Utente) risultatoQuery.get(i);
			}
			return utentiTrovati;
		}
		return null;
	}

	@Override
	public IUtente[] ricercaUtentiPerUsername(String stringa) {
		Utente[] risultato = new Utente[1];
		try {
			risultato[0] = this.entityManager.find(Utente.class, stringa);
		} catch (Exception e) {
			risultato[0] = null;
		}
		return risultato;
	}

	@Override
	@SuppressWarnings("unchecked")
	public IUtente[] ricercaUtentiPerEmail(String stringa) {
		Query query = entityManager
				.createNamedQuery("Utente.getUtentePerEmail");
		query.setParameter("email", stringa);
		List<Object> risultatoQuery = (List<Object>) query.getResultList();
		if (risultatoQuery.size() > 0) {
			// ci sono progetti creati dal professore
			Utente[] utentiTrovati = new Utente[risultatoQuery.size()];
			// copia del risultato
			for (int i = 0; i < risultatoQuery.size(); i++) {
				utentiTrovati[i] = (Utente) risultatoQuery.get(i);
			}
			return utentiTrovati;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IUtente[] ricercaUtentiPerUnQualsiasiCampo(String testo) {
		Query query = entityManager.createNamedQuery("Utente.getUtentePerUnQualiasiCampo");

		query.setParameter("testo", "%" + testo + "%");

		List<Domanda> listaRis = null;
		try {
			listaRis = (List<Domanda>) query.getResultList();
		} catch (Exception e) {
			return null;
		}

		if (listaRis.size() == 0)
			return null;

		return (IUtente[]) listaRis.toArray(new IUtente[listaRis.size()]);
	}

}
