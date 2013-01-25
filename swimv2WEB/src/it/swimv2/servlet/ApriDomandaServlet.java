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
 * Servlet implementation class ApriDomandaServlet
 */
public class ApriDomandaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final IFactory factory = new SimpleFactory();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApriDomandaServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		apriDomandaEsecuzione(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		apriDomandaEsecuzione(request, response);
	}

	private void apriDomandaEsecuzione(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			IManagerDomanda managerDomanda = factory.getManagerDomanda();

			int idDomanda = (int) request.getAttribute("idDomanda");

			IDomanda domanda = managerDomanda.apriDomanda(idDomanda);

			request.setAttribute("domanda", domanda);

			GestioneServlet.showPage(request, response, "showDomanda.jsp");
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			request.setAttribute("messaggio",
					"Errore: Impossibile creare una domanda.");
			GestioneServlet.showPage(request, response, "index.jsp");
		}

	}
}
