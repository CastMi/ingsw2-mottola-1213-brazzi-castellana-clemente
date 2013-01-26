package it.swimv2.entities;

import it.swimv2.entities.remoteEntities.IRichiestaAbilita;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;

/**
 * 
 * @author Michele
 * 
 */
@NamedQueries({
		// Query di estrazione dati
		@NamedQuery(name = "RichiestaAbilita.getRichiestaAbilita",
				query = "SELECT ra FROM RichiestaAbilita ra WHERE ra.nome = :nome AND ra.richiedente = :utente"),
		@NamedQuery(name = "RichiestaAbilita.getTutteLeRichiesteDiAbilita",
				query = "SELECT ra FROM RichiestaAbilita ra")
 })
@Entity
@Table(name = "RichiestaAbilita")
@IdClass(RichiestaAbilitaPK.class)
public class RichiestaAbilita implements Serializable, IRichiestaAbilita {

	private static final long serialVersionUID = 707389346008617812L;

	@Id
	@Column(name = "richiedente")
	private String richiedente;

	@Id
	@Column(name = "nome")
	private String nome;

	@Lob
	@Column(name = "descrizione")
	private String descrizione;

	public RichiestaAbilita() {
		super();
	}
	
	public RichiestaAbilita(String richiedente, String nome, String descrizione) {
		super();
		this.richiedente = richiedente;
		this.nome = nome;
		this.descrizione = descrizione;
	}

	/**
	 * 
	 * @param richiedente
	 */
	public void setRichiedente(String richiedente) {
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
	 * Restituisce un clone del richiedente (non si restituisce l'originale per
	 * questioni di sicurezza)
	 * 
	 * @return il richiedente dell'abilità
	 */
	public String getRichiedente() {
		return richiedente;
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}
}
