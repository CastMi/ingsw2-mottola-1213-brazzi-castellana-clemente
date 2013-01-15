package it.swimv2.controller.remoteController;

import it.swimv2.entities.remoteEntities.IRichiestaAbilita;
import it.swimv2.util.ManutenzioneAbilitaEnum;
import it.swimv2.util.ManutenzioneRichiestaAbilitaEnum;

import javax.ejb.Remote;

@Remote
public interface IManutenzioneAbilitaAmministratore {

	public ManutenzioneRichiestaAbilitaEnum accettareRichiestaAbilita(String nomeAbilita, long idUtente);
	
	public ManutenzioneRichiestaAbilitaEnum rifiutareRichiestaAbilita(String nomeAbilita, long idUtente);
	
	public ManutenzioneAbilitaEnum rimuovereAbilita(String nomeAbilita, long idUtente);
	
	public IRichiestaAbilita[] getTutteLeRichiesteDiAbilita();
	
}
