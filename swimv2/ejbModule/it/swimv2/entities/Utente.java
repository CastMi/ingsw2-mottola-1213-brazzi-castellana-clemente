package it.swimv2.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(	name = "UTENTE", uniqueConstraints = @UniqueConstraint( columnNames={"name"} ) )
public class Utente implements Serializable {

	private long id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String email;
	private Set<Abilita> abilita;
	
	@Id
    @GeneratedValue
    @Column(name="id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name="name", nullable= false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name="surname", nullable= false)
	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	@Column(name="username", unique=true, nullable= false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="email", unique=true, nullable= false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Abilita> getAbilita() {
		return abilita;
	}

	public void setAbilita(Set<Abilita> abilita) {
		this.abilita = abilita;
	}
	
	/**
	 * 
	 * @param nome
	 * @param cognome
	 * @param username
	 * @param password
	 * @param email
	 */
	public Utente(String nome, String cognome, String username, String password, String email)
	{
		// TODO
		// implementare l'id
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.email = email;
		this.abilita = new HashSet<Abilita>();
	}
	
	/**
	 * restituisce true se e solo se l'abilità non appartiene già
	 * all'utente e se viene aggiunta correttamente.
	 * @param abi
	 * @return
	 */
	public boolean AggiungiAbilità(Abilita abi)
	{
		return this.abilita.add(abi);
	}
	
	/**
	 * restituisce true
	 * @param nuovaMail
	 * @return
	 */
	public boolean ModificaMail(String nuovaMail)
	{
		if(this.email.equals(nuovaMail) ||
				nuovaMail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$;"))
			return false;
		this.email = nuovaMail;
		return true;
	}
	
	/**
	 * 
	 */
	public void RicercaDomande()
	{
		
	}
}
/*
sig Utente{
nome: Testo,
cognome: Testo,
username: Testo,
password: Testo,
email: Testo,
abilita: set Abilita
}
*/