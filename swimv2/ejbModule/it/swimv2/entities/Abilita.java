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
	//Query di estrazione dati	
	@NamedQuery(name = "Abilita.getAbilitaPerNome",
			query = "SELECT a FROM Abilita a WHERE a.nome = :nome"),
})
@Entity
@Table(	name = "Abilita" )
public class Abilita implements Serializable, IAbilita {
	
	private static final long serialVersionUID = -8113622545375125271L;

	// FIXME qualora dia problemi, eliminare "unique=true" gia compreso perche chiave primaria
	@Id
	@Column(name="name")
	private String nome;
	
	@Lob
	@Column(name="description")
	private String descrizione;
	
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
