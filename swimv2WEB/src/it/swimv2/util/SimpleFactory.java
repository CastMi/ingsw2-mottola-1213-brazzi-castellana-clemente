package it.swimv2.util;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import it.swimv2.controller.remoteController.IAmicizia;
import it.swimv2.controller.remoteController.ILogin;
import it.swimv2.controller.remoteController.IManagerDomanda;
import it.swimv2.controller.remoteController.IManagerRisposta;
import it.swimv2.controller.remoteController.IManutenzioneAbilitaAmministratore;
import it.swimv2.controller.remoteController.IManutenzioneAbilitaUtente;
import it.swimv2.controller.remoteController.IRegistrazione;
import it.swimv2.controller.remoteController.IRicercaUtenti;
import it.swimv2.controller.remoteController.IRichiestaAmicizia;
import it.swimv2.util.ContextUtil;

public class SimpleFactory implements IFactory {

	private final String managerAmicizia = "ManagerAmicizia";
	private final String managerLogin = "ManagerLogin";
	private final String manutenzioneAbilitaAmministratore = "ManagerManutenzioneAbilitaAmministratore";
	private final String manutenzioneAbilitaUtente = "ManagerManutenzioneAbilitaUtente";
	private final String managerRegistrazione = "ManagerRegistrazione";
	private final String managerRicerca = "ManagerRicercaUtenti";
	private final String managerRichiestaAmicizia = "ManagerRichiestaAmicizia";
	private final String managerDomanda = "ManagerDomanda";
	private final String managerRisposta = "ManagerRisposta";

	private Object cercaClasseConcreta(String nomeClasseConcreta)
			throws NamingException {
		return ContextUtil.getInitialContext().lookup(
				"swimv2EAR/" + nomeClasseConcreta + "/remote");
	}

	@Override
	public IAmicizia getManagerAmicizia() throws NamingException,
			ClassCastException {
		Object obj = this.cercaClasseConcreta(this.managerAmicizia);

		return (IAmicizia) PortableRemoteObject.narrow(obj, IAmicizia.class);
	}

	@Override
	public ILogin getManagerLogin() throws NamingException, ClassCastException {
		Object obj = this.cercaClasseConcreta(this.managerLogin);

		return (ILogin) PortableRemoteObject.narrow(obj, ILogin.class);
	}

	@Override
	public IManutenzioneAbilitaAmministratore getManutentoreAmministratore()
			throws NamingException, ClassCastException {
		Object obj = this
				.cercaClasseConcreta(this.manutenzioneAbilitaAmministratore);

		return (IManutenzioneAbilitaAmministratore) PortableRemoteObject
				.narrow(obj, IManutenzioneAbilitaAmministratore.class);
	}

	@Override
	public IManutenzioneAbilitaUtente getManutentoreUtente()
			throws NamingException, ClassCastException {
		Object obj = this.cercaClasseConcreta(this.manutenzioneAbilitaUtente);

		return (IManutenzioneAbilitaUtente) PortableRemoteObject.narrow(obj,
				IManutenzioneAbilitaUtente.class);
	}

	@Override
	public IRegistrazione getGestoreRegistrazione() throws NamingException,
			ClassCastException {
		Object obj = this.cercaClasseConcreta(this.managerRegistrazione);

		return (IRegistrazione) PortableRemoteObject.narrow(obj,
				IRegistrazione.class);
	}

	@Override
	public IRicercaUtenti getRicercaUtenti() throws NamingException,
			ClassCastException {
		Object obj = this.cercaClasseConcreta(this.managerRicerca);

		return (IRicercaUtenti) PortableRemoteObject.narrow(obj,
				IRicercaUtenti.class);
	}

	@Override
	public IRichiestaAmicizia getRichiestaAmicizia() throws NamingException,
			ClassCastException {
		Object obj = this.cercaClasseConcreta(this.managerRichiestaAmicizia);

		return (IRichiestaAmicizia) PortableRemoteObject.narrow(obj,
				IRichiestaAmicizia.class);
	}

	@Override
	public IManagerDomanda getManagerDomanda() throws NamingException,
			ClassCastException {
		Object obj = this.cercaClasseConcreta(this.managerDomanda);

		return (IManagerDomanda) PortableRemoteObject.narrow(obj,
				IManagerDomanda.class);
	}

	@Override
	public IManagerRisposta getManagerRisposta() throws NamingException,
			ClassCastException {
		Object obj = this.cercaClasseConcreta(this.managerRisposta);

		return (IManagerRisposta) PortableRemoteObject.narrow(obj,
				IManagerRisposta.class);
	}
}
