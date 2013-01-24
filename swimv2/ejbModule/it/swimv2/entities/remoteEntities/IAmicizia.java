package it.swimv2.entities.remoteEntities;

public interface IAmicizia {

	public int getIdUtente1();

	public int getIdUtente2();

	/**
	 * @param utente
	 *            : ID utente da verificare se � presente nell'entit�
	 * @return true se � presente
	 */
	public boolean utentePresente(int utente);

}