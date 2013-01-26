package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IManagerDomanda;
import it.swimv2.controller.remoteController.IManagerRisposta;
import it.swimv2.entities.remoteEntities.IDomanda;
import it.swimv2.entities.remoteEntities.IRisposta;
import it.swimv2.util.GestioneServlet;
import it.swimv2.util.IFactory;
import it.swimv2.util.SimpleFactory;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

/**
 * Servlet implementation class RicercaDomandeRisposteServlet
 */
public class RicercaDomandeRisposteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(RicercaDomandeRisposteServlet.class.getName());
	private final IFactory factory = new SimpleFactory();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaDomandeRisposteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ricercaDomandeRisposteEsecuzione(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String testo;
		testo = (String) request.getParameter("testo");
		log.error("RicercaDomandeRisposte 1: "+testo);
		ricercaDomandeRisposteEsecuzione(request, response);
	}

	private void ricercaDomandeRisposteEsecuzione(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String testo;
		testo = (String) request.getParameter("testo");

		try {
			IManagerRisposta managerRisposta = factory.getManagerRisposta();
			
			IManagerDomanda managerDomanda = factory.getManagerDomanda();
			log.error("RicercaDomandeRisposte 2: "+testo);
			
			IDomanda[] domande = managerDomanda.ricercaDomande(testo);
			
			IRisposta[] risposte = managerRisposta.ricercaRisposta(testo);
			
			request.setAttribute("arrayRisposte", risposte);
			
			request.setAttribute("arrayDomande", domande);

			GestioneServlet.showPage(request, response,
					"ricercaDomandeRisposte.jsp");
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			request.setAttribute("messaggio",
					"Errore: Impossibile visualizzare le risposte.");
			GestioneServlet.showPage(request, response, "index.jsp");
		}

	}
}
