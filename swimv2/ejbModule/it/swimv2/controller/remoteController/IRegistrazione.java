package it.swimv2.controller.remoteController;

import it.swimv2.util.ErroriRegistrazione;

public interface IRegistrazione {

	/**
	 * @param nome
	 * @param cognome
	 * @param email
	 * @param id
	 * @param password
	 * @return
	 */
	public abstract ErroriRegistrazione nuovaRegistrazione(String nome,
			String cognome, String email, String id, String password);

}