package it.swimv2.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(	name = "ABILITA" )
public class Abilita implements Serializable {
	
	private long id;
	private String nome;
	private String descrizione;
	
    @Id
    @GeneratedValue
    @Column(name="id")
	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="name", unique=true, nullable= false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Lob
	@Column(name="description")
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
