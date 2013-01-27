package it.swimv2.servlet;

import java.io.IOException;

import it.swimv2.controller.remoteController.IManutenzioneAbilitaAmministratore;
import it.swimv2.util.GestioneServlet;
import it.swimv2.util.IFactory;
import it.swimv2.util.SimpleFactory;

import javax.naming.NamingException;
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

		String nomeRichiestaAbilita = request.getParameter("nomeRichiesta");
		String username = request.getParameter("username");

		switch (manager.accettareRichiestaAbilita(nomeRichiestaAbilita,
				username)) {
		case UTENTE_INESISTENTE:
			GestioneServlet.annullaSessione(request, response, "index.jsp",
					"Errore: utente inesistente.");
			break;
		case RICHIESTAABILITA_INESISTENTE:
			request.setAttribute("messaggio",
					"Errore: non esiste alcuna richiesta di abilità corrispondente.");
			request.setAttribute("richiesteAbilita", manager.getTutteLeRichiesteDiAbilita());
			GestioneServlet.showPage(request, response, "richiesteAbilita.jsp");
			break;
		case ERRORE:
			GestioneServlet.annullaSessione(request, response, "index.jsp",
					"Errore: errore inaspettato, si prega di riprovare.");
			break;
		case OK:
			request.setAttribute("messaggio",
					"Richiesta accettata correttamente.");
			request.setAttribute("richiesteAbilita", manager.getTutteLeRichiesteDiAbilita());
			GestioneServlet.showPage(request, response, "richiesteAbilita.jsp");
			break;
		}
	}
}
