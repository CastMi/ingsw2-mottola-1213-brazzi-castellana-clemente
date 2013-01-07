package it.swimv2.entities.remoteEntities;

import it.swimv2.entities.Utente;

public interface IRichiestaAbilita {

	/**
	 * 
	 * @return il richiedente dell'abilità
	 */
	public Utente getRichiedente();

	/**
	 * 
	 * @return il nome dell'abilità
	 */
	public String getNome();

	/**
	 * 
	 * @return la descrizione dell'abilità
	 */	
	public String getDescrizione();
}
