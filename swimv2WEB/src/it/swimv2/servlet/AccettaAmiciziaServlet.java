package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IAmicizia;
import it.swimv2.util.ContextUtil;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AccettaAmiciziaServlet
 */
public class AccettaAmiciziaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccettaAmiciziaServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp;
		disp = request.getRequestDispatcher(response
				.encodeURL("WEB-INF/AmiciziaAccettata.jsp"));
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unused")
		RequestDispatcher disp;
		Object obj;
		// metodi per il naming
		try {
			obj = ContextUtil
					.getInitialContext()
					.lookup("/swimv2/ejbModule/it/swimv2/controller/remoteController/IAmicizia.java");
			@SuppressWarnings("unused")
			IAmicizia iAmicizia = (IAmicizia) PortableRemoteObject.narrow(obj,
					IAmicizia.class);
			// ricezione dati provenienti dalla jsp
			@SuppressWarnings("unused")
			String nome = request.getParameter("nome");

			// TODO capire la gestione dell'id della richiesta d'amicizia

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
