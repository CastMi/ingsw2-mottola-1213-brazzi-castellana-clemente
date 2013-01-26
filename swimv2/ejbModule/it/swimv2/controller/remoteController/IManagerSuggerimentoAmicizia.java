package it.swimv2.controller.remoteController;

import javax.ejb.Remote;

@Remote
public interface IManagerSuggerimentoAmicizia {
	
	public String[] ottieniSuggerimenti(String nomeUtente);

}