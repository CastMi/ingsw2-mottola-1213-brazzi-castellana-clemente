package it.swimv2.controller;

import it.swimv2.controller.remoteController.IManagerSuggerimentoAmicizia;

import it.swimv2.entities.SuggerimentoAmicizia;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Stateless
public class ManagerSuggerimentoAmicizia implements Serializable,
		IManagerSuggerimentoAmicizia {

	private static final long serialVersionUID = 829024488493804653L;
	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;
	
	public String[] ottieniSuggerimenti(String nomeUtente){
		List<SuggerimentoAmicizia> listaSuggerimenti = getListaSuggerimentiPerUtente(nomeUtente); 
		return getSuggeritiDaSuggerimenti(listaSuggerimenti);
	}
	

	private String[] getSuggeritiDaSuggerimenti(
			List<SuggerimentoAmicizia> listaSuggerimenti) {
		List<String> listaUtentiSuggeriti = new ArrayList<String>();
		for(SuggerimentoAmicizia l: listaSuggerimenti){
			listaUtentiSuggeriti.add(l.getSuggerito());
		}

		return (String[]) listaUtentiSuggeriti.toArray(new String[listaUtentiSuggeriti.size()]);
	}

	@SuppressWarnings("unchecked")
	private List<SuggerimentoAmicizia> getListaSuggerimentiPerUtente(String utente) {
		Query query = entityManager
				.createNamedQuery("SuggerimentoAmicizia.getSuggerimentoPerDestinatario");
		query.setParameter("nomeUtente", utente);
		List<SuggerimentoAmicizia> amiciUtente = (List<SuggerimentoAmicizia>) query.getResultList();
		return amiciUtente;
	}

}
