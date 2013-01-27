package it.swimv2.controller.remoteController;

import it.swimv2.entities.remoteEntities.IAmicizia;

import javax.ejb.Remote;

@Remote
public interface IManagerAmicizia {

	public void creaAmicizia(int idRichiestaAmicizia);

	public boolean sonoAmici(String utenteA, String utenteB);

	public IAmicizia[] tuttiGliAmici(String utente);
	
	public boolean rimuoviAmicizia(String utenteA, String utenteB);
}