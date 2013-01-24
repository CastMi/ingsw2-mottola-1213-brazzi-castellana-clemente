package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IManagerRichiestaAmicizia;
import it.swimv2.util.IFactory;
import it.swimv2.util.SimpleFactory;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RichiediAmiciziaServlet
 */
public class RichiestaAmiciziaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final IFactory factory = new SimpleFactory();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp;
		disp = request.getRequestDispatcher(response
				.encodeURL("WEB-INF/RichiestaInviata.jsp"));
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unused")
		RequestDispatcher disp;
		IManagerRichiestaAmicizia iRichiestaAmicizia;
		try {
			iRichiestaAmicizia = factory.getRichiestaAmicizia();
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			return;
		}

		// ricezione dati provenienti dalla jsp
		int richiedente = Integer.parseInt(request.getParameter("richiedente"));
		int destinatario = Integer.parseInt(request
				.getParameter("destinatario"));
		String note = request.getParameter("note");
		iRichiestaAmicizia.creaNuovaRichiestaAmicizia(richiedente,
				destinatario, note);
		disp = request.getRequestDispatcher(response
				.encodeURL("WEB-INF/RichiestaAmiciziaEffettuata.jsp"));
	}

}
