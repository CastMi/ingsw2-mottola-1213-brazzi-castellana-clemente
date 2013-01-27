package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IManagerRichiestaAmicizia;
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
 * Servlet implementation class RifiutaRichiestaAmiciziaServlet
 */
public class RifiutaRichiestaAmiciziaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final IFactory factory = new SimpleFactory();
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RifiutaRichiestaAmiciziaServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IManagerRichiestaAmicizia iRichiestaAmicizia;
		try {
			iRichiestaAmicizia = factory.getRichiestaAmicizia();
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			return;
		}

		// ricezione dati provenienti dalla jsp
		int idRichiestaAmicizia = Integer.parseInt(request
				.getParameter("idRichiestaAmicizia"));
		iRichiestaAmicizia.rimuoviRichiestaAmicizia(idRichiestaAmicizia);
		GestioneServlet.showPage(request, response,
				"RichiestaAmiciziaRifiutata.jsp");
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IManagerRichiestaAmicizia iRichiestaAmicizia;
		try {
			iRichiestaAmicizia = factory.getRichiestaAmicizia();
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			return;
		}

		// ricezione dati provenienti dalla jsp
		int idRichiestaAmicizia = Integer.parseInt(request
				.getParameter("idRichiestaAmicizia"));
		iRichiestaAmicizia.rimuoviRichiestaAmicizia(idRichiestaAmicizia);
		GestioneServlet.showPage(request, response,
				"richiestaAmiciziaRifiutata.jsp");
	}
	
	
	

}
