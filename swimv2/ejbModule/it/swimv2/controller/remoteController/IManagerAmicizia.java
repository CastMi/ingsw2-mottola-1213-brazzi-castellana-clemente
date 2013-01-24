package it.swimv2.controller.remoteController;

import javax.ejb.Remote;

@Remote
public interface IManagerAmicizia {

	public abstract void creaAmicizia(int mittente, int destinatario);

}