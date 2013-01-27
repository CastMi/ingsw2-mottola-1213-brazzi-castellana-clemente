package it.swimv2.controller;

import it.swimv2.controller.remoteController.IManagerRichiestaAmicizia;
import it.swimv2.entities.RichiestaAmicizia;
import it.swimv2.entities.SuggerimentoAmicizia;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.swimv2.controller.IRichiestaAmicizia#creaNuovaRichiestaAmicizia(String
	 * , String, java.lang.String)
	 */
	@Override
	public void creaNuovaRichiestaAmicizia(String mittente,
			String destinatario, String note) {
		RichiestaAmicizia richiestaAmicizia = new RichiestaAmicizia(mittente,
				destinatario, note, false);
		entityManager.persist(richiestaAmicizia);
	}
	
	@Override
	public void creaNuovaRichiestaAmiciziaTramiteSuggerimento(String mittente,
			String destinatario, String note) {
		RichiestaAmicizia richiestaAmicizia = new RichiestaAmicizia(mittente,
				destinatario, note, true);
		entityManager.persist(richiestaAmicizia);
		SuggerimentoAmicizia sugg;
		try {
			sugg = entityManager.find(SuggerimentoAmicizia.class, destinatario);
		} catch (Exception e) {
			return;
		}
		try {
			entityManager.remove(sugg);
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.swimv2.controller.IRichiestaAmicizia#rimuoviRichiestaAmicizia(int)
	 */
	@Override
	public void rimuoviRichiestaAmicizia(int id) {
		RichiestaAmicizia temp;
		try {
			temp = entityManager.find(RichiestaAmicizia.class, id);
		} catch (Exception e) {
			return;
		}
		try {
			entityManager.remove(temp);
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}

	public RichiestaAmicizia getRichiestaAmicizia(String mittente,
			String destinatario) {
		Query query = entityManager
				.createNamedQuery(
						"RichiestaAmicizia.getRichiesteAmiciziePerMittenteEDestinatario")
				.setParameter("idRichiedente", mittente)
				.setParameter("idDestinatario", destinatario);

		return (RichiestaAmicizia) query.getSingleResult();
	}

}
