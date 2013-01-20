package it.swimv2.entities;

import java.io.Serializable;
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
import javax.persistence.Table;

@Entity
@Table(	name = "Domanda" )
public class Domanda implements Serializable {

	private static final long serialVersionUID = -4297181626893145903L;
	
	@Id
    @GeneratedValue
    @Column(name="id")
	private int id;
	
	@Column(name="title", nullable = false)
	private String titolo;
	
	@Lob
	@Column(name="description")	
	private String descrizione;
	
    @ManyToMany
    @JoinTable(name = "domanda_abilita",
        joinColumns = @JoinColumn(name = "domanda", referencedColumnName = "id"),
        inverseJoinColumns = {@JoinColumn(name = "abilita", referencedColumnName = "name")})
	private Set<Abilita> abilita;
    
    @ManyToOne(cascade=CascadeType.ALL, optional=false)
    @JoinColumn(name="creatore", nullable=false)
	private Utente creatore;
    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}


	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Set<Abilita> getAbilita() {
		return abilita;
	}

	public void setAbilita(Set<Abilita> abilita) {
		this.abilita = abilita;
	}
	
	public void addAbilita(Abilita abilita) {
		this.getAbilita().add(abilita);
	}
	
	public void dropAbilita(Abilita toDrop) {
		this.getAbilita().remove(toDrop);
	}

	public Utente getCreatore() {
		return creatore;
	}

	public void setCreatore(Utente creatore) {
		this.creatore = creatore;
	}
}