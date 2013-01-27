package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IManagerRichiestaAmicizia;
import it.swimv2.controller.remoteController.IManagerSuggerimentoAmicizia;
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
 * Servlet implementation class VisualizzaRichiesteAmiciziaServlet
 */
public class VisualizzaRichiesteAmiciziaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final IFactory factory = new SimpleFactory();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaRichiesteAmiciziaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		visualizzaSuggerimenti(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		visualizzaSuggerimenti(request, response);
	}
	private void visualizzaSuggerimenti(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IManagerRichiestaAmicizia iManagerRichiestaAmicizia;
		try {
			iManagerRichiestaAmicizia = factory.getRichiestaAmicizia();
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			return;
		}
		String nomeUtente = (String) request.getSession().getAttribute("nomeUtente");
		request.setAttribute("richiesteAmicizia", iManagerRichiestaAmicizia.getTutteRichiesteAmiciziaPerUtente(nomeUtente));
		GestioneServlet.showPage(request, response,
				"suggerimenti.jsp");
	}

}
