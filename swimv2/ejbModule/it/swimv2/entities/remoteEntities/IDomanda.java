package it.swimv2.entities.remoteEntities;

import java.util.Date;

public interface IDomanda {
	
	public int getId();

	public String getTitolo();
	
	public String getDescrizione();

	public IAbilita[] getAbilita();

	public IUtente getCreatore();
	
	public Date getData();
	
	
}
