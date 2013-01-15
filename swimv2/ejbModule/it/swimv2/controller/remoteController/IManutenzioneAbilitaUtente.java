package it.swimv2.controller.remoteController;

import it.swimv2.util.ManutenzioneAbilitaEnum;
import it.swimv2.util.ManutenzioneRichiestaAbilitaEnum;

import javax.ejb.Remote;

@Remote
public interface IManutenzioneAbilitaUtente {

	public ManutenzioneRichiestaAbilitaEnum inviareRichiestaAbilita(String nomeAbilita, String descrizione, long idUtente);
	
	public ManutenzioneAbilitaEnum rimuoverePropriaAbilita(String nomeAbilita, long idUtente);
	
}
