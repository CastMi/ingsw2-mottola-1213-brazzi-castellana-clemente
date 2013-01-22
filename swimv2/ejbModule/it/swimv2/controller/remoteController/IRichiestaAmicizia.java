package it.swimv2.controller.remoteController;

import javax.ejb.Remote;

@Remote
public interface IRichiestaAmicizia {

	public abstract void creaNuovaRichiestaAmicizia(int idMittente,
			int idDestinatario, String note);

	public abstract void rimuoviRichiestaAmicizia(int id);

}