package it.swimv2.entities.remoteEntities;

public interface ISuggerimentoAmicizia {

	public String getDestinatario();

	public String getSuggerito();

	/**
	 * @param utente
	 *            : ID utente da verificare se � presente nell'entit�
	 * @return true se � presente
	 */
	public boolean utentePresente(String utente);

}