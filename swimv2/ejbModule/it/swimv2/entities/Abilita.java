package it.swimv2.entities;

import it.swimv2.entities.remoteEntities.IAbilita;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
		// Query di estrazione dati
		@NamedQuery(name = "Abilita.getTutteLeAbilita", query = "SELECT a FROM Abilita a"), })
@Entity
@Table(name = "Abilita")
public class Abilita implements Serializable, IAbilita {

	private static final long serialVersionUID = -8113622545375125271L;

	@Id
	@Column(name = "name")
	private String nome;

	@Lob
	@Column(name = "description")
	private String descrizione;

	public Abilita() {
		super();
	}
	
	
	
	public Abilita(String nome, String descrizione) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
	}



	public String getNome() {
		return nome;
	}
	
	/**
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * 
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
