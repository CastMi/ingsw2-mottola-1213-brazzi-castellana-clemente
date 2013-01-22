package it.swimv2.entities;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Daniele
 * 
 */

@NamedQueries({
		// Query di estrazione dati
		@NamedQuery(name = "RichiestaAmicizia.getTutteRichiesteAmicizia", query = "SELECT r FROM RichiestaAmicizia r"),
		@NamedQuery(name = "RichiestaAmicizia.getRichiesteAmiciziePerIdUtente", query = "SELECT r FROM RichiestaAmicizia r WHERE r.idDestinatario = :idUtente"),
		@NamedQuery(name = "RichiestaAmicizia.getAmiciziaPerIdRichiestaAmicizia", query = "SELECT r FROM RichiestaAmicizia r WHERE r.id = :id"),
		@NamedQuery(name = "RichiestaAmicizia.getRichiesteAmiciziePerMittenteEDestinatario", query = "SELECT r FROM RichiestaAmicizia r WHERE r.idDestinatario = :idDestinatario AND r.idMittente = idMittente "),
		@NamedQuery(name = "RichiestaAmicizia.getProssimoIdRichiestaAmicizia", query = "SELECT MAX(r.id)+1 as max_id FROM RichiestaAmicizia r")
})

@Entity
@Table(name="RichiestaAmicizia")
public class RichiestaAmicizia {
	
	@Id
    @GeneratedValue
	@Column(name = "id")
	private int idRichiestaAmicizia;
	
	@Column(name="idDestinatario")
	private int idDestinatario;
	
	@Column(name="idMittente")
	private int idRichiedente;
	
	@Lob
	@Column(name="note")
	private String note;

	public RichiestaAmicizia(int idRichiedente, int idDestinatario, String note) {
		this.idRichiedente = idRichiedente;
		this.idDestinatario = idDestinatario;
		this.note = note;
	}

	public int getIdDestinatario() {
		return idDestinatario;
	}
	
	public String getNote() {
		return note;
	}

	public int getIdRichiedente() {
		return idRichiedente;
	}

	public int getIdRichiestaAmicizia() {
		return idRichiestaAmicizia;
	}
	
}
