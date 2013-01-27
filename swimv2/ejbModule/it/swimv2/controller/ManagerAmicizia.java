package it.swimv2.controller;

import it.swimv2.controller.remoteController.IManagerAmicizia;
import it.swimv2.entities.Amicizia;
import it.swimv2.entities.AmiciziaPK;
import it.swimv2.entities.RichiestaAmicizia;

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
	public void creaAmicizia(int idRichiestaAmicizia) {
		RichiestaAmicizia richiestaAmicizia = null;
		boolean suggerita = false;
		try {
			richiestaAmicizia = entityManager.find(RichiestaAmicizia.class,
					idRichiestaAmicizia);
			suggerita = richiestaAmicizia.isSuggerita();
		} catch (Exception e) {
			return;
		}

		Amicizia amicizia = new Amicizia(richiestaAmicizia.getIdRichiedente(),
				richiestaAmicizia.getIdDestinatario());
		entityManager.persist(amicizia);

		if (suggerita) {
			ManagerSuggerimentoAmicizia managerSuggerimentoAmicizia = new ManagerSuggerimentoAmicizia();
			managerSuggerimentoAmicizia.gestioneSuggerimento(
					richiestaAmicizia.getIdRichiedente(),
					richiestaAmicizia.getIdDestinatario(), amicizia);
		}
		entityManager.remove(richiestaAmicizia);
		entityManager.flush();
	}

	public boolean sonoAmici(String utenteA, String utenteB) {
		Amicizia amiciziaAB;
		Amicizia amiciziaBA;
		try {
			amiciziaAB = entityManager.find(Amicizia.class, new AmiciziaPK(
					utenteA, utenteB));
		} catch (Exception e) {
			amiciziaAB = null;
		}

		try {
			amiciziaBA = entityManager.find(Amicizia.class, new AmiciziaPK(
					utenteB, utenteA));
		} catch (Exception e) {
			amiciziaBA = null;
		}

		return (amiciziaAB != null || amiciziaBA != null);

	}
}
