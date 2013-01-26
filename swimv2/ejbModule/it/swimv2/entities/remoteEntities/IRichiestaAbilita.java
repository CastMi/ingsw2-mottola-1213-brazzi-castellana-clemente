package it.swimv2.entities.remoteEntities;

public interface IRichiestaAbilita {

	/**
	 * 
	 * @return il richiedente dell'abilit�
	 */
	public IUtente getRichiedente();

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
