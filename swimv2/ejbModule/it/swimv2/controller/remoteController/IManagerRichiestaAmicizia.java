package it.swimv2.controller.remoteController;

import it.swimv2.entities.RichiestaAmicizia;

import javax.ejb.Remote;

@Remote
public interface IManagerRichiestaAmicizia {

	public void creaNuovaRichiestaAmicizia(String idMittente,
			String idDestinatario, String note);
	
	public void creaNuovaRichiestaAmiciziaTramiteSuggerimento(String mittente,
			String destinatario, String note);

	public void rimuoviRichiestaAmicizia(int idRichiestaAmicizia);
	
	public RichiestaAmicizia[] getTutteRichiesteAmiciziaPerUtente(String utente);

}