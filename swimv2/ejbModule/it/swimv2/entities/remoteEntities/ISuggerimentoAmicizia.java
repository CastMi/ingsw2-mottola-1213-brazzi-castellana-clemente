package it.swimv2.entities.remoteEntities;

public interface ISuggerimentoAmicizia {

	public int getDestinatario();

	public int getSuggerito();

	/**
	 * @param utente
	 *            : ID utente da verificare se è presente nell'entità
	 * @return true se è presente
	 */
	public boolean utentePresente(int utente);

}