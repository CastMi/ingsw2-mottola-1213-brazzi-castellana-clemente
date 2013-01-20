package it.swimv2.entities;

import java.io.Serializable;

public class RichiestaAbilitaPK implements Serializable {

	private static final long serialVersionUID = 2836709770823328844L;

	private int richiedente;

	private String nome;
	
	public RichiestaAbilitaPK() {
		super();
	}
	
	@Override
	public boolean equals(Object obj) {
	      if (obj == this) return true;
	      if (!(obj instanceof RichiestaAbilitaPK)) return false;
	      RichiestaAbilitaPK pk = (RichiestaAbilitaPK) obj;
	      return pk.richiedente == richiedente && pk.nome == nome;
	}
}

