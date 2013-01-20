package it.swimv2.controller;

import it.swimv2.entities.RichiestaAmicizia;
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
	
	public void creaNuovaRichiestaAmicizia(int idMittente, int idDestinatario, String note){
		RichiestaAmicizia richiestaAmicizia = new RichiestaAmicizia(idMittente, idDestinatario, note);
		entityManager.persist(richiestaAmicizia);
	}
	
	public void rimuoviRichiestaAmicizia(int id){
		Query query = entityManager.createNamedQuery("RichiestaAmicizia.eliminaRichiestaAmicizia");
		query.setParameter("id", id);
	}
	
	
}
