package it.swimv2.util;

/**
 * @author Daniele
 *
 */
public class ErroriRegistrazione {
	
	public boolean ErroreNome;
	public boolean ErroreCognome;
	public boolean ErroreEmail;
	public boolean ErrorePassword;
	public boolean ErroreId;
	
	/**
	 * @return
	 */
	public boolean loginValido(){
		return !(ErroreNome || ErroreCognome || ErroreEmail || ErrorePassword || ErroreId);
	}

}
