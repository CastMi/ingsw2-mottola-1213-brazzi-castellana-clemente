package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IRichiestaAmicizia;
import it.swimv2.util.ContextUtil;
import java.io.IOException;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RichiestaAmiciziaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher disp;
			disp = request.getRequestDispatcher(response.encodeURL("WEB-INF/RichiestaInviata.jsp"));
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
		Object obj;
		// metodi per il naming
		try {
			obj = ContextUtil
					.getInitialContext()
					.lookup("/swimv2/ejbModule/it/swimv2/controller/remoteController/IRichiestaAmicizia.java");
		
		IRichiestaAmicizia iRichiestaAmicizia = (IRichiestaAmicizia) PortableRemoteObject
				.narrow(obj, IRichiestaAmicizia.class);
		// ricezione dati provenienti dalla jsp
		int richiedente = Integer.parseInt(request.getParameter("richiedente"));
		int destinatario = Integer.parseInt(request.getParameter("destinatario"));
		String note = request.getParameter("note");
		iRichiestaAmicizia.creaNuovaRichiestaAmicizia(richiedente, destinatario, note);
		disp = request.getRequestDispatcher(response
				.encodeURL("WEB-INF/RichiestaAmiciziaEffettuata.jsp"));
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
