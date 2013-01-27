package it.swimv2.controller;

import java.util.List;

import it.swimv2.controller.remoteController.IManagerRichiestaAmicizia;
import it.swimv2.entities.RichiestaAmicizia;
import it.swimv2.entities.SuggerimentoAmicizia;
import it.swimv2.entities.SuggerimentoAmiciziaPK;

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
		entityManager.flush();
	}
	
	@Override
	public void creaNuovaRichiestaAmiciziaTramiteSuggerimento(String mittente,
			String destinatario, String note) {
		RichiestaAmicizia richiestaAmicizia = new RichiestaAmicizia(mittente,
				destinatario, note, true);
		entityManager.persist(richiestaAmicizia);
		SuggerimentoAmicizia app = entityManager.find(SuggerimentoAmicizia.class, new SuggerimentoAmiciziaPK(mittente, destinatario));
		entityManager.remove(app);
		entityManager.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.swimv2.controller.IRichiestaAmicizia#rimuoviRichiestaAmicizia(int)
	 */
	@Override
	public void rimuoviRichiestaAmicizia(int idRichiestaAmicizia) {
		RichiestaAmicizia temp;
		try {
			temp = entityManager.find(RichiestaAmicizia.class, idRichiestaAmicizia);
		} catch (Exception e) {
			return;
		}
		try {
			entityManager.remove(temp);
			entityManager.flush();
		} catch (Exception e) {
			return;
		}

	}

	public RichiestaAmicizia getRichiestaAmicizia(String mittente,
			String destinatario) {
		Query query = entityManager
				.createNamedQuery(
						"RichiestaAmicizia.getRichiesteAmiciziePerMittenteEDestinatario")
				.setParameter("idRichiedente", mittente)
				.setParameter("idDestinatario", destinatario);
		
		return (RichiestaAmicizia) query.getResultList().get(0);
	}

	
	@SuppressWarnings("unchecked")
	public RichiestaAmicizia[] getTutteRichiesteAmiciziaPerUtente(String utente){
		Query query = entityManager
				.createNamedQuery(
						"RichiestaAmicizia.getRichiesteAmiciziePerIdUtente")
				.setParameter("userName", utente);
		List<RichiestaAmicizia> listaRichieste = (List<RichiestaAmicizia>) query.getResultList();
		
		return (RichiestaAmicizia[]) listaRichieste.toArray(new RichiestaAmicizia[listaRichieste.size()]);
	}
	
	@SuppressWarnings("unchecked")
	public boolean hannoRichiesteInCorso(String utenteA,String utenteB){
		Query query = entityManager
				.createNamedQuery(
						"RichiestaAmicizia.getRichiesteAmicizieABBA");
		query.setParameter("utenteA", utenteA);
		query.setParameter("utenteB", utenteB);
		List<RichiestaAmicizia> listaRichieste = (List<RichiestaAmicizia>) query.getResultList();
		if(listaRichieste==null || listaRichieste.size()==0){
			return false;
		}
		return true;
	}
}
