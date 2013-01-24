package it.swimv2.entities;

import it.swimv2.entities.remoteEntities.IRisposta;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Risposta")
@NamedQueries({
		// Query di estrazione dati
		@NamedQuery(name = "Risposta.ricercaRisposte", query = "SELECT r FROM Risposta r WHERE r.descrizione like :testo"),
		@NamedQuery(name = "Risposta.getRisposteByDomanda", query = "SELECT r FROM Risposta r WHERE r.domanda=:domanda") })
public class Risposta implements Serializable, IRisposta {

	private static final long serialVersionUID = 4139265859940592845L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "domanda", nullable = false)
	private Domanda domanda;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "utente", nullable = false)
	private Utente utente;

	@Lob
	@Column(name = "description")
	private String descrizione;

	@Column(name = "data")
	private Date data;

	@Column(name = "feedback")
	private int feedback;

	public Risposta() {
		super();
	}

	public Risposta(Domanda domanda, Utente utente, String descrizione) {
		super();
		this.domanda = domanda;
		this.utente = utente;
		this.descrizione = descrizione;
		this.data = new Date();
		this.feedback = 0;
	}

	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public Domanda getDomanda() {
		return domanda;
	}

	public void setDomanda(Domanda domanda) {
		this.domanda = domanda;
	}

	@Override
	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	@Override
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public int getFeedback() {
		return feedback;
	}

	public void setFeedback(int feedback) {
		this.feedback = feedback;
	}

}
