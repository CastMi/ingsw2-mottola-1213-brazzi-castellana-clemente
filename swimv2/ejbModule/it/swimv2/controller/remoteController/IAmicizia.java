package it.swimv2.controller.remoteController;

import javax.ejb.Remote;

@Remote
public interface IAmicizia {

	public abstract void creaAmicizia(int mittente, int destinatario);

}