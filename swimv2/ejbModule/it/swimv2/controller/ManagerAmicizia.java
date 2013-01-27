package it.swimv2.controller;

import it.swimv2.controller.remoteController.IManagerAmicizia;
import it.swimv2.entities.Amicizia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Daniele
 * 
 */
@Stateless
public class ManagerAmicizia implements IManagerAmicizia {

	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.swimv2.controller.IAmicizia#creaAmicizia(it.swimv2.entities.
	 * RichiestaAmicizia)
	 */
	@Override
	public void creaAmicizia(String mittente, String destinatario, String note, boolean suggerita) {
		ManagerRichiestaAmicizia managerRichiestaAmicizia = new ManagerRichiestaAmicizia();
		Amicizia amicizia = new Amicizia(mittente,
				destinatario);
		managerRichiestaAmicizia.rimuoviRichiestaAmicizia(destinatario, mittente, note);
		// aggiungo la nuova amicizia
		entityManager.persist(amicizia);
		if(suggerita){
			ManagerSuggerimentoAmicizia managerSuggerimentoAmicizia = new ManagerSuggerimentoAmicizia();
			managerSuggerimentoAmicizia.gestioneSuggerimento(mittente, destinatario, amicizia);
		}
	}

}
