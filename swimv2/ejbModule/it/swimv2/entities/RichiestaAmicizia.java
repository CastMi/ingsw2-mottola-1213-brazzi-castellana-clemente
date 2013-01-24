package it.swimv2.entities;

import it.swimv2.entities.remoteEntities.IRichiestaAmicizia;

import java.io.Serializable;

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
		@NamedQuery(name = "RichiestaAmicizia.getRichiesteAmiciziePerMittenteEDestinatario", query = "SELECT r FROM RichiestaAmicizia r WHERE r.idDestinatario = :idDestinatario AND r.idRichiedente = :idRichiedente "),
		@NamedQuery(name = "RichiestaAmicizia.getProssimoIdRichiestaAmicizia", query = "SELECT MAX(r.id)+1 as max_id FROM RichiestaAmicizia r") })
@Entity
@Table(name = "RichiestaAmicizia")
public class RichiestaAmicizia implements Serializable, IRichiestaAmicizia {

	private static final long serialVersionUID = -3464474854877853025L;

	// FIXME NON ESISTE PIU' L'ID DELL'UTENTE QUINDI idDestinatario E
	// idRichiedente SONO SBAGLIATI, USARE LO USERNAME DELL'UTENTE
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int idRichiestaAmicizia;

	@Column(name = "destinatario")
	private String destinatario;

	@Column(name = "richiedente")
	private String richiedente;

	@Lob
	@Column(name = "note")
	private String note;

	public RichiestaAmicizia(String idRichiedente, String idDestinatario, String note) {
		this.richiedente = idRichiedente;
		this.destinatario = idDestinatario;
		this.note = note;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.swimv2.entities.IRichiestaAmicizia#getIdDestinatario()
	 */
	@Override
	public String getIdDestinatario() {
		return destinatario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.swimv2.entities.IRichiestaAmicizia#getNote()
	 */
	@Override
	public String getNote() {
		return note;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.swimv2.entities.IRichiestaAmicizia#getIdRichiedente()
	 */
	@Override
	public String getIdRichiedente() {
		return richiedente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.swimv2.entities.IRichiestaAmicizia#getIdRichiestaAmicizia()
	 */
	@Override
	public int getIdRichiestaAmicizia() {
		return idRichiestaAmicizia;
	}

}
