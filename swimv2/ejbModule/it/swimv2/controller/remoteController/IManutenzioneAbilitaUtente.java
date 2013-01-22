package it.swimv2.controller.remoteController;

import it.swimv2.util.ManutenzioneAbilitaEnum;
import it.swimv2.util.ManutenzioneRichiestaAbilitaEnum;

import javax.ejb.Remote;

@Remote
public interface IManutenzioneAbilitaUtente {

	public ManutenzioneRichiestaAbilitaEnum inviareRichiestaAbilita(
			String nomeAbilita, String descrizione, String username);

	public ManutenzioneAbilitaEnum rimuoverePropriaAbilita(String nomeAbilita,
			String username);

}
