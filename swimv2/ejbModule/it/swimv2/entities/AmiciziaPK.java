package it.swimv2.entities;

import java.io.Serializable;

public class AmiciziaPK implements Serializable {

	private static final long serialVersionUID = -6735677119052181575L;

	private String idUtente1;

	private String idUtente2;

	public AmiciziaPK(String idUtente1, String idUtente2) {
		this.idUtente1 = idUtente1;
		this.idUtente2 = idUtente2;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof AmiciziaPK))
			return false;
		AmiciziaPK pk = (AmiciziaPK) obj;
		return pk.idUtente1.equals(idUtente1) && pk.idUtente2.equals(idUtente2);
	}

}
