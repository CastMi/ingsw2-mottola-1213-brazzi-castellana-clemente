package it.swimv2.controller.remoteController;

import it.swimv2.entities.Domanda;
import it.swimv2.entities.Risposta;
import it.swimv2.entities.Utente;

import javax.ejb.Remote;

@Remote
public interface IManagerRisposta {

	public Risposta[] ricercaRisposta(String testo);

	public Risposta[] getRisposteByDomanda(Domanda domanda);

	public boolean rilasciaFeedback(Risposta risposta, int voto);

	public boolean aggiungiRispsota(Domanda domanda, Utente utente,
			String risposta);

}
