package it.swimv2.entities.remoteEntities;

import it.swimv2.entities.Abilita;
import it.swimv2.entities.Utente;

import java.util.Set;

public interface IDomanda {
	
	public int getId();

	public String getTitolo();
	
	public String getDescrizione();

	public Set<Abilita> getAbilita();

	public Utente getCreatore();
	
}
