package it.swimv2.controller;

import it.swimv2.entities.RichiestaAmicizia;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Daniele
 *
 */
public class ManagerAmicizia {

	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;
	
	public void creaAmicizia(RichiestaAmicizia richiestaAmicizia){
		Query queryIdRichiestaAmicizia = entityManager.createNamedQuery("Amicizia.getProssimoIdAmicizia");
		Query query = entityManager.createNamedQuery("Amicizia.inserisciNuovaAmicizia");
		query.setParameter("id", (int) queryIdRichiestaAmicizia.getResultList().get(0));
		query.setParameter("idMittente", richiestaAmicizia.getIdRichiedente());
		query.setParameter("idDestinatario", richiestaAmicizia.getIdDestinatario());
		return;
	}
}
