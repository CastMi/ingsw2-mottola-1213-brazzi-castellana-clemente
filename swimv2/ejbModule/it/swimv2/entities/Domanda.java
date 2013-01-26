package it.swimv2.entities;

import it.swimv2.entities.remoteEntities.IAbilita;
import it.swimv2.entities.remoteEntities.IDomanda;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Domanda")
@NamedQueries({
		// Query di estrazione dati
		@NamedQuery(name = "Domanda.proprieDomande", query = "SELECT d FROM Domanda d WHERE d.creatore=:utente"),
		@NamedQuery(name = "Domanda.ricercaDomande", query = "SELECT d FROM Domanda d WHERE d.titolo like :testo OR d.descrizione like :testo") })
public class Domanda implements Serializable, IDomanda {

	private static final long serialVersionUID = -4297181626893145903L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "title", nullable = false)
	private String titolo;

	@Lob
	@Column(name = "description")
	private String descrizione;

	@ManyToMany
	@JoinTable(name = "domanda_abilita", joinColumns = @JoinColumn(name = "domanda", referencedColumnName = "id"), inverseJoinColumns = { @JoinColumn(name = "abilita", referencedColumnName = "name") })
	private Set<Abilita> abilita;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "creatore", nullable = false)
	private Utente creatore;
	
	@Column(name = "data")
	private Date data;

	public Domanda() {
		super();
	}

	public Domanda(String titolo, String descrizione, Set<Abilita> abilita,
			Utente creatore) {
		super();
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.abilita = abilita;
		this.creatore = creatore;
		this.data = new Date();
	}
	
	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	@Override
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public IAbilita[] getAbilita() {
		return (IAbilita[]) abilita.toArray(new IAbilita[abilita.size()]);
	}

	public void setAbilita(Set<Abilita> abilita) {
		this.abilita = abilita;
	}

	public void addAbilita(Abilita abilita) {
		this.abilita.add(abilita);
	}

	public void dropAbilita(Abilita toDrop) {
		this.abilita.remove(toDrop);
	}

	@Override
	public Utente getCreatore() {
		return creatore;
	}

	public void setCreatore(Utente creatore) {
		this.creatore = creatore;
	}
	
	@Override
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}