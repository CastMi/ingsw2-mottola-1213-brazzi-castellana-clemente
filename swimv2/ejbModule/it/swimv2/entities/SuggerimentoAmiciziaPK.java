package it.swimv2.entities;

import java.io.Serializable;

public class SuggerimentoAmiciziaPK implements Serializable {

	private static final long serialVersionUID = -336246507697779943L;

	private int destinatario;

	private int suggerito;

	public SuggerimentoAmiciziaPK() {
		super();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SuggerimentoAmiciziaPK))
			return false;
		SuggerimentoAmiciziaPK pk = (SuggerimentoAmiciziaPK) obj;
		return pk.destinatario == destinatario && pk.suggerito == suggerito;
	}

}
