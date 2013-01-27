package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IManagerAmicizia;
import it.swimv2.util.GestioneServlet;
import it.swimv2.util.IFactory;
import it.swimv2.util.SimpleFactory;

import javax.naming.NamingException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AccettaAmiciziaServlet
 */
public class AccettaAmiciziaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final IFactory factory = new SimpleFactory();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		IManagerAmicizia iAmicizia;

		try {
			iAmicizia = factory.getManagerAmicizia();
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			return;
		}

		// ricezione dati provenienti dalla jsp
		int idRichiestaAmicizia = Integer.parseInt(request
				.getParameter("idRichiestaAmicizia"));

		iAmicizia.creaAmicizia(idRichiestaAmicizia);
		GestioneServlet.showPage(request, response,
				"richiestaAmiciziaAccettata.jsp");
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 * 
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		IManagerAmicizia iAmicizia;

		try {
			iAmicizia = factory.getManagerAmicizia();
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			return;
		}

		// ricezione dati provenienti dalla jsp
		int idRichiestaAmicizia = Integer.parseInt(request
				.getParameter("idRichiestaAmicizia"));
		iAmicizia.creaAmicizia(idRichiestaAmicizia);
		GestioneServlet.showPage(request, response,
				"richiestaAmiciziaAccettata.jsp");
	}

}
