package it.swimv2.util;

import it.swimv2.controller.remoteController.IAmicizia;
import it.swimv2.controller.remoteController.ILogin;
import it.swimv2.controller.remoteController.IManagerDomanda;
import it.swimv2.controller.remoteController.IManagerRisposta;
import it.swimv2.controller.remoteController.IManutenzioneAbilitaAmministratore;
import it.swimv2.controller.remoteController.IManutenzioneAbilitaUtente;
import it.swimv2.controller.remoteController.IRegistrazione;
import it.swimv2.controller.remoteController.IRicercaUtenti;
import it.swimv2.controller.remoteController.IRichiestaAmicizia;

import javax.naming.NamingException;

public interface IFactory {

	public IAmicizia getManagerAmicizia() throws NamingException,
			ClassCastException;

	public ILogin getManagerLogin() throws NamingException, ClassCastException;

	public IManutenzioneAbilitaAmministratore getManutentoreAmministratore()
			throws NamingException, ClassCastException;

	public IManutenzioneAbilitaUtente getManutentoreUtente()
			throws NamingException, ClassCastException;

	public IRegistrazione getGestoreRegistrazione() throws NamingException,
			ClassCastException;

	public IRicercaUtenti getRicercaUtenti() throws NamingException,
			ClassCastException;

	public IRichiestaAmicizia getRichiestaAmicizia() throws NamingException,
			ClassCastException;

	public IManagerDomanda getManagerDomanda() throws NamingException,
			ClassCastException;

	public IManagerRisposta getManagerRisposta() throws NamingException,
			ClassCastException;

}