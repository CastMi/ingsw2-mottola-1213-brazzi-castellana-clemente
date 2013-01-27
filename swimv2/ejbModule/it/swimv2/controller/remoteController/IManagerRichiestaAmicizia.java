package it.swimv2.controller.remoteController;

import javax.ejb.Remote;

@Remote
public interface IManagerRichiestaAmicizia {

	public void creaNuovaRichiestaAmicizia(String idMittente,
			String idDestinatario, String note);
	
	public void creaNuovaRichiestaAmiciziaTramiteSuggerimento(String mittente,
			String destinatario, String note);

	public void rimuoviRichiestaAmicizia(int id);
	
	public String[] getTutteRichiesteAmiciziaPerUtente(String utente);

}