package it.swimv2.controller.remoteController;

import it.swimv2.entities.remoteEntities.IAbilita;
import it.swimv2.util.InvioRichiestaAbilitaEnum;
import it.swimv2.util.ManutenzioneAbilitaEnum;

import javax.ejb.Remote;

@Remote
public interface IManutenzioneAbilitaUtente {

	public InvioRichiestaAbilitaEnum inviareRichiestaAbilita(
			String nomeAbilita, String descrizione, String username);

	public ManutenzioneAbilitaEnum rimuoverePropriaAbilita(String nomeAbilita,
			String username);

	public IAbilita[] getTutteLeAbilita();
	
	public IAbilita[] getProprieAbilita(String username);
}
