package it.swimv2.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class GestioneServlet {

	public static void annullaSessione(HttpServletRequest request,
			HttpServletResponse response, String page, String messaggio)
			throws ServletException, IOException {
		request.getSession().invalidate();
		request.setAttribute("messaggio", messaggio + ". Navigazione annullata");
		showPage(request, response, page);
	}

	public static void showPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		request.getRequestDispatcher(response.encodeURL(page)).forward(request,
				response);
	}
}
