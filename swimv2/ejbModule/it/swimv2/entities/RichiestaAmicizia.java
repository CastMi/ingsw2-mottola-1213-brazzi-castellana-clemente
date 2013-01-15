package it.swimv2.entities;

import javax.persistence.Entity;
import javax.persistence.Column;
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
		@NamedQuery(name = "RichiestaAmicizia.getTutteRichiesteAmicizia", query = "SELECT r FROM richiestaamicizia r"),
		@NamedQuery(name = "RichiestaAmicizia.getRichiesteAmiciziePerIdUtente", query = "SELECT r FROM richiestaamicizia r WHERE r.idDestinatario = :idUtente"),
		@NamedQuery(name = "RichiestaAmicizia.getAmiciziaPerIdRichiestaAmicizia", query = "SELECT r FROM richiestaamicizia r WHERE r.id = :id"),
		@NamedQuery(name = "RichiestaAmicizia.getProssimoIdRichiestaAmicizia", query = "(SELECT MAX(id) FROM richiestaamicizia)+1"),
		// Query di inserimento entità
		@NamedQuery(name = "RichiestaAmicizia.inserisciNuovaRichiestaAmicizia", query = "INSERT INTO richiestaamicizia VALUES (id, idMittente, idDestinatario, note)"),
		// Query di eliminazione entità
		@NamedQuery(name = "RichiestaAmicizia.eliminaRichiestaAmicizia", query = "DELETE FROM amicizia a WHERE a.id = :id")})

@Entity
public class RichiestaAmicizia {
	
	@PersistenceContext(unitName = "swimv2DB")
	private EntityManager entityManager;

	@Column(name="idDestinatario")
	private String idDestinatario;
	
	@Column(name="idMittente")
	private String idRichiedente;
	
	@Column(name="id")
	private int idRichiestaAmicizia;
	
	@Column(name="note")
	private String note;

	public RichiestaAmicizia(String idRichiedente, String idDestinatario, String note) {
		this.idRichiedente = idRichiedente;
		this.idDestinatario = idDestinatario;
		this.idRichiestaAmicizia = (int) entityManager.createNamedQuery("RichiestaAmicizia.getProssimoIdRichiestaAmicizia").getResultList().get(0);
		this.note = note;
	}

	public String getIdDestinatario() {
		return idDestinatario;
	}
	
	public String getNote() {
		return note;
	}

	public String getIdRichiedente() {
		return idRichiedente;
	}

	public long getIdRichiestaAmicizia() {
		return idRichiestaAmicizia;
	}
	
}
