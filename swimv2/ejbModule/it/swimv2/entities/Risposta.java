package it.swimv2.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(	name = "Risposta" )
public class Risposta implements Serializable {
	
	private static final long serialVersionUID = 4139265859940592845L;

	@Id
    @GeneratedValue
    @Column(name="id")
	private int id;
	
    @ManyToOne(cascade=CascadeType.ALL, optional=false)
    @JoinColumn(name="domanda", nullable=false)
	private Domanda domanda;
    
    @ManyToOne(cascade=CascadeType.ALL, optional=false)
    @JoinColumn(name="utente", nullable=false)
	private Utente utente;
    
	@Lob
	@Column(name="description")	
	private String descrizione;
	
	@Column(name="data")	
	private Date data;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Domanda getDomanda() {
		return domanda;
	}

	public void setDomanda(Domanda domanda) {
		this.domanda = domanda;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
}
