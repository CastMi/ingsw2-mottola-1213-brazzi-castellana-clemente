package it.swimv2.controller;

import it.swimv2.controller.remoteController.IManagerSuggerimentoAmicizia;
import it.swimv2.entities.Amicizia;
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


	public void gestioneSuggerimento(String utente1, String utente2,
			Amicizia amicizia) {

		List<Amicizia> amiciUtente1 = getQuerySetAmiciziePerUtente(utente1);
		List<Amicizia> amiciUtente2 = getQuerySetAmiciziePerUtente(utente2);
			List<Amicizia> suggerimentiAUtente1 = new ArrayList<Amicizia>();
		if (amiciUtente2.size() != 0) {
			suggerimentiAUtente1.addAll(amiciUtente2);
			suggerimentiAUtente1.removeAll(amiciUtente1);
			suggerimentiAUtente1.remove(amicizia);
		}
		List<Amicizia> suggerimentiAUtente2 = new ArrayList<Amicizia>();
		if (amiciUtente1.size() != 0) {
			suggerimentiAUtente2.addAll(amiciUtente1);
			suggerimentiAUtente2.removeAll(amiciUtente2);
			suggerimentiAUtente2.remove(amicizia);
		}
		creaSuggerimento(utente1, utente2, suggerimentiAUtente1);
		creaSuggerimento(utente2, utente1, suggerimentiAUtente2);

	}

	private void creaSuggerimento(String utente, String utente2,
			List<Amicizia> suggerimentiAUtente) {
		SuggerimentoAmicizia suggerimento = new SuggerimentoAmicizia();
		for(Amicizia a : suggerimentiAUtente){
			suggerimento.setDestinatario(utente);
			suggerimento.setSuggerito(getSuggerito(a, utente2));
			entityManager.persist(suggerimento);
		}
	}

	private String getSuggerito(Amicizia amicizia, String utente2) {
		if (amicizia.getIdUtente1().equals(utente2))
			return amicizia.getIdUtente2();
		return amicizia.getIdUtente1();
	}

	@SuppressWarnings("unchecked")
	private List<Amicizia> getQuerySetAmiciziePerUtente(String utente) {
		Query query = entityManager
				.createNamedQuery("Amicizia.getAmiciziePerIdUtente");
		query.setParameter("idUtente", utente);
		List<Amicizia> amiciUtente = (List<Amicizia>) query.getResultList();
		return amiciUtente;
	}
	
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
