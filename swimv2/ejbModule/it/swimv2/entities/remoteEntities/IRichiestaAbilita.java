package it.swimv2.entities.remoteEntities;

public interface IRichiestaAbilita {

	/**
	 * 
	 * @return il richiedente dell'abilità
	 */
	public IUtente getRichiedente();

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
