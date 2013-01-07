package it.swimv2.controller.remoteController;

import javax.ejb.Remote;

@Remote
public interface IManutenzioneAbilitaUtente {

	public boolean inviareRichiestaAbilita(String nomeAbilita, String descrizione, long idUtente);
	
	public boolean rimuoverePropriaAbilita(String nomeAbilita, long idUtente);
	
}
