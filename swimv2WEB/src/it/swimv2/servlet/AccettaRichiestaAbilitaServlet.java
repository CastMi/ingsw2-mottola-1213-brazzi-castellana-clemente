package it.swimv2.servlet;

import java.io.IOException;

import it.swimv2.controller.remoteController.IManutenzioneAbilitaAmministratore;
import it.swimv2.util.IFactory;
import it.swimv2.util.SimpleFactory;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccettaRichiestaAbilitaServlet extends HttpServlet {

	private static final long serialVersionUID = -2093601169195637516L;
	
	private final IFactory factory = new SimpleFactory();

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
		
		String nomeRichiestaAbilita = request.getParameter("nomeAbilita");
		String username = request.getParameter("username");

		switch (manager.accettareRichiestaAbilita(nomeRichiestaAbilita,
				username)) {
		case UTENTE_INESISTENTE:
			PaginaErrore(request, response, "Errore: utente inesistente.");
			break;
		case RICHIESTAABILITA_INESISTENTE:
			PaginaErrore(request, response,
					"Errore: non esiste alcuna richiesta di abilità corrispondente.");
			break;
		case ERRORE:
			PaginaErrore(request, response,
					"Errore: errore inaspettato, si prega di riprovare.");
			break;
		case OK:
			break;
		}
	}
	
	private void PaginaErrore(HttpServletRequest request,
			HttpServletResponse response, String messaggioDiErrore)
			throws ServletException, IOException {
		RequestDispatcher disp;
		request.setAttribute("messaggio", messaggioDiErrore);
		disp = request.getRequestDispatcher(response
				.encodeURL("AmministrazioneAbilita.jsp"));
		disp.forward(request, response);
	}
}
