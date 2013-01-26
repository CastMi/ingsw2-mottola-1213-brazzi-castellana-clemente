package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IManutenzioneAbilitaAmministratore;
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
 * Servlet implementation class VisualizzaRichiesteAbilitaServlet
 */
public class VisualizzaRichiesteAbilitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final IFactory factory = new SimpleFactory();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaRichiesteAbilitaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IManutenzioneAbilitaAmministratore manager;
		try {
			manager = factory.getManutentoreAmministratore();
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			return;
		}
		request.setAttribute("richiesteAbilita", manager.getTutteLeRichiesteDiAbilita());
		GestioneServlet.showPage(request, response, "richiesteAbilita.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
