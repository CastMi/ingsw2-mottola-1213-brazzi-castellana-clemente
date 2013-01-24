package it.swimv2.entities;

import java.io.Serializable;

public class SuggerimentoAmiciziaPK implements Serializable {

	private static final long serialVersionUID = -336246507697779943L;

	private String destinatario;

	private String suggerito;

	public SuggerimentoAmiciziaPK(String destinatario, String suggerito) {
		this.destinatario = destinatario;
		this.suggerito = suggerito;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SuggerimentoAmiciziaPK))
			return false;
		SuggerimentoAmiciziaPK pk = (SuggerimentoAmiciziaPK) obj;
		return pk.destinatario.equals(destinatario)
				&& pk.suggerito.equals(suggerito);
	}

}
