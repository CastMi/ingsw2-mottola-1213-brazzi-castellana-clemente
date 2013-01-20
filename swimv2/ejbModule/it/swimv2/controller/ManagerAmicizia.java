package it.swimv2.controller;

import it.swimv2.controller.remoteController.IAmicizia;
import it.swimv2.entities.Amicizia;
import it.swimv2.entities.RichiestaAmicizia;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Daniele
 * 
 */
public class ManagerAmicizia implements IAmicizia {

	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see it.swimv2.controller.IAmicizia#creaAmicizia(it.swimv2.entities.RichiestaAmicizia)
	 */
	@Override
	public void creaAmicizia(RichiestaAmicizia richiestaAmicizia) {
		Amicizia amicizia = new Amicizia(richiestaAmicizia.getIdRichiedente(),
				richiestaAmicizia.getIdDestinatario());
		// aggiungo la nuova amicizia
		entityManager.getTransaction().begin();
		entityManager.persist(amicizia);
		entityManager.getTransaction().commit();
	}
	
}
