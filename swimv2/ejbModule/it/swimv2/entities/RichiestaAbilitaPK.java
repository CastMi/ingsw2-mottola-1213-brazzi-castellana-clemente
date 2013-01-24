package it.swimv2.entities;

import java.io.Serializable;

public class RichiestaAbilitaPK implements Serializable {

	private static final long serialVersionUID = 2836709770823328844L;

	private String richiedente;

	private String nome;

	public RichiestaAbilitaPK(String nomeRichiestaAbilita,
			String usernameRichiedente) {
		this.nome = nomeRichiestaAbilita;
		this.richiedente = usernameRichiedente;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof RichiestaAbilitaPK))
			return false;
		RichiestaAbilitaPK pk = (RichiestaAbilitaPK) obj;
		return pk.richiedente.equals(this.richiedente)
				&& pk.nome.equals(this.nome);
	}
}
