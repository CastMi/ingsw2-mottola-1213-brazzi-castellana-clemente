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
import javax.servlet.http.HttpSession;

public class CancellaAbilitaServlet extends HttpServlet {

	private static final long serialVersionUID = 3156495080573772166L;

	private final IFactory factory = new SimpleFactory();

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 * 
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IManutenzioneAbilitaUtente manager;
		HttpSession session;
		try {
			manager = factory.getManutentoreUtente();
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			return;
		}
		session = request.getSession();
		String nomeAbilita = request.getParameter("nomeAbilita");
		String username = (String) session.getAttribute("nomeUtente");
		if (username == null) {
			session.setAttribute("LoginDaRifare", "Si");
			response.sendRedirect("index.jsp");
			return;
		}
		
		switch (manager.rimuoverePropriaAbilita(nomeAbilita, username)) {
		case UTENTE_INESISTENTE:
			GestioneServlet.annullaSessione(request, response, "index.jsp",
					"Errore: utente inesistente.");
			break;

		case ABILITA_INESISTENTE:
			request.setAttribute("messaggio",
					"Errore: l'utente non possiede l'abilità che desidera cancellare.");
			GestioneServlet.showPage(request, response, "abilita.jsp");
			break;

		case ERRORE:
			GestioneServlet.annullaSessione(request, response, "index.jsp",
					"Errore: errore inaspettato.");
			break;

		case OK:
			request.getSession().setAttribute("proprieAbilita",
					manager.getTutteLeAbilita());
			GestioneServlet.showPage(request, response, "abilita.jsp");
			break;
		}
	}
}
