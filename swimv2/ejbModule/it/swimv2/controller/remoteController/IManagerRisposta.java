package it.swimv2.controller.remoteController;

import it.swimv2.entities.remoteEntities.IRisposta;

import javax.ejb.Remote;

@Remote
public interface IManagerRisposta {

	public IRisposta apriRisposta(int idRisposta);

	public IRisposta[] ricercaRisposta(String testo);

	public IRisposta[] getRisposteByDomanda(int domanda);

	public boolean rilasciaFeedback(int risposta, String userName, int voto);

	public IRisposta aggiungiRispsota(int domanda, String userName,
			String risposta);

}
