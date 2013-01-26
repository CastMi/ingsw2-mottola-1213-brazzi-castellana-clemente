package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IRicercaUtenti;
import it.swimv2.util.IFactory;
import it.swimv2.util.SimpleFactory;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RicercaUtenteServlet extends HttpServlet {

	private static final long serialVersionUID = -2608491352709946175L;
	
	private final IFactory factory = new SimpleFactory();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IRicercaUtenti manager;
		try {
			manager = factory.getRicercaUtenti();
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			return;
		}
		String testo = request.getParameter("testoRicerca");
		request.setAttribute("risultatoRicerca", manager.ricercaUtentiPerUsername(testo));
		RequestDispatcher disp;
		disp = request.getRequestDispatcher(response
				.encodeURL("ricerca.jsp"));
		disp.forward(request, response);
	}
}
