package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IRegistrazione;
import it.swimv2.util.ErroriRegistrazione;
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
 * Servlet implementation class RegistrazioneServlet
 */
public class RegistrazioneServlet extends HttpServlet {
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
				.encodeURL("WEB-INF/Registrazione.jsp"));
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp;
		ErroriRegistrazione risultatoRegistrazione;
		IRegistrazione iRegistrazione;
		try {
			iRegistrazione = factory.getGestoreRegistrazione();
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			return;
		}

		// ricezione dati provenienti dalla jsp
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String nomeUtente = request.getParameter("nomeUtente");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		risultatoRegistrazione = iRegistrazione.nuovaRegistrazione(nome,
				cognome, email, nomeUtente, password);
		if (risultatoRegistrazione.registrazioneValida()) {
			// visualizzazione registrazioneEffettuata
			disp = request.getRequestDispatcher(response
					.encodeURL("WEB-INF/RegistrazioneEffettuata.jsp"));
		} else {
			if (risultatoRegistrazione.ErroreCognome) {
				disp = request.getRequestDispatcher(response
						.encodeURL("WEB-INF/Registrazione.jsp"));
				request.setAttribute("messaggio", "Cognome non valido");
			} else {
				if (risultatoRegistrazione.ErroreNome) {
					disp = request.getRequestDispatcher(response
							.encodeURL("WEB-INF/Registrazione.jsp"));
					request.setAttribute("messaggio", "Nome non valido");
				} else if (risultatoRegistrazione.ErroreEmail) {
					disp = request.getRequestDispatcher(response
							.encodeURL("WEB-INF/Registrazione.jsp"));
					request.setAttribute("messaggio", "Email non valida");
				} else {
					if (risultatoRegistrazione.ErroreId) {
						disp = request.getRequestDispatcher(response
								.encodeURL("WEB-INF/Registrazione.jsp"));
						request.setAttribute("messaggio",
								"Nome utente non valido");
					} else {
						disp = request.getRequestDispatcher(response
								.encodeURL("WEB-INF/Registrazione.jsp"));
						request.setAttribute("messaggio", "Password non valida");
					}
				}
			}
		}
		disp.forward(request, response);

	}

}
