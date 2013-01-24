package it.swimv2.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Daniele
 * 
 */

@NamedQueries({
		// Query di estrazione dati
		@NamedQuery(name = "Amicizia.getTutteAmicizie", 
				query = "SELECT a FROM Amicizia a"),
		@NamedQuery(name = "Amicizia.getAmiciziePerIdUtente", 
				query = "SELECT a FROM Amicizia a WHERE a.idUtente1 = :idUtente OR a.idUtente2 = :idUtente"),
		@NamedQuery(name = "Amicizia.getAmicizia", 
				query = "SELECT a FROM Amicizia a WHERE a.idUtente1 = :idUtente1 AND a.idUtente2 = :idUtente2"),
 })
@Entity
@Table(	name = "Amicizia" )
@IdClass(AmiciziaPK.class)
public class Amicizia implements Serializable {
	
	private static final long serialVersionUID = 3181884424066437840L;

	@Id
	@Column(name="idUtente1")
	private int idUtente1;

	@Id
	@Column(name="idUtente2")
	private int idUtente2;
	
	public Amicizia(int idUtente1, int idUtente2) {
		this.idUtente1 = idUtente1;
		this.idUtente2 = idUtente2;
	}
	
	public int getIdUtente1() {
		return idUtente1;
	}

	public void setIdUtente1(int idUtente1) {
		this.idUtente1 = idUtente1;
	}

	public int getIdUtente2() {
		return idUtente2;
	}

	public void setIdUtente2(int idUtente2) {
		this.idUtente2 = idUtente2;
	}
	
	/**
	 * @param utente
	 *            : ID utente da verificare se è presente nell'entità
	 * @return true se è presente
	 */
	public boolean utentePresente(int utente) {
		return (this.idUtente1 == utente || this.idUtente2 == utente);

	}

}
