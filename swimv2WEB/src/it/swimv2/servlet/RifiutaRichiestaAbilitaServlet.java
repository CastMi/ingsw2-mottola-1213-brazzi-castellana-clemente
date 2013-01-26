package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IManutenzioneAbilitaAmministratore;
import it.swimv2.util.IFactory;
import it.swimv2.util.SimpleFactory;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RifiutaRichiestaAbilita
 */
public class RifiutaRichiestaAbilitaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final IFactory factory = new SimpleFactory();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RifiutaRichiestaAbilitaServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IManutenzioneAbilitaAmministratore manager;
		try {
			manager = factory.getManutentoreAmministratore();
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			return;
		}
		String nomeRichiesta = request.getParameter("nomeRichiesta");
		String username = request.getParameter("username");
		switch (manager.rifiutareRichiestaAbilita(nomeRichiesta, username)) {
		case UTENTE_INESISTENTE:
			break;

		case RICHIESTAABILITA_INESISTENTE:
			break;

		case ERRORE:
			break;

		case OK:
			break;
		}
	}

}
