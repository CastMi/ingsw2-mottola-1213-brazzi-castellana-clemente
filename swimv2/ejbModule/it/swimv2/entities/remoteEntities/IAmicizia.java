package it.swimv2.entities.remoteEntities;

public interface IAmicizia {

	public String getIdUtente1();

	public String getIdUtente2();

	/**
	 * @param utente
	 *            : ID utente da verificare se è presente nell'entità
	 * @return true se è presente
	 */
	public boolean utentePresente(String utente);

}