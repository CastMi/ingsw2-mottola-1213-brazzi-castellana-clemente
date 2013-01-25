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
 * Servlet implementation class VisualizzaRispostaServlet
 */
public class VisualizzaRisposteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final IFactory factory = new SimpleFactory();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VisualizzaRisposteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		visualizzaRisposteEsecuzione(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		visualizzaRisposteEsecuzione(request, response);
	}

	private void visualizzaRisposteEsecuzione(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int idDomanda;
		idDomanda = (int) request.getAttribute("idDomanda");

		try {
			IManagerRisposta managerRisposta = factory.getManagerRisposta();

			IRisposta[] risposte = managerRisposta
					.getRisposteByDomanda(idDomanda);

			request.setAttribute("arrayRisposte", risposte);

			GestioneServlet.showPage(request, response,
					"visualizzaRisposte.jsp");
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			request.setAttribute("messaggio",
					"Errore: Impossibile visualizzare le risposte.");
			GestioneServlet.showPage(request, response, "index.jsp");
		}

	}

}
