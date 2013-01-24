package it.swimv2.entities.remoteEntities;

public interface ISuggerimentoAmicizia {

	public int getDestinatario();

	public int getSuggerito();

	/**
	 * @param utente
	 *            : ID utente da verificare se � presente nell'entit�
	 * @return true se � presente
	 */
	public boolean utentePresente(int utente);

}