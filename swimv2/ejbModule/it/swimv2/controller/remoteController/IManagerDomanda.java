package it.swimv2.controller.remoteController;

import it.swimv2.entities.remoteEntities.IDomanda;

import java.util.Set;

import javax.ejb.Remote;

@Remote
public interface IManagerDomanda {
	public IDomanda apriDomanda(int idDomanda);
	
	public IDomanda[] proprieDomande(String userName);

	public IDomanda[] ricercaDomande(String testo);

	public IDomanda creaDomanda(String userName, String titoloDomanda, String descrizioneDomanda,
			Set<String> abilita);

}
