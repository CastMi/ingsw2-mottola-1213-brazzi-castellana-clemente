package it.swimv2.entities;

import it.swimv2.entities.remoteEntities.ISuggerimentoAmicizia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(	name = "SuggerimentoAmicizia" )
@IdClass(SuggerimentoAmiciziaPK.class)
public class SuggerimentoAmicizia implements Serializable, ISuggerimentoAmicizia {

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

	/* (non-Javadoc)
	 * @see it.swimv2.entities.ISuggerimentoAmicizia#getDestinatario()
	 */
	@Override
	public int getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(int destinatario) {
		this.destinatario = destinatario;
	}

	/* (non-Javadoc)
	 * @see it.swimv2.entities.ISuggerimentoAmicizia#getSuggerito()
	 */
	@Override
	public int getSuggerito() {
		return suggerito;
	}

	public void setSuggerito(int suggerito) {
		this.suggerito = suggerito;
	}

	/* (non-Javadoc)
	 * @see it.swimv2.entities.ISuggerimentoAmicizia#utentePresente(int)
	 */
	@Override
	public boolean utentePresente(int utente) {
		return (this.destinatario == utente || this.suggerito == utente);

	}

}