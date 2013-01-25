package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IManagerRisposta;
import it.swimv2.entities.remoteEntities.IRisposta;
import it.swimv2.util.GestioneServlet;
import it.swimv2.util.IFactory;
import it.swimv2.util.SimpleFactory;

import java.io.IOException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RispondiADomandaServlet
 */
public class RispondiADomandaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final IFactory factory = new SimpleFactory();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RispondiADomandaServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		rispondiADomandaEsecuzione(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		rispondiADomandaEsecuzione(request, response);
	}

	private void rispondiADomandaEsecuzione(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// leggo il codice utente dalla session
		String userName;
		userName = (String) request.getSession().getAttribute("userName");

		if (userName == null || userName.isEmpty()) {
			// utente non loggato
			GestioneServlet
					.annullaSessione(request, response, "index.jsp",
							"Errore: Per rispondere ad una domanda effettuare il login");
		} else {
			try {
				IManagerRisposta managerRisposta = factory.getManagerRisposta();

				int idDomanda = (int) request.getAttribute("idDomanda");

				String descrizioneRisposta = (String) request
						.getAttribute("descrizioneRisposta");

				IRisposta risposta = managerRisposta.aggiungiRispsota(
						idDomanda, userName, descrizioneRisposta);

				request.setAttribute("risposta", risposta);

				GestioneServlet.showPage(request, response, "showRisposta.jsp");
			} catch (ClassCastException | NamingException e) {
				e.printStackTrace();
				request.setAttribute("messaggio",
						"Errore: Impossibile rispondere ad una domanda.");
				GestioneServlet.showPage(request, response, "index.jsp");
			}

		}

	}

}
