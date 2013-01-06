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

@SuppressWarnings("serial")
@NamedQueries({ 
	//Query di estrazione dati	
	@NamedQuery(name = "Abilita.getAbilitaPerNome",
			query = "SELECT a FROM Abilita a WHERE a.nome = :nome"),
			
	//Query di update
	@NamedQuery(name="Abilita.eliminaAbilita",
			query = "DELETE FROM Abilita a WHERE a.nome = :nome")
	})
@Entity
@Table(	name = "Abilita" )
public class Abilita implements Serializable, IAbilita {
	
	@Id
	@Column(name="name", unique=true, nullable= false)
	private String nome;
	
	@Lob
	@Column(name="description")
	private String descrizione;
		
	/**
	 * 
	 * @return
	 */
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
		
	/**
	 * 
	 * @return
	 */
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
