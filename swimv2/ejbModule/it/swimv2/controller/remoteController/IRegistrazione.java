package it.swimv2.controller.remoteController;

import javax.ejb.Remote;

import it.swimv2.util.RegistrazioneEnum;

@Remote
public interface IRegistrazione {

	/**
	 * @param nome
	 * @param cognome
	 * @param email
	 * @param id
	 * @param password
	 * @param abilita 
	 * @return
	 */
	public abstract RegistrazioneEnum nuovaRegistrazione(String nome,
			String cognome, String email, String id, String password, String abilita);

}