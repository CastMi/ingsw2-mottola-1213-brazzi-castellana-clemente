package it.swimv2.controller;

import it.swimv2.controller.remoteController.IManagerRisposta;
import it.swimv2.entities.Domanda;
import it.swimv2.entities.Risposta;
import it.swimv2.entities.Utente;
import it.swimv2.entities.remoteEntities.IRisposta;

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
	public IRisposta apriRisposta(int idRispsota) {
		Risposta risposta = null;
		try {
			risposta = entityManager.find(Risposta.class, idRispsota);
		} catch (Exception e) {
			return null;
		}
		return risposta;
	}

	@Override
	public IRisposta[] ricercaRisposta(String testo) {

		Query query = entityManager
				.createNamedQuery("Risposta.ricercaRisposte");

		query.setParameter("testo", "%" + testo + "%");

		return ottieniRisultatoQuery(query);
	}

	@Override
	public IRisposta[] getRisposteByDomanda(int domanda) {

		Query query = entityManager
				.createNamedQuery("Risposta.getRisposteByDomanda");

		query.setParameter("domanda", domanda);

		return ottieniRisultatoQuery(query);
	}

	@Override
	public boolean rilasciaFeedback(int idRisposta, String userName, int voto) {
		try {
			Risposta risposta = entityManager.find(Risposta.class, idRisposta);
			Utente utente = entityManager.find(Utente.class, userName);
			if (risposta.getDomanda().getCreatore().equals(utente)) {
				entityManager.getTransaction().begin();
				risposta.setFeedback(voto);
				entityManager.getTransaction().commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private IRisposta[] ottieniRisultatoQuery(Query qy) {
		List<Risposta> listaRis = null;
		try {
			listaRis = (List<Risposta>) qy.getResultList();
		} catch (Exception e) {
			return null;
		}
		if (listaRis.size() == 0)
			return null;

		return (IRisposta[]) listaRis.toArray(new IRisposta[listaRis.size()]);
	}

	@Override
	public IRisposta aggiungiRispsota(int idDomanda, String userName,
			String risposta) {
		Domanda domanda = entityManager.find(Domanda.class, idDomanda);

		Utente utente = entityManager.find(Utente.class, userName);

		Risposta temp = new Risposta(domanda, utente, risposta);
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(temp);
			entityManager.getTransaction().commit();
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
