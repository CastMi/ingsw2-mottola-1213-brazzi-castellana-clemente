package it.swimv2.servlet;

import it.swimv2.controller.remoteController.IManutenzioneAbilitaUtente;
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
 * Servlet implementation class FormRegistrazioneServlet
 */
public class FormRegistrazioneServlet extends HttpServlet {
	
	private final IFactory factory = new SimpleFactory();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormRegistrazioneServlet() {
        super();
        
    }
    
  

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		formRegistrazioneEsegui(request, response);
	
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		formRegistrazioneEsegui(request, response);
	}



	private void formRegistrazioneEsegui(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IManutenzioneAbilitaUtente iManutenzioneAbilitaUtente;
		try {
			iManutenzioneAbilitaUtente = factory.getManutentoreUtente();
		} catch (ClassCastException | NamingException e) {
			e.printStackTrace();
			return;
		}
		request.setAttribute("abilita", iManutenzioneAbilitaUtente.getTutteLeAbilita());
		GestioneServlet.showPage(request, response,
				"registrazione.jsp");
	}

}
