package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IManutenzioneAbilitaUtente;
import it.swimv2.util.GestioneServlet;
import it.swimv2.util.IFactory;
import it.swimv2.util.InvioRichiestaAbilitaEnum;
import it.swimv2.util.SimpleFactory;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Michele
 * 
 */
public final class InviaRichiestaAbilitaServlet extends HttpServlet {

	private static final long serialVersionUID = -9116607973741379686L;

	private final IFactory factory = new SimpleFactory();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IManutenzioneAbilitaUtente manager;
		try {
			manager = factory.getManutentoreUtente();
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			return;
		}
		String nomeAbilita = request.getParameter("nomeAbilita");
		String descrizione = request.getParameter("descrizione");
		String username = (String) request.getSession().getAttribute(
				"nomeUtente");
		if (nomeAbilita.isEmpty()) {
			request.setAttribute("messaggio",
					"Errore: esiste già una richiesta per tale abilità.");
			return;
		}
		InvioRichiestaAbilitaEnum temp = manager.inviareRichiestaAbilita(
				nomeAbilita, descrizione, username);
		switch (temp) {
		case UTENTE_INESISTENTE:
			GestioneServlet.annullaSessione(request, response, "index.jsp",
					"Errore: utente inesistente.");
			break;

		case RICHIESTAABILITA_DUPLICATA:
			request.setAttribute("messaggio",
					"Errore: esiste già una richiesta per tale abilità.");
			GestioneServlet.showPage(request, response, "nuovaabilita.jsp");
			break;

		case ERRORE:
			GestioneServlet.annullaSessione(request, response, "index.jsp",
					"Errore: errore inaspettato.");
			break;

		case OK:
			request.setAttribute("messaggio",
					"Richiesta effettuata correttamente.");
			GestioneServlet.showPage(request, response, "nuovaabilita.jsp");
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IManutenzioneAbilitaUtente manager;
		try {
			manager = factory.getManutentoreUtente();
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			return;
		}
		String nomeAbilita = request.getParameter("nomeAbilita");
		String descrizione = request.getParameter("descrizione");
		String username = (String) request.getSession().getAttribute(
				"nomeUtente");

		InvioRichiestaAbilitaEnum temp = manager.inviareRichiestaAbilita(
				nomeAbilita, descrizione, username);
		switch (temp) {
		case UTENTE_INESISTENTE:
			GestioneServlet.annullaSessione(request, response, "index.jsp",
					"Errore: utente inesistente.");
			break;

		case RICHIESTAABILITA_DUPLICATA:
			request.setAttribute("messaggio",
					"Errore: esiste già una richiesta per tale abilità.");
			GestioneServlet.showPage(request, response, "nuovaabilita.jsp");
			break;

		case ERRORE:
			GestioneServlet.annullaSessione(request, response, "index.jsp",
					"Errore: errore inaspettato.");
			break;

		case OK:
			request.setAttribute("messaggio",
					"Richiesta effettuata correttamente.");
			GestioneServlet.showPage(request, response, "nuovaabilita.jsp");
			break;
		}
	}
}
