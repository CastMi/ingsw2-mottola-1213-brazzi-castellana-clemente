package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IRegistrazione;
import it.swimv2.util.ContextUtil;
import it.swimv2.util.ErroriRegistrazione;
import java.io.IOException;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrazioneServlet() {
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
		Object obj;
		// metodi per il naming
		try {
			obj = ContextUtil
					.getInitialContext()
					.lookup("/swimv2/ejbModule/it/swimv2/controller/remoteController/IRegistrazione.java");
		
		IRegistrazione iRegistrazione = (IRegistrazione) PortableRemoteObject
				.narrow(obj, IRegistrazione.class);
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
			} else{ 
				if (risultatoRegistrazione.ErroreNome) {
				disp = request.getRequestDispatcher(response
						.encodeURL("WEB-INF/Registrazione.jsp"));
				request.setAttribute("messaggio", "Nome non valido");
			}else 
				if (risultatoRegistrazione.ErroreEmail){
					disp = request.getRequestDispatcher(response
							.encodeURL("WEB-INF/Registrazione.jsp"));
					request.setAttribute("messaggio", "Email non valida");
				}else{
					if (risultatoRegistrazione.ErroreId){
						disp = request.getRequestDispatcher(response
								.encodeURL("WEB-INF/Registrazione.jsp"));
						request.setAttribute("messaggio", "Nome utente non valido");
					}else{
						disp = request.getRequestDispatcher(response
								.encodeURL("WEB-INF/Registrazione.jsp"));
						request.setAttribute("messaggio", "Password non valida");
					}
				}
			}
		}
		disp.forward(request, response);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
		
}
