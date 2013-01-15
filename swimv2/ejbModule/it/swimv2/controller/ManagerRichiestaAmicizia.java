package it.swimv2.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Daniele
 *
 */
public class ManagerRichiestaAmicizia {

	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;
	
	public void creaNuovaRichiestaAmicizia(String idMittente, String idDestinatario, String note){
		Query queryIdRichiestaAmicizia = entityManager.createNamedQuery("RichiestaAmicizia.getProssimoIdRichiestaAmicizia");
		Query query = entityManager.createNamedQuery("RichiestaAmicizia.inserisciNuovaRichiestaAmicizia");
		query.setParameter("id", (int) queryIdRichiestaAmicizia.getResultList().get(0));
		query.setParameter("idMittente", idMittente);
		query.setParameter("idDestinatario", idDestinatario);
		query.setParameter("note", note);
		return;
	}
	
	public void rimuoviRichiestaAmicizia(int id){
		Query query = entityManager.createNamedQuery("RichiestaAmicizia.eliminaRichiestaAmicizia");
		query.setParameter("id", id);
		return;
	}
	
	
}
