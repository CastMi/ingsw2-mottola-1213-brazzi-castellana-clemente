package it.swimv2.entities.remoteEntities;

import it.swimv2.entities.Domanda;
import it.swimv2.entities.Utente;
import java.util.Date;

public interface IRisposta {

	public int getId();

	public Domanda getDomanda();

	public Utente getUtente();

	public String getDescrizione();

	public Date getData();

	public int getFeedback();

}
