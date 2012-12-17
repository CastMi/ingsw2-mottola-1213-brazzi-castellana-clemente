package it.swimv2.entities;
/**
 * 
 * @author Michele
 *
 */
public class RichiestaAbilita {

	private Utente richiedente;
	private String nome;
	private String descrizione;
	
	/**
	 * 
	 * @param richiedente
	 * @param nome
	 * @param descrizione
	 */
	public RichiestaAbilita(Utente richiedente, String nome, String descrizione)
	{
		this.richiedente = richiedente;
		this.nome = nome;
		this.descrizione = descrizione;
	}
	
	/**
	 * 
	 */
	public void Accettata()
	{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 */
	public void Rifiutata()
	{
		throw new UnsupportedOperationException();
	}
}
