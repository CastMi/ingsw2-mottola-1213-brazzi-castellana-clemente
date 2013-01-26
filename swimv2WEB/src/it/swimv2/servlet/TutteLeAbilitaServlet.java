package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IManutenzioneAbilitaUtente;
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
 * Servlet implementation class TutteLeAbilita
 */
public class TutteLeAbilitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final IFactory factory = new SimpleFactory();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TutteLeAbilitaServlet() {
		super();
	}

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
		String username = (String) request.getSession().getAttribute(
				"nomeUtente");

		request.getSession().setAttribute("proprieAbilita",
				manager.getProprieAbilita(username));
		GestioneServlet.showPage(request, response, "abilita.jsp");
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
		String username = (String) request.getSession().getAttribute(
				"nomeUtente");

		request.getSession().setAttribute("proprieAbilita",
				manager.getProprieAbilita(username));
		GestioneServlet.showPage(request, response, "abilita.jsp");
	}

}
