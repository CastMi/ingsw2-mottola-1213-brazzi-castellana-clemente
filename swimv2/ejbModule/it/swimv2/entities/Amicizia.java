package it.swimv2.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;

/**
 * @author Daniele
 * 
 */

@NamedQueries({
		// Query di estrazione dati
		@NamedQuery(name = "Amicizia.getTutteAmicizie", query = "SELECT a FROM amicizia a"),
		@NamedQuery(name = "Amicizia.getAmiciziePerIdUtente", query = "SELECT a FROM amicizia a WHERE a.idUtente1 = :idUtente OR SELECT a FROM amicizia a WHERE a.idUtente = :idUtente"),
		@NamedQuery(name = "Amicizia.getAmiciziaPerIdAmicizia", query = "SELECT a FROM amicizia a WHERE a.id = :id"),
		@NamedQuery(name = "Amicizia.getProssimoIdAmicizia", query = "(SELECT MAX(id) FROM amicizia)+1"),
		// Query di inserimento
		@NamedQuery(name = "Amicizia.inserisciNuovaAmicizia", query = "INSERT INTO amicizia VALUES (id, idUtente1, idUtente2)") })

@Entity
public class Amicizia {
	
	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;

    @Column(name="id")
	private int idAmicizia;
	
	@Column(name="idUtente1", nullable= false)
	private String idUtente1;
	
	@Column(name="idUtente2", nullable= false)
	private String idUtente2;

	/**
	 * @param idUtente1
	 * @param idUtente2
	 */
	public Amicizia(String idUtente1, String idUtente2) {
		this.idUtente1 = idUtente1;
		this.idUtente2 = idUtente2;
		this.idAmicizia = (int) entityManager.createNamedQuery("Amicizia.getProssimoIdAmicizia").getResultList().get(0);
	}

	/**
	 * @return
	 */
	public int getIdAmicizia() {
		return idAmicizia;
	}

	/**
	 * @return
	 */
	public String getIdUtente1() {
		return idUtente1;
	}

	/**
	 * @return
	 */
	public String getIdUtente2() {
		return idUtente2;
	}

	/**
	 * @param utente
	 *            : ID utente da verificare se è presente nell'entità
	 * @return true se è presente
	 */
	public boolean utentePresente(String utente) {

		return (idUtente1 == utente || idUtente2 == utente);

	}

}
