package it.swimv2.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author Michele
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(	name = "RICHIESTAABILITA", uniqueConstraints= @UniqueConstraint( columnNames={"richiedente", "nome"}) )
public class RichiestaAbilita implements Serializable {

	private Utente richiedente;
	private String nome;
	private String descrizione;
	
	/**
	 * 
	 * @return
	 */
	@Column(name="richiedente", nullable= false)
	public Utente getRichiedente() {
		return richiedente;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name="nome", nullable= false)
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * @return
	 */
	@Lob
	@Column(name="descrizione")
	public String getDescrizione() {
		return descrizione;
	}
	
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
