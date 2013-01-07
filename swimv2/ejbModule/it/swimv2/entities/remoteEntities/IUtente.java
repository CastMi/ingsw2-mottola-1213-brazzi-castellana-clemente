package it.swimv2.entities.remoteEntities;

public interface IUtente {
	
	/**
	 * 
	 * @return l'id dell'utente
	 */
	public long getId();
	
	/**
	 * 
	 * @return il nome dell'utente
	 */
	public String getNome();
	
	/**
	 * 
	 * @return il cognome dell'utente
	 */
	public String getCognome();
	
	/**
	 * 
	 * @return la password dell'utente
	 */
	public String getPassword();
	
	/**
	 * 
	 * @return lo username dell'utente
	 */
	public String getUsername();
	
	/**
	 * 
	 * @return la mail dell'utente
	 */
	public String getEmail();
}
