package it.swimv2.controller.remoteController;

import javax.ejb.Remote;

import it.swimv2.entities.remoteEntities.IUtente;

@Remote
public interface IRicercaUtenti {

	public IUtente[] ricercaUtentiPerNome(String nome);
	
	public IUtente[] ricercaUtentiPerCognome(String nome);
	
	public IUtente ricercaUtentiPerUsername(String nome);
	
	public IUtente[] ricercaUtentiPerEmail(String nome);
}
