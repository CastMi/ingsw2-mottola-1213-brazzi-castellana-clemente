package it.swimv2.controller;

import it.swimv2.controller.remoteController.IManagerAmicizia;
import it.swimv2.entities.Amicizia;
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

	// FIXME USARE LO USERNAME COME INPUT DELLE FUNZIONI PERCHE' L'ID NON
	// ESISTE PIU'

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.swimv2.controller.IAmicizia#creaAmicizia(it.swimv2.entities.
	 * RichiestaAmicizia)
	 */
	@Override
	public void creaAmicizia(int mittente, int destinatario) {
		ManagerRichiestaAmicizia managerRichiestaAmicizia = new ManagerRichiestaAmicizia();
		RichiestaAmicizia richiestaAmicizia = managerRichiestaAmicizia
				.getRichiestaAmicizia(mittente, destinatario);
		Amicizia amicizia = new Amicizia(richiestaAmicizia.getIdRichiedente(),
				richiestaAmicizia.getIdDestinatario());
		managerRichiestaAmicizia.rimuoviRichiestaAmicizia(richiestaAmicizia
				.getIdRichiestaAmicizia());
		// aggiungo la nuova amicizia
		entityManager.getTransaction().begin();
		entityManager.persist(amicizia);
		entityManager.getTransaction().commit();
	}

}
