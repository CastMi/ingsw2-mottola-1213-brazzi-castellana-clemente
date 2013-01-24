package it.swimv2.controller;

import it.swimv2.controller.remoteController.IManagerDomanda;
import it.swimv2.entities.Abilita;
import it.swimv2.entities.Domanda;
import it.swimv2.entities.Utente;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ManagerDomanda implements Serializable, IManagerDomanda {

	private static final long serialVersionUID = -6820848436608479992L;

	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;

	@Override
	public Domanda[] proprieDomande(Utente utente) {
		Query query = entityManager.createNamedQuery("Domanda.prorieDomande");

		query.setParameter("utente", utente);

		return ottieniRisultatoQuery(query);
	}

	@Override
	public Domanda[] ricercaDomande(String testo) {
		Query query = entityManager.createNamedQuery("Domanda.ricercaDomande");

		query.setParameter("testo", testo);

		return ottieniRisultatoQuery(query);
	}

	@Override
	public Domanda creaDomanda(Utente utente, String titolo, String domanda,
			Set<Abilita> abilita) {
		Domanda temp = new Domanda(titolo, domanda, abilita, utente);

		entityManager.getTransaction().begin();
		entityManager.persist(temp);
		entityManager.getTransaction().commit();

		return temp;
	}

	private Domanda[] ottieniRisultatoQuery(Query qy) {
		@SuppressWarnings("unchecked")
		List<Domanda> listaRis = (List<Domanda>) qy.getResultList();

		if (listaRis.size() == 0)
			return null;

		return (Domanda[]) listaRis.toArray();
	}

}
