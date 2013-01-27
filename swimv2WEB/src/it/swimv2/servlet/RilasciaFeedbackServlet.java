package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IManagerRisposta;
import it.swimv2.entities.remoteEntities.IDomanda;
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
 * Servlet implementation class RilasciaFeedbackServlet
 */
public class RilasciaFeedbackServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final IFactory factory = new SimpleFactory();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RilasciaFeedbackServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		rilasciaFeedbackEsecuzione(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		rilasciaFeedbackEsecuzione(request, response);
	}

	private void rilasciaFeedbackEsecuzione(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// leggo il codice utente dalla session
		String userName;
		userName = (String) request.getSession().getAttribute("nomeUtente");

		if (userName == null || userName.isEmpty()) {
			// utente non loggato
			GestioneServlet.annullaSessione(request, response, "index.jsp",
					"Errore: Per rilasciare un feedback effettuare il login");
		} else {
			try {
				IManagerRisposta managerRisposta = factory.getManagerRisposta();

				int idRisposta = Integer.parseInt(request
						.getParameter("idRisposta"));

				int voto = Integer.parseInt(request.getParameter("voto"));

				if (managerRisposta
						.rilasciaFeedback(idRisposta, userName, voto)) {
					request.setAttribute("risulato", "OK");
				} else {
					request.setAttribute("risulato", "KO");
				}

				IRisposta risposta = managerRisposta.apriRisposta(idRisposta);

				IDomanda domanda = risposta.getDomanda();

				request.setAttribute("domanda", domanda);

				IRisposta[] risposte = managerRisposta
						.getRisposteByDomanda(domanda.getId());

				request.setAttribute("arrayRisposte", risposte);

				GestioneServlet.showPage(request, response,
						"showConversazione.jsp");
			} catch (ClassCastException | NamingException e) {
				e.printStackTrace();
				request.setAttribute("messaggio",
						"Errore: Impossibile rilasciare un feedback.");
				GestioneServlet.showPage(request, response, "index.jsp");
			}

		}

	}

}
