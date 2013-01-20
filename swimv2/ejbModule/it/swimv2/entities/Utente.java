package it.swimv2.entities;

import it.swimv2.entities.remoteEntities.IUtente;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@NamedQueries({ 
	//Query di estrazione dati
	@NamedQuery(name = "Utente.getTuttiIUtentiDisponibili",
			query = "SELECT u FROM Utente u"),
	@NamedQuery(name = "Utente.getUtentePerId",
			query = "SELECT u FROM Utente u WHERE u.id = :id"),
	@NamedQuery(name = "Utente.getUtentiPerNome",
			query = "SELECT u FROM Utente u WHERE u.nome = :nome"),
	@NamedQuery(name = "Utente.getUtentiPerCognome",
			query = "SELECT u FROM Utente u WHERE u.cognome = :cognome"),
	@NamedQuery(name = "Utente.getUtentiPerUsername",
			query = "SELECT u FROM Utente u WHERE u.username = :username"),
	@NamedQuery(name = "Utente.getUtentePerEmail",
			query = "SELECT u FROM Utente u WHERE u.email = :email")
	})
@Entity
@Table(	name = "Utente" )
public class Utente implements Serializable, IUtente, Cloneable {

	// non modificare il column name perchè viene utilizzato da "RichiestaAbilita"
	// nella relazione "ManyToOne"
	@Id
    @GeneratedValue
    @Column(name="id")
	private int id;
	
	@Column(name="name", nullable= false)
	private String nome;
	
	@Column(name="surname", nullable= false)
	private String cognome;
	
	@Column(name="username", unique=true, nullable= false)
	private String username;
	
	@Column(name="password", nullable= false)
	private String password;

	@Column(name="email", unique=true, nullable= false)
	private String email;
	
	// FIXME da ricontrollare questa relazione manytomany
	@ManyToMany
	@JoinTable(
			name = "Associazione_Utente_Abilita",
			joinColumns = @JoinColumn (name="utente_id"),
			inverseJoinColumns = @JoinColumn (name="abilita_name")
			)
	private Set<Abilita> abilita;
	
	
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
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
     * @param abi - l'abilità da aggiungere
     * @return true se e solo se l'abilità non appartiene già all'utente e se viene aggiunta correttamente.
     */
    public boolean AggiungiAbilità(Abilita abi)
    {
            return this.abilita.add(abi);
    }
    
    /**
     * Controlla se l'abilità è tra quelle possedute dell'utente
     * @param abi - l'abilità da controllare
     * @return true se e solo se l'abilità è posseduta dall'utente.
     */
    public boolean possiedeAbilita(Abilita abi)
    {
            return this.abilita.contains(abi);
    }
    
    /**
     * Rimuove l'abilità da quelle possedute dell'utente
     * @param abi - l'abilità da rimuovere
     * @return true se e solo se l'abilità appartiene all'utente e se viene rimossa correttamente.
     */
    public boolean RimuoviAbilità(Abilita abi)
    {
            return this.abilita.remove(abi);
    }
    
    public Utente clone() {
    	try {
			return (Utente) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
    }
}