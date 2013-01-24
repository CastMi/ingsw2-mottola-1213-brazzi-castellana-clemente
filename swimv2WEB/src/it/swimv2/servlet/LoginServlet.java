package it.swimv2.servlet;

import it.swimv2.controller.remoteController.ILogin;
import it.swimv2.util.IFactory;
import it.swimv2.util.SimpleFactory;
import it.swimv2.util.UtenteEnum;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

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
		UtenteEnum risultatoLogin;
		try {
			iLogin = factory.getManagerLogin();
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			return;
		}

		String nomeUtente = request.getParameter("userName");
		String password = request.getParameter("password");
		risultatoLogin = iLogin.verificaLogin(nomeUtente, password);
		if (risultatoLogin.isAmministratore()) {
			request.getSession().setAttribute("nomeUtente", nomeUtente);
			caricamentoHomeAmministratore(request, response);
		} else if (risultatoLogin.isUtente()) {
			request.getSession().setAttribute("nomeUtente", nomeUtente);
			caricamentoHomeUtente(nomeUtente, request, response);
		} else {
			request.setAttribute("messaggio",
					"Errore: nome utente o password errati.");
			showPaginaLogin(request, response);
		}
	}

	private void caricamentoHomeUtente(String nomeUtente,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher disp;
		disp = request.getRequestDispatcher(response
				.encodeURL("HomeAmministratore.jsp"));
		disp.forward(request, response);

	}

	private void caricamentoHomeAmministratore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp;
		disp = request.getRequestDispatcher(response
				.encodeURL("HomeUtente.jsp"));
		disp.forward(request, response);

	}

	private void showPaginaLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp;
		disp = request.getRequestDispatcher(response.encodeURL("index.jsp"));
		disp.forward(request, response);
	}

}
