package it.swimv2.controller.remoteController;

import javax.ejb.Remote;

@Remote
public interface IManagerAmicizia {

	public void creaAmicizia(String mittente, String destinatario, String note, boolean suggerita);

}