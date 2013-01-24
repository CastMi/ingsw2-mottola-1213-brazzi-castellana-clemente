package it.swimv2.entities.remoteEntities;

public interface ISuggerimentoAmicizia {

	public String getDestinatario();

	public String getSuggerito();

	/**
	 * @param utente
	 *            : ID utente da verificare se è presente nell'entità
	 * @return true se è presente
	 */
	public boolean utentePresente(String utente);

}