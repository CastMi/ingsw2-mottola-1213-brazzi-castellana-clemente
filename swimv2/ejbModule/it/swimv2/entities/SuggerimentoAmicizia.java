package it.swimv2.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(	name = "SuggerimentoAmicizia" )
@IdClass(SuggerimentoAmiciziaPK.class)
public class SuggerimentoAmicizia implements Serializable {

	private static final long serialVersionUID = 5359986778176337837L;

	@Id
	@Column(name = "destinatario")
	private int destinatario;

	@Id
	@Column(name = "suggerito")
	private int suggerito;

	public SuggerimentoAmicizia(int destinatario, int suggerito) {
		this.destinatario = destinatario;
		this.suggerito = suggerito;
	}

	public int getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(int destinatario) {
		this.destinatario = destinatario;
	}

	public int getSuggerito() {
		return suggerito;
	}

	public void setSuggerito(int suggerito) {
		this.suggerito = suggerito;
	}

	/**
	 * @param utente
	 *            : ID utente da verificare se è presente nell'entità
	 * @return true se è presente
	 */
	public boolean utentePresente(int utente) {
		return (this.destinatario == utente || this.suggerito == utente);

	}

}