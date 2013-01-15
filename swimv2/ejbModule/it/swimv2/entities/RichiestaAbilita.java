package it.swimv2.entities;

import it.swimv2.entities.remoteEntities.IRichiestaAbilita;

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
		// Query di estrazione dati
		@NamedQuery(name = "RichiestaAbilita.getRichiestaAbilita", query = "SELECT ra FROM RichiestaAbilita ra WHERE ra.nome = :nome AND "
				+ "ra.richiedente = :utente"),
		@NamedQuery(name = "RichiestaAbilita.getTutteLeRichiesteDiAbilita", query = "SELECT ra FROM RichiestaAbilita ra"),

		// Query per eliminare
		@NamedQuery(name = "RichiestaAbilita.elimina", query = "DELETE FROM RichiestaAbilita ra WHERE ra.richiedente = :utente AND "
				+ "ra.nome = :nome"), })
@Entity
@Table(name = "RichiestaAbilita", uniqueConstraints = @UniqueConstraint(columnNames = {
		"richiedente", "nome" }))
public class RichiestaAbilita implements Serializable, IRichiestaAbilita {

	@ManyToOne
	@JoinColumn(name = "richiedente", referencedColumnName = "id", nullable = false, updatable = false)
	private Utente richiedente;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Lob
	@Column(name = "descrizione")
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
	 * Restituisce un clone del richiedente (non si restituisce l'originale per
	 * questioni di sicurezza)
	 * 
	 * @return il richiedente dell'abilità
	 */
	public Utente getRichiedente() {
		return richiedente.clone();
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}
}
