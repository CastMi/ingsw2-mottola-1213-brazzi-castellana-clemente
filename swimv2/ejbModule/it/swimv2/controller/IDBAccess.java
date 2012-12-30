package it.swimv2.controller;
/**
 * 
 * @author Michele
 *
 */
public interface IDBAccess {

	/**
	 * ricerca utente
	 * @param nome
	 */
	public void getUtente(String nome);
	
	/**
	 * ricerca domanda
	 * @param nome
	 */
	public void getDomanda(String nome);
	
	/**
	 * ricerca abilità
	 * @param nome
	 */
	public void getAbilità(String nome);
	
	/**
	 * ricerca risposta
	 * @param nome
	 */
	public void getRisposta(String nome);
	
}
