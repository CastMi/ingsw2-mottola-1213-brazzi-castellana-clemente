package it.swimv2.entities;

import it.swimv2.entities.remoteEntities.IAmministratore;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Daniele
 * 
 */
@Entity
@Table(name = "Amministratore")
public class Amministratore implements Serializable, IAmministratore {

	private static final long serialVersionUID = 5968228689127557217L;

	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "password", nullable = false)
	private String password;
	
	public Amministratore() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.swimv2.entities.IAmministratore#getId()
	 */
	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.swimv2.entities.IAmministratore#getPassword()
	 */
	@Override
	public String getPassword() {
		return password;
	}

}
