package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IManagerDomanda;
import it.swimv2.entities.remoteEntities.IDomanda;
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
 * Servlet implementation class ProprieDomandeServlet
 */
public class ProprieDomandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final IFactory factory = new SimpleFactory();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProprieDomandeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		proprieDomandeEsecuzione(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		proprieDomandeEsecuzione(request, response);
	}

	private void proprieDomandeEsecuzione(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// leggo il codice utente dalla session
		String userName;
		userName = (String) request.getSession().getAttribute("userName");

		if (userName == null || userName.isEmpty()) {
			// utente non loggato
			GestioneServlet
					.annullaSessione(request, response, "index.jsp",
							"Errore: Per visualizzare le proprie domande effettuare il login");
		} else {
			try {
				IManagerDomanda managerDomanda = factory.getManagerDomanda();

				IDomanda[] domande = managerDomanda.proprieDomande(userName);

				request.setAttribute("arrayProprieDomande", domande);

				GestioneServlet.showPage(request, response,
						"proprieDomande.jsp");
			} catch (ClassCastException | NamingException e) {
				e.printStackTrace();
				request.setAttribute("messaggio",
						"Errore: Impossibile visualizzare le proprie domande.");
				GestioneServlet.showPage(request, response, "index.jsp");
			}

		}

	}
}
