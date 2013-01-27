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
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Stateless
public class ManagerSuggerimentoAmicizia implements Serializable,
		IManagerSuggerimentoAmicizia {

	private static final long serialVersionUID = 829024488493804653L;
	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;


	public void gestioneSuggerimento(String utente1, String utente2,
			Amicizia amicizia) {

		Set<String> amiciUtente1 = getQuerySetAmiciziePerUtente(utente1);
		Set<String> amiciUtente2 = getQuerySetAmiciziePerUtente(utente2);
		Set<String> suggerimentiAUtente1 = new HashSet<String>();
		if (amiciUtente2.size() != 0) {
			suggerimentiAUtente1.addAll(amiciUtente2);
			suggerimentiAUtente1.removeAll(amiciUtente1);
			suggerimentiAUtente1.remove(utente1);
			suggerimentiAUtente1.remove(utente2);
		}
		Set<String> suggerimentiAUtente2 = new HashSet<String>();
		if (amiciUtente1.size() != 0) {
			suggerimentiAUtente2.addAll(amiciUtente1);
			suggerimentiAUtente2.removeAll(amiciUtente2);
			suggerimentiAUtente2.remove(utente1);
			suggerimentiAUtente2.remove(utente2);
		}
		creaSuggerimento(utente1, utente2, suggerimentiAUtente1);
		creaSuggerimento(utente2, utente1, suggerimentiAUtente2);

	}

	@SuppressWarnings("unused")
	private void creaSuggerimento(String utente, String utente2,
			Set<String> suggerimentiAUtente1) {
		for(String a : suggerimentiAUtente1){
			SuggerimentoAmicizia suggerimento = new SuggerimentoAmicizia();
			suggerimento.setDestinatario(utente);
			entityManager.persist(suggerimento);
		}
	}

	@SuppressWarnings("unchecked")
	private Set<String> getQuerySetAmiciziePerUtente(String utente) {
		Query query = entityManager
				.createNamedQuery("Amicizia.getAmiciziePerIdUtente");
		query.setParameter("idUtente", utente);
		List<Amicizia> amiciUtente = (List<Amicizia>) query.getResultList();
		Set<String> nomiAmici = new HashSet<String>();
		for(Amicizia s:amiciUtente){
			if(s.getIdUtente1().equals(utente)){
				nomiAmici.add(s.getIdUtente1());
			}
			else{
				nomiAmici.add(s.getIdUtente2());
			}
		}
		return nomiAmici;
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
