package it.swimv2.entities;

import it.swimv2.entities.remoteEntities.IUtente;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
		// Query di estrazione dati
		@NamedQuery(name = "Utente.getTuttiIUtentiDisponibili", query = "SELECT u FROM Utente u"),
		@NamedQuery(name = "Utente.getAbilitaUtente", query = "SELECT u.abilita FROM Utente u where u.username = :username"),
		@NamedQuery(name = "Utente.getUtentiPerNome", query = "SELECT u FROM Utente u WHERE u.nome = :nome"),
		@NamedQuery(name = "Utente.getUtentiPerCognome", query = "SELECT u FROM Utente u WHERE u.cognome = :cognome"),
		@NamedQuery(name = "Utente.getUtentePerEmail", query = "SELECT u FROM Utente u WHERE u.email = :email") })
@Entity
@Table(name = "Utente")
public class Utente implements Serializable, IUtente {

	private static final long serialVersionUID = 7553687756826590720L;

	@Column(name = "name", nullable = false)
	private String nome;

	@Column(name = "surname", nullable = false)
	private String cognome;

	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "Associazione_Utente_Abilita", joinColumns = @JoinColumn(name = "utente_id"), inverseJoinColumns = @JoinColumn(name = "abilita_name"))
	private Set<Abilita> abilita;

	public Utente(){
		super();
	}
	
	public Utente(String nome, String cognome, String username,
			String password, String email, Set<Abilita> abilita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.email = email;
		this.abilita = abilita;
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

	public String getCognome() {
		return cognome;
	}

	/**
	 * 
	 * @param cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Aggiunge l'abilità a quelle possedute dell'utente
	 * 
	 * @param abi
	 *            - l'abilità da aggiungere
	 * @return true se e solo se l'abilità non appartiene già all'utente e se
	 *         viene aggiunta correttamente.
	 */
	public boolean aggiungiAbilita(Abilita abi) {
		this.abilita.add(abi);
		return this.possiedeAbilita(abi);
	}

	/**
	 * Controlla se l'abilità è tra quelle possedute dell'utente
	 * 
	 * @param abi
	 *            - l'abilità da controllare
	 * @return true se e solo se l'abilità è posseduta dall'utente.
	 */
	private boolean possiedeAbilita(Abilita abi) {
		return this.abilita.contains(abi);
	}

	/**
	 * Rimuove l'abilità da quelle possedute dell'utente
	 * 
	 * @param abi
	 *            - l'abilità da rimuovere
	 * @return true se e solo se l'abilità appartiene all'utente e se viene
	 *         rimossa correttamente.
	 */
	public boolean RimuoviAbilità(Abilita abi) {
		return this.possiedeAbilita(abi) && this.abilita.remove(abi);
	}
	
	public Set<Abilita> getAbilita() {
		return abilita;
	}
}