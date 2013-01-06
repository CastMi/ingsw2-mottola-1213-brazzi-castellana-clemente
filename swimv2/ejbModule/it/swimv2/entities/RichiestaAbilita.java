package it.swimv2.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author Michele
 *
 */
@SuppressWarnings("serial")
@NamedQueries({ 
	//Query di estrazione dati	
	@NamedQuery(name = "RichiestaAbilita.getRichiestaAbilita",
			query = "SELECT ra FROM RichiestaAbilita ra WHERE ra.nome = :nome AND " +
					"ra.richiedente = :utente"),
	
	//Query per eliminare
	@NamedQuery(name="RichiestaAbilita.Elimina",
					query="DELETE FROM RichiestaAbilita ra WHERE ra.richiedente = :utente AND " +
						  "ra.nome = :nome"),
	})
@Entity
@Table(	name = "RichiestaAbilita", uniqueConstraints= @UniqueConstraint( columnNames={"richiedente", "nome"}) )
public class RichiestaAbilita implements Serializable {

	@ManyToOne
	@JoinColumn(name = "richiedente", referencedColumnName = "id", nullable = false, updatable = false)
	private Utente richiedente;
	
	@Column(name="nome", nullable= false)
	private String nome;
	
	@Lob
	@Column(name="descrizione")
	private String descrizione;
	
	/**
	 * 
	 * @param richiedente
	 */
	public void setRichiedente(Utente richiedente) {
		this.richiedente = richiedente;
	}

	/**
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * 
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * 
	 * @return il richiedente
	 */
	public Utente getRichiedente() {
		return richiedente;
	}

	/**
	 * 
	 * @return il nome
	 */	
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * @return la descrizione
	 */	
	public String getDescrizione() {
		return descrizione;
	}
}
