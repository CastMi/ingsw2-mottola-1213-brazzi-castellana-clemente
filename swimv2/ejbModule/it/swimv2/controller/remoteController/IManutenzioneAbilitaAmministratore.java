package it.swimv2.controller.remoteController;

import it.swimv2.entities.remoteEntities.IRichiestaAbilita;

import javax.ejb.Remote;

@Remote
public interface IManutenzioneAbilitaAmministratore {

	public boolean accettareRichiestaAbilita(String nomeAbilita, long idUtente);
	
	public boolean rifiutareRichiestaAbilita(String nomeAbilita, long idUtente);
	
	public boolean rimuovereAbilita(String nomeAbilita, long idUtente);
	
	public IRichiestaAbilita[] getTutteLeRichiesteDiAbilita();
	
}
