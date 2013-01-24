package it.swimv2.controller;

import java.util.List;

import it.swimv2.controller.remoteController.IRichiestaAmicizia;
import it.swimv2.entities.RichiestaAmicizia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Daniele
 *
 */
@Stateless
public class ManagerRichiestaAmicizia implements IRichiestaAmicizia {

	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * @see it.swimv2.controller.IRichiestaAmicizia#creaNuovaRichiestaAmicizia(int, int, java.lang.String)
	 */
	@Override
	public void creaNuovaRichiestaAmicizia(int idMittente, int idDestinatario, String note){
		RichiestaAmicizia richiestaAmicizia = new RichiestaAmicizia(idMittente, idDestinatario, note);
		entityManager.persist(richiestaAmicizia);
	}
	
	/* (non-Javadoc)
	 * @see it.swimv2.controller.IRichiestaAmicizia#rimuoviRichiestaAmicizia(int)
	 */
	@Override
	public void rimuoviRichiestaAmicizia(int id){
		RichiestaAmicizia temp = entityManager.find(RichiestaAmicizia.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(temp);
		entityManager.getTransaction().commit();		
	}
	
	public RichiestaAmicizia getRichiestaAmicizia(int mittente, int destinatario){
		Query query = entityManager.createNamedQuery("RichiestaAmicizia.getRichiesteAmiciziePerMittenteEDestinatario")
				.setParameter("idRichiedente", mittente).setParameter("idDestinatario", destinatario);
		List<?> risultatoQuery = query.getResultList();
		return (RichiestaAmicizia) risultatoQuery.get(0);
	}
		
	
}
