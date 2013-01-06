package it.swimv2.controller;

import java.util.List;

import it.swimv2.controller.remoteController.IRicercaUtenti;
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
	public IUtente[] ricercaUtentiPerCognome(String stringa) {
		Query query = entityManager.createNamedQuery("Utente.getUtentiPerCognome");
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
		Query query = entityManager.createNamedQuery("Utente.getUtentiPerUsername");
		query.setParameter("username", stringa);
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
	public IUtente[] ricercaUtentiPerEmail(String stringa) {
		Query query = entityManager.createNamedQuery("Utente.isUtentePerEmail");
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
	
	
}
