package it.swimv2.servlet;

import it.swimv2.controller.remoteController.ILogin;
import it.swimv2.util.GestioneServlet;
import it.swimv2.util.IFactory;
import it.swimv2.util.SimpleFactory;
import it.swimv2.util.UtenteEnum;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final IFactory factory = new SimpleFactory();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ILogin iLogin;
		try {
			iLogin = factory.getManagerLogin();
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			return;
		}
		String nomeUtente = request.getParameter("userName");
		String password = request.getParameter("password");
		UtenteEnum uEnum = iLogin.verificaLogin(nomeUtente, password);
		if (uEnum == UtenteEnum.AMMINISTRATORE) {
			request.getSession().setAttribute("nomeUtente", nomeUtente);
			request.setAttribute("amministratore",
					iLogin.getAmministratore(nomeUtente));

			GestioneServlet.showPage(request, response,
					"homeAmministratore.jsp");
		} else if (uEnum == UtenteEnum.UTENTE) {
			request.getSession().setAttribute("nomeUtente", nomeUtente);
			request.getSession().setAttribute("utente", iLogin.getUtente(nomeUtente));
			GestioneServlet.showPage(request, response, "homeUtente.jsp");
		} else {
			request.setAttribute("messaggioLogin",
					"Errore: nome utente o password errati!");
			if (request.getSession().getAttribute("nomeUtente") != null) {
				request.getSession().removeAttribute("nomeUtente");
			}
			GestioneServlet.showPage(request, response, "index.jsp");
		}
	}
}
