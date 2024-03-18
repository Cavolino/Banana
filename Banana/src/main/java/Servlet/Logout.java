package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.Utente;

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/ricerca.jsp");
		if (request.getSession().getAttribute("isLoggedIn") != null
				&& (boolean) request.getSession().getAttribute("isLoggedIn")) {
			request.getSession().setAttribute("isLoggedIn", false);
			request.getSession().setAttribute("utente", null);

			request.setAttribute("message", "Logout eseguito con successo");
		}
		rd.forward(request, response);
	}
	
	public static void logout(HttpSession session, Utente u) {
		session.setAttribute("isLoggedIn", false);
		session.setAttribute("utente", null);
		u.setSession(null);		
	}
}
