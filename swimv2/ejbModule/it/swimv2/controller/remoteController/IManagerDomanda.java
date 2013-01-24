package it.swimv2.controller.remoteController;

import it.swimv2.entities.Abilita;
import it.swimv2.entities.Domanda;
import it.swimv2.entities.Utente;
import it.swimv2.entities.remoteEntities.IDomanda;

import java.util.Set;

import javax.ejb.Remote;

@Remote
public interface IManagerDomanda {
	public Domanda[] proprieDomande(Utente utente);

	public Domanda[] ricercaDomande(String testo);

	public IDomanda creaDomanda(Utente utente, String titolo, String domanda,
			Set<Abilita> abilita);

}
