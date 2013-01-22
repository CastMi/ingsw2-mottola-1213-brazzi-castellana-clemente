package it.swimv2.servlet;

import it.swimv2.controller.remoteController.ILogin;
import it.swimv2.util.ContextUtil;
import it.swimv2.util.UtenteEnum;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UtenteEnum risultatoLogin;
		Object obj;
		try {
			obj = ContextUtil
					.getInitialContext()
					.lookup("/swimv2/ejbModule/it/swimv2/controller/remoteController/ILogin.java");

			ILogin iLogin = (ILogin) PortableRemoteObject.narrow(obj,
					ILogin.class);
			String nomeUtente = request.getParameter("nomeUtente");
			String password = request.getParameter("password");
			risultatoLogin = iLogin.verificaLogin(nomeUtente, password);
			if (risultatoLogin.isAmministratore()) {
				request.setAttribute("nomeUtente", nomeUtente);
				request.setAttribute("password", password);
				caricamentoHomeAmministratore(request, response);
			} else if (risultatoLogin.isUtente()) {
				request.setAttribute("messaggio",
						"Errore: codice utente o password errati.");
				caricamentoHomeUtente(nomeUtente, request, response);
			} else {
				request.setAttribute("messaggio",
						"Errore: codice utente o password errati.");
				showPaginaLogin(request, response);
			}
		} catch (NamingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
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
		disp = request.getRequestDispatcher(response.encodeURL("Home.jsp"));
		disp.forward(request, response);
	}

}
