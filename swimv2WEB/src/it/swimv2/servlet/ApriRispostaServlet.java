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
 * Servlet implementation class ApriRispostaServlet
 */
public class ApriRispostaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final IFactory factory = new SimpleFactory();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApriRispostaServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		apriRispostaEsecuzione(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		apriRispostaEsecuzione(request, response);
	}

	private void apriRispostaEsecuzione(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			IManagerRisposta managerRisposta = factory.getManagerRisposta();

			int idRisposta = (int) request.getAttribute("idRisposta");

			IRisposta risposta = managerRisposta.apriRisposta(idRisposta);

			request.setAttribute("risposta", risposta);

			GestioneServlet.showPage(request, response, "showRisposta.jsp");
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			request.setAttribute("messaggio",
					"Errore: Impossibile aprire una risposta.");
			GestioneServlet.showPage(request, response, "index.jsp");
		}

	}

}
