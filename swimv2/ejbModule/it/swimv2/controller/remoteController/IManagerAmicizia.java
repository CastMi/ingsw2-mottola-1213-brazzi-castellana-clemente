package it.swimv2.controller.remoteController;

import javax.ejb.Remote;

@Remote
public interface IManagerAmicizia {

	public void creaAmicizia(int idRichiestaAmicizia);

}