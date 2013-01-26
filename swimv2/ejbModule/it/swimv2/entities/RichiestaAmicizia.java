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
		@NamedQuery(name = "RichiestaAmicizia.getRichiesteAmiciziePerIdUtente", query = "SELECT r FROM RichiestaAmicizia r WHERE r.destinatario = :userName"),
		@NamedQuery(name = "RichiestaAmicizia.getRichiesteAmiciziePerMittenteEDestinatario", query = "SELECT r FROM RichiestaAmicizia r WHERE r.destinatario = :destinatario AND r.richiedente = :richiedente"),
		@NamedQuery(name = "RichiestaAmicizia.getProssimoIdRichiestaAmicizia", query = "SELECT MAX(r.id)+1 as max_id FROM RichiestaAmicizia r") })
//TODO è suggerita?
@Entity
@Table(name = "RichiestaAmicizia")
public class RichiestaAmicizia implements Serializable, IRichiestaAmicizia {

	private static final long serialVersionUID = -3464474854877853025L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int idRichiestaAmicizia;

	@Column(name = "destinatario")
	private String destinatario;

	@Column(name = "richiedente")
	private String richiedente;
	
	@Column(name = "suggerita")
	private boolean suggerita;
	
	public boolean isSuggerita() {
		return suggerita;
	}

	@Lob
	@Column(name = "note")
	private String note;

	public RichiestaAmicizia(String idRichiedente, String idDestinatario, String note, boolean suggerita) {
		this.richiedente = idRichiedente;
		this.destinatario = idDestinatario;
		this.note = note;
		this.suggerita = suggerita;
	}
	
	public RichiestaAmicizia() {
		super();
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
