package it.swimv2.entities.remoteEntities;

import it.swimv2.entities.Utente;

public interface IRichiestaAbilita {

	/**
	 * 
	 * @return il richiedente dell'abilit�
	 */
	public Utente getRichiedente();

	/**
	 * 
	 * @return il nome dell'abilit�
	 */
	public String getNome();

	/**
	 * 
	 * @return la descrizione dell'abilit�
	 */	
	public String getDescrizione();
}
