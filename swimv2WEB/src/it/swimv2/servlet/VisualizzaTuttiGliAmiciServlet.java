package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IManagerAmicizia;
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
 * Servlet implementation class VisualizzaTuttiGliAmiciServlet
 */
public class VisualizzaTuttiGliAmiciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final IFactory factory = new SimpleFactory();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VisualizzaTuttiGliAmiciServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IManagerAmicizia manager;
		try {
			manager = factory.getManagerAmicizia();
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			return;
		}
		String username = (String) request.getSession().getAttribute(
				"nomeUtente");
		request.setAttribute("propriAmici", manager.tuttiGliAmici(username));
		GestioneServlet.showPage(request, response, "listaAmici.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
