package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IManagerDomanda;
import it.swimv2.entities.remoteEntities.IDomanda;
import it.swimv2.util.GestioneServlet;
import it.swimv2.util.IFactory;
import it.swimv2.util.SimpleFactory;
import java.io.IOException;
import java.util.Collections;
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
		userName = (String) request.getSession().getAttribute("userName");
		
		if (userName == null || userName.isEmpty() ){
			// utente non loggato
			GestioneServlet
			.annullaSessione(request, response, "index.jsp",
					"Errore: Per creare una domanda effettuare il login");
		}else{
			try {
				IManagerDomanda managerDomanda = factory.getManagerDomanda();
				
				String titoloDomanda = (String) request.getAttribute("titoloDomanda");
				
				String descrizioneDomanda = (String) request.getAttribute("descrizioneDomanda");
				
				String[] arrayAbilita = (String[]) request.getAttribute("arrayAbilita");
				
				Set<String> setAbilita = new HashSet<String>();
				
				Collections.addAll(setAbilita, arrayAbilita);
								
				IDomanda domanda = managerDomanda.creaDomanda(userName, titoloDomanda, descrizioneDomanda, setAbilita);
				
				request.setAttribute("domanda", domanda);
				
				GestioneServlet.showPage(request, response, "showDomanda.jsp");
			} catch (ClassCastException | NamingException e) {
				e.printStackTrace();
				request.setAttribute("messaggio","Errore: Impossibile creare una domanda.");
				GestioneServlet.showPage(request, response, "index.jsp");
			}
			
		}
		
	}
	
}
