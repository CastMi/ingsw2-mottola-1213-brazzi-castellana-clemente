package it.swimv2.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Daniele
 * 
 */
@NamedQueries({
	@NamedQuery(name = "Amministratore.getAmministratore", 
			query = "SELECT a FROM Amministratore a")
})
@Entity
@Table(name = "Amministratore")
public class Amministratore {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "password", nullable = false)
	private String password;

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

}
