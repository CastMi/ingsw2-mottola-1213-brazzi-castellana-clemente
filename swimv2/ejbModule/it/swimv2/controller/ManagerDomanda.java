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

		return entityManager.find(Domanda.class, idDomanda);
	}
	
	@Override
	public IDomanda[] proprieDomande(String userName) {
		Utente utente = entityManager.find(Utente.class, userName);
		
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
	public IDomanda creaDomanda(String userName, String titoloDomanda, String descrizioneDomanda,
			Set<String> abilita) {
		//ricerca utente
		Utente utente = entityManager.find(Utente.class, userName);
		
		// ricerca abilita
		Set<Abilita> setClasseAbilita = new HashSet<Abilita>();
		Abilita classeAbilita;
		for( String appAbilita : abilita){
			classeAbilita = entityManager.find(Abilita.class, appAbilita);
			setClasseAbilita.add(classeAbilita);
		}
		
		
		Domanda temp = new Domanda(titoloDomanda, descrizioneDomanda, setClasseAbilita, utente);

		entityManager.getTransaction().begin();
		entityManager.persist(temp);
		entityManager.getTransaction().commit();

		return temp;
	}

	private IDomanda[] ottieniRisultatoQuery(Query qy) {
		@SuppressWarnings("unchecked")
		List<Domanda> listaRis = (List<Domanda>) qy.getResultList();

		if (listaRis.size() == 0)
			return null;

		return (Domanda[]) listaRis.toArray();
	}



}
