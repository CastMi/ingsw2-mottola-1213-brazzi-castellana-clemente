package it.swimv2.entities;

import java.io.Serializable;

public class AmiciziaPK implements Serializable {

	private static final long serialVersionUID = -6735677119052181575L;

	private int idUtente1;

	private int idUtente2;
	
	public AmiciziaPK() {
		super();
	}
	
	@Override
	public boolean equals(Object obj) {
	      if (obj == this) return true;
	      if (!(obj instanceof AmiciziaPK)) return false;
	      AmiciziaPK pk = (AmiciziaPK) obj;
	      return pk.idUtente1 == idUtente1 && pk.idUtente2 == idUtente2;
	}

}
