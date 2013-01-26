package it.swimv2.entities;

import it.swimv2.entities.remoteEntities.ISuggerimentoAmicizia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@NamedQueries({
	// Query di estrazione dati
	@NamedQuery(name = "SuggerimentoAmicizia.getSuggerimentoPerDestinatario", 
			query = "SELECT s FROM SuggerimentoAmicizia s WHERE s.destinatario = :nomeUtente")
})

@Entity
@Table(	name = "SuggerimentoAmicizia" )
@IdClass(SuggerimentoAmiciziaPK.class)
public class SuggerimentoAmicizia implements Serializable, ISuggerimentoAmicizia {

	private static final long serialVersionUID = 5359986778176337837L;
	
	/**il destinatario è colui che riceve il suggerimento d'amicizia
	 * 
	 */
	@Id
	@Column(name = "destinatario")
	private String destinatario;

	@Id
	@Column(name = "suggerito")
	private String suggerito;
	
	public SuggerimentoAmicizia() {
		super();
	}

	public SuggerimentoAmicizia(String destinatario, String suggerito) {
		this.destinatario = destinatario;
		this.suggerito = suggerito;
	}

	/* (non-Javadoc)
	 * @see it.swimv2.entities.ISuggerimentoAmicizia#getDestinatario()
	 */
	@Override
	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/* (non-Javadoc)
	 * @see it.swimv2.entities.ISuggerimentoAmicizia#getSuggerito()
	 */
	@Override
	public String getSuggerito() {
		return suggerito;
	}

	public void setSuggerito(String suggerito) {
		this.suggerito = suggerito;
	}

	/* (non-Javadoc)
	 * @see it.swimv2.entities.ISuggerimentoAmicizia#utentePresente(String)
	 */
	@Override
	public boolean utentePresente(String utente) {
		return (this.destinatario == utente || this.suggerito == utente);

	}

}