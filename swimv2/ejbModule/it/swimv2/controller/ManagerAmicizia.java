package it.swimv2.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.swimv2.controller.remoteController.IManagerAmicizia;
import it.swimv2.entities.Amicizia;
import it.swimv2.entities.AmiciziaPK;
import it.swimv2.entities.RichiestaAmicizia;
import it.swimv2.entities.SuggerimentoAmicizia;
import it.swimv2.entities.remoteEntities.IAmicizia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		String idRichiedente = new String();
		String idDestinatario = new String();
		boolean suggerita;
		try {
			richiestaAmicizia = entityManager.find(RichiestaAmicizia.class,
					idRichiestaAmicizia);
			suggerita = richiestaAmicizia.isSuggerita();
		} catch (Exception e) {
			return;
		}
		idRichiedente = richiestaAmicizia.getIdRichiedente();
		idDestinatario = richiestaAmicizia.getIdDestinatario();
		Amicizia amicizia = new Amicizia(idRichiedente,	idDestinatario);
		
		entityManager.persist(amicizia);
		entityManager.remove(richiestaAmicizia);
		entityManager.flush();
		if (!suggerita) {
			gestioneSuggerimento(
					idRichiedente,idDestinatario);
		}
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

	public boolean rimuoviAmicizia(String utenteA, String utenteB) {
		Amicizia amiciziaAB;

		try {
			amiciziaAB = entityManager.find(Amicizia.class, new AmiciziaPK(
					utenteA, utenteB));
		} catch (Exception e) {
			amiciziaAB = null;
		}

		try {
			this.entityManager.remove(amiciziaAB);
			this.entityManager.flush();
		} catch (Exception r) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IAmicizia[] tuttiGliAmici(String utente) {
		Query query = entityManager
				.createNamedQuery("Amicizia.getAmiciziePerIdUtente");

		query.setParameter("idUtente", utente);

		List<Amicizia> listaRis = null;
		try {
			listaRis = (List<Amicizia>) query.getResultList();
		} catch (Exception e) {
			return null;
		}
		if (listaRis.size() == 0)
			return null;

		return (IAmicizia[]) listaRis.toArray(new IAmicizia[listaRis.size()]);
	}
	
	public void gestioneSuggerimento(String utente1, String utente2) {

		Set<String> amiciUtente1 = getQuerySetAmiciziePerUtente(utente1);
		Set<String> amiciUtente2 = getQuerySetAmiciziePerUtente(utente2);
		Set<String> suggerimentiAUtente1 = new HashSet<String>();
		if (amiciUtente2.size() != 0) {
			suggerimentiAUtente1.addAll(amiciUtente2);
			suggerimentiAUtente1.removeAll(amiciUtente1);
			suggerimentiAUtente1.remove(utente1);
			suggerimentiAUtente1.remove(utente2);
			suggerimentiAUtente1.removeAll(tuttiISuggerimentiDiUtente(utente1));
		}
		Set<String> suggerimentiAUtente2 = new HashSet<String>();
		if (amiciUtente1.size() != 0) {
			suggerimentiAUtente2.addAll(amiciUtente1);
			suggerimentiAUtente2.removeAll(amiciUtente2);
			suggerimentiAUtente2.remove(utente1);
			suggerimentiAUtente2.remove(utente2);
			suggerimentiAUtente2.removeAll(tuttiISuggerimentiDiUtente(utente2));
		}
		creaSuggerimento(utente1, suggerimentiAUtente1);
		creaSuggerimento(utente2, suggerimentiAUtente2);

	}
	@SuppressWarnings("unchecked")
	private Set<String> tuttiISuggerimentiDiUtente(String utente) {
		Query query = entityManager
				.createNamedQuery("SuggerimentoAmicizia.getSuggerimentoPerDestinatario");
		query.setParameter("nomeUtente", utente);
		List<SuggerimentoAmicizia> suggerimentiPerUtente = (List<SuggerimentoAmicizia>) query.getResultList();
		Set<String> nomiAmici = new HashSet<String>();
		if(suggerimentiPerUtente!=null){
		for(SuggerimentoAmicizia s: suggerimentiPerUtente){
			nomiAmici.add(s.getSuggerito());
		}}
		return nomiAmici;
	}

	@SuppressWarnings("unchecked")
	private Set<String> getQuerySetAmiciziePerUtente(String utente) {
		Query query = entityManager
				.createNamedQuery("Amicizia.getAmiciziePerIdUtente");
		query.setParameter("idUtente", utente);
		List<Amicizia> amiciUtente = (List<Amicizia>) query.getResultList();
		Set<String> nomiAmici = new HashSet<String>();
		if(amiciUtente!=null){
		
		for(Amicizia s:amiciUtente){
			if(s.getIdUtente1().equals(utente)){
				nomiAmici.add(s.getIdUtente2());
			}
			else{
				nomiAmici.add(s.getIdUtente1());
			}
		}
		}
		return nomiAmici;
	}
	
	private void creaSuggerimento(String utente,
			Set<String> suggerimentiAUtente1) {
		for(String a : suggerimentiAUtente1){
			SuggerimentoAmicizia suggerimento = new SuggerimentoAmicizia(utente, a);
			suggerimento.setDestinatario(utente);
			entityManager.persist(suggerimento);
		}
	}
}
