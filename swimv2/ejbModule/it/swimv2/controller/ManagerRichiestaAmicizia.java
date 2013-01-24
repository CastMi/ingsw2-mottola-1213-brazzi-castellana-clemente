package it.swimv2.controller;

import it.swimv2.controller.remoteController.IManagerRichiestaAmicizia;
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
public class ManagerRichiestaAmicizia implements IManagerRichiestaAmicizia {

	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;

	// FIXME USARE LO USERNAME COME INPUT DELLE FUNZIONI PERCHE' L'ID NON
	// ESISTE PIU'

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.swimv2.controller.IRichiestaAmicizia#creaNuovaRichiestaAmicizia(String,
	 * String, java.lang.String)
	 */
	@Override
	public void creaNuovaRichiestaAmicizia(String idMittente, String idDestinatario,
			String note) {
		RichiestaAmicizia richiestaAmicizia = new RichiestaAmicizia(idMittente,
				idDestinatario, note);
		entityManager.getTransaction().begin();
		entityManager.persist(richiestaAmicizia);
		entityManager.getTransaction().commit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.swimv2.controller.IRichiestaAmicizia#rimuoviRichiestaAmicizia(int)
	 */
	@Override
	public void rimuoviRichiestaAmicizia(int id) {
		RichiestaAmicizia temp = entityManager
				.find(RichiestaAmicizia.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(temp);
		entityManager.getTransaction().commit();
	}

	public RichiestaAmicizia getRichiestaAmicizia(String mittente, String destinatario) {
		Query query = entityManager
				.createNamedQuery(
						"RichiestaAmicizia.getRichiesteAmiciziePerMittenteEDestinatario")
				.setParameter("idRichiedente", mittente)
				.setParameter("idDestinatario", destinatario);

		return (RichiestaAmicizia) query.getSingleResult();
	}

}
