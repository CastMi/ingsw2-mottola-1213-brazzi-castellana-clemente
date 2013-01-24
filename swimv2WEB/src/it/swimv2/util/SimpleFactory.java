package it.swimv2.util;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import it.swimv2.controller.remoteController.IAmicizia;
import it.swimv2.controller.remoteController.ILogin;
import it.swimv2.controller.remoteController.IManutenzioneAbilitaAmministratore;
import it.swimv2.controller.remoteController.IManutenzioneAbilitaUtente;
import it.swimv2.controller.remoteController.IRegistrazione;
import it.swimv2.controller.remoteController.IRicercaUtenti;
import it.swimv2.controller.remoteController.IRichiestaAmicizia;
import it.swimv2.util.ContextUtil;

public class SimpleFactory implements IFactory {

	private final String ManagerAmicizia = "ManagerAmicizia";
	private final String ManagerLogin = "ManagerLogin";
	private final String ManutenzioneAbilitaAmministratore = "ManagerManutenzioneAbilitaAmministratore";
	private final String ManutenzioneAbilitaUtente = "ManagerManutenzioneAbilitaUtente";
	private final String ManagerRegistrazione = "ManagerRegistrazione";
	private final String ManagerRicerca = "ManagerRicercaUtenti";
	private final String ManagerRichiestaAmicizia = "ManagerRichiestaAmicizia";

	private Object cercaClasseConcreta(String nomeClasseConcreta)
			throws NamingException {
		return ContextUtil.getInitialContext().lookup(
				"swimv2EAR/" + nomeClasseConcreta + "/remote");
	}

	@Override
	public IAmicizia getManagerAmicizia() throws NamingException,
			ClassCastException {
		Object obj = this.cercaClasseConcreta(this.ManagerAmicizia);

		return (IAmicizia) PortableRemoteObject.narrow(obj, ILogin.class);
	}

	@Override
	public ILogin getManagerLogin() throws NamingException, ClassCastException {
		Object obj = this.cercaClasseConcreta(this.ManagerLogin);

		return (ILogin) PortableRemoteObject.narrow(obj, ILogin.class);
	}

	@Override
	public IManutenzioneAbilitaAmministratore getManutentoreAmministratore()
			throws NamingException, ClassCastException {
		Object obj = this
				.cercaClasseConcreta(this.ManutenzioneAbilitaAmministratore);

		return (IManutenzioneAbilitaAmministratore) PortableRemoteObject
				.narrow(obj, ILogin.class);
	}

	@Override
	public IManutenzioneAbilitaUtente getManutentoreUtente()
			throws NamingException, ClassCastException {
		Object obj = this.cercaClasseConcreta(this.ManutenzioneAbilitaUtente);

		return (IManutenzioneAbilitaUtente) PortableRemoteObject.narrow(obj,
				ILogin.class);
	}

	@Override
	public IRegistrazione getGestoreRegistrazione() throws NamingException,
			ClassCastException {
		Object obj = this.cercaClasseConcreta(this.ManagerRegistrazione);

		return (IRegistrazione) PortableRemoteObject.narrow(obj, ILogin.class);
	}

	@Override
	public IRicercaUtenti getRicercaUtenti() throws NamingException,
			ClassCastException {
		Object obj = this.cercaClasseConcreta(this.ManagerRicerca);

		return (IRicercaUtenti) PortableRemoteObject.narrow(obj, ILogin.class);
	}

	@Override
	public IRichiestaAmicizia getRichiestaAmicizia() throws NamingException,
			ClassCastException {
		Object obj = this.cercaClasseConcreta(this.ManagerRichiestaAmicizia);

		return (IRichiestaAmicizia) PortableRemoteObject.narrow(obj,
				ILogin.class);
	}
}
