package it.swimv2.entities;

/**
 * @author Daniele
 *
 */
public class Amicizia {
	
	private static long ultimoIdAmicizia;
	private long idAmicizia;
	private String idUtente1;
	private String idUtente2;
	
	/**
	 * @param idUtente1
	 * @param idUtente2
	 */
	public void Amicizia(String idUtente1, String idUtente2){
		this.idUtente1=idUtente1;
		this.idUtente2=idUtente2;
		idAmicizia=ultimoIdAmicizia++;
	}

}
