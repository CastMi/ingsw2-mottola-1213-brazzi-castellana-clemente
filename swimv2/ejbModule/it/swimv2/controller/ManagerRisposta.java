package it.swimv2.controller;

import it.swimv2.controller.remoteController.IManagerRisposta;
import it.swimv2.entities.Domanda;
import it.swimv2.entities.Risposta;
import it.swimv2.entities.Utente;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ManagerRisposta implements Serializable, IManagerRisposta {

	private static final long serialVersionUID = 2285777635470674269L;

	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;

	@Override
	public Risposta[] ricercaRisposta(String testo) {

		Query query = entityManager
				.createNamedQuery("Risposta.ricercaRisposte");

		query.setParameter("testo", "%" + testo + "%");

		return ottieniRisultatoQuery(query);
	}

	@Override
	public Risposta[] getRisposteByDomanda(Domanda domanda) {

		Query query = entityManager
				.createNamedQuery("Risposta.getRisposteByDomanda");

		query.setParameter("domanda", domanda);

		return ottieniRisultatoQuery(query);
	}

	@Override
	public boolean rilasciaFeedback(Risposta risposta, int voto) {
		try {
			entityManager.getTransaction().begin();
			risposta.setFeedback(voto);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	private Risposta[] ottieniRisultatoQuery(Query qy) {
		@SuppressWarnings("unchecked")
		List<Risposta> listaRis = (List<Risposta>) qy.getResultList();

		if (listaRis.size() == 0)
			return null;

		return (Risposta[]) listaRis.toArray();
	}

	@Override
	public boolean aggiungiRispsota(Domanda domanda, Utente utente,
			String risposta) {
		Risposta temp = new Risposta(domanda, utente, risposta);
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(temp);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
