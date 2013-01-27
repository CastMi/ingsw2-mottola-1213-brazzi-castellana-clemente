package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IManagerDomanda;
import it.swimv2.controller.remoteController.IManutenzioneAbilitaUtente;
import it.swimv2.entities.remoteEntities.IDomanda;
import it.swimv2.util.GestioneServlet;
import it.swimv2.util.IFactory;
import it.swimv2.util.SimpleFactory;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreaDomandaServlet
 */
public class CreaDomandaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private final IFactory factory = new SimpleFactory();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaDomandaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		creaDomandaEsecuzione(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		creaDomandaEsecuzione(request, response);		
	}
	
	private void creaDomandaEsecuzione(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//leggo il codice utente dalla session
		String userName;
		userName = (String) request.getSession().getAttribute("nomeUtente");
		
		if (userName == null || userName.isEmpty() ){
			// utente non loggato
			GestioneServlet
			.annullaSessione(request, response, "index.jsp",
					"Errore: Per creare una domanda effettuare il login");
		}else{
			try {
				IManagerDomanda managerDomanda = factory.getManagerDomanda();
				
				IManutenzioneAbilitaUtente iManutenzioneAbilitaUtente = factory.getManutentoreUtente();
				
				String titoloDomanda = (String) request.getParameter("titoloDomanda");
				
				String descrizioneDomanda = (String) request.getParameter("descrizioneDomanda");
				
				String nomeAbilita = (String) request.getParameter("nomeAbilita");
				
				Set<String> setAbilita = new HashSet<String>();
				
				setAbilita.add(nomeAbilita);
				//Collections.addAll(setAbilita, arrayAbilita);
								
				managerDomanda.creaDomanda(userName, titoloDomanda, descrizioneDomanda, setAbilita);
				
				IDomanda[] domande = managerDomanda.proprieDomande(userName);
				
				request.setAttribute("abilita", iManutenzioneAbilitaUtente.getTutteLeAbilita());
				
				request.setAttribute("arrayProprieDomande", domande);

				GestioneServlet.showPage(request, response,
						"proprieDomande.jsp");
			} catch (ClassCastException | NamingException e) {
				e.printStackTrace();
				request.setAttribute("messaggio","Errore: Impossibile creare una domanda.");
				GestioneServlet.showPage(request, response, "index.jsp");
			}
			
		}
		
	}
	
}
