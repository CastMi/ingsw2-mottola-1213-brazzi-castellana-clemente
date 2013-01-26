package it.swimv2.entities;

import it.swimv2.entities.remoteEntities.IAmicizia;

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
public class Amicizia implements Serializable, IAmicizia {
	
	private static final long serialVersionUID = 3181884424066437840L;

	@Id
	@Column(name="idUtente1")
	private String idUtente1;

	@Id
	@Column(name="idUtente2")
	private String idUtente2;
	
	public Amicizia() {
		super();
	}

	public Amicizia(String idUtente1, String idUtente2) {
		this.idUtente1 = idUtente1;
		this.idUtente2 = idUtente2;
	}
	
	/* (non-Javadoc)
	 * @see it.swimv2.entities.IAmicizia#getIdUtente1()
	 */
	@Override
	public String getIdUtente1() {
		return idUtente1;
	}

	public void setIdUtente1(String idUtente1) {
		this.idUtente1 = idUtente1;
	}

	/* (non-Javadoc)
	 * @see it.swimv2.entities.IAmicizia#getIdUtente2()
	 */
	@Override
	public String getIdUtente2() {
		return idUtente2;
	}

	public void setIdUtente2(String idUtente2) {
		this.idUtente2 = idUtente2;
	}
	
	/* (non-Javadoc)
	 * @see it.swimv2.entities.IAmicizia#utentePresente(String)
	 */
	@Override
	public boolean utentePresente(String utente) {
		return (this.idUtente1 == utente || this.idUtente2 == utente);

	}

}
