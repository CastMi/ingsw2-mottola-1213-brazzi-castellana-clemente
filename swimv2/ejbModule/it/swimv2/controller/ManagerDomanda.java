package it.swimv2.controller;

import it.swimv2.controller.remoteController.IManagerDomanda;
import it.swimv2.entities.Abilita;
import it.swimv2.entities.Domanda;
import it.swimv2.entities.Utente;
import it.swimv2.entities.remoteEntities.IDomanda;

import java.io.Serializable;
import java.util.HashSet;
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
	public IDomanda apriDomanda(int idDomanda) {
		Domanda domanda = null;
		try {
			domanda = entityManager.find(Domanda.class, idDomanda);
		} catch (Exception e) {
			return null;
		}
		return domanda;
	}

	@Override
	public IDomanda[] proprieDomande(String userName) {
		Utente utente = null;
		try {
			utente = entityManager.find(Utente.class, userName);
		} catch (Exception e) {
			utente = null;
		}
		if (utente == null) {
			return null;
		}
		Query query = entityManager.createNamedQuery("Domanda.prorieDomande");

		query.setParameter("utente", utente);

		return ottieniRisultatoQuery(query);
	}

	@Override
	public IDomanda[] ricercaDomande(String testo) {
		Query query = entityManager.createNamedQuery("Domanda.ricercaDomande");

		query.setParameter("testo", testo);

		return ottieniRisultatoQuery(query);
	}

	@Override
	public IDomanda creaDomanda(String userName, String titoloDomanda,
			String descrizioneDomanda, Set<String> abilita) {
		// ricerca utente
		Utente utente = null;
		try {
			utente = entityManager.find(Utente.class, userName);
		} catch (Exception e) {
			utente = null;
		}
		if (utente == null) {
			return null;
		}
		// ricerca abilita
		Set<Abilita> setClasseAbilita = new HashSet<Abilita>();
		Abilita classeAbilita = null;
		for (String appAbilita : abilita) {
			try {
				classeAbilita = entityManager.find(Abilita.class, appAbilita);
			} catch (Exception e) {
				classeAbilita = null;
			}
			if (classeAbilita != null) {
				setClasseAbilita.add(classeAbilita);
			}
		}

		Domanda temp = new Domanda(titoloDomanda, descrizioneDomanda,
				setClasseAbilita, utente);
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(temp);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			if(entityManager.getTransaction().isActive())
				entityManager.getTransaction().rollback();
			return null;
		}

		return temp;
	}

	@SuppressWarnings("unchecked")
	private IDomanda[] ottieniRisultatoQuery(Query qy) {
		List<Domanda> listaRis = null;
		try {
			listaRis = (List<Domanda>) qy.getResultList();
		} catch (Exception e) {
			return null;
		}

		if (listaRis.size() == 0)
			return null;

		return (IDomanda[]) listaRis.toArray(new IDomanda[listaRis.size()]);
	}

}
