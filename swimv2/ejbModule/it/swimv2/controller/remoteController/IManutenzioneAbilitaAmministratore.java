package it.swimv2.controller.remoteController;

import it.swimv2.entities.remoteEntities.IRichiestaAbilita;
import it.swimv2.util.ManutenzioneAbilitaEnum;
import it.swimv2.util.ManutenzioneRichiestaAbilitaEnum;

import javax.ejb.Remote;

@Remote
public interface IManutenzioneAbilitaAmministratore {

	public ManutenzioneRichiestaAbilitaEnum accettareRichiestaAbilita(
			String nomeRichiestaAbilita, String username);

	public ManutenzioneRichiestaAbilitaEnum rifiutareRichiestaAbilita(
			String nomeRichiestaAbilita, String username);

	public ManutenzioneAbilitaEnum rimuovereAbilita(String nomeAbilita,
			String username);

	public IRichiestaAbilita[] getTutteLeRichiesteDiAbilita();

}
