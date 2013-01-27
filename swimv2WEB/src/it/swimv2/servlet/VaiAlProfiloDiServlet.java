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
 * Servlet implementation class VaiAlProfiloDiServlet
 */
public class VaiAlProfiloDiServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private final IFactory factory = new SimpleFactory();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VaiAlProfiloDiServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
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
		String io = (String) request.getSession().getAttribute(
				"nomeUtente");
		if (io == null || io.isEmpty()) {
			request.setAttribute("messaggio",
					"Errore: non ti è permesso vedere il profilo dell'utente.");
			GestioneServlet.showPage(request, response, "nuovaabilita.jsp");
			return;
		}
		String username = request.getParameter("username");
		request.setAttribute("altroUtente",
				manager.getUtente(username));
		GestioneServlet.showPage(request, response, "paginaAltroUtente.jsp");		
	}

}
