package Servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.Utente;

public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		
		new Gson();
		
		Init init = (Init)this.getServletContext().getAttribute("init");
		if(init == null) {
			init = new Init();
			init.init(this.getServletConfig());			
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("username");
		Map<String, Utente> utentiRegistrati = (Map<String, Utente>) this.getServletContext().getAttribute("utentiRegistrati");
		Utente utente = utentiRegistrati.get(name);
		if (utente == null) {
			// utente non registrato
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login/login.jsp");
			request.setAttribute("error", "Utente non registrato!");
			rd.forward(request, response);
			return;
		}
		if (utente.getPassword().compareTo(request.getParameter("password")) != 0) {
			// pwd errata
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login/login.jsp");
			request.setAttribute("error", "Password errata!");
			rd.forward(request, response);
			return;
		} // altrimenti tutto OK, si procede

		HttpSession session = request.getSession();
		utente.setSession(session);
	
		session.setAttribute("utente", utente);
		session.setAttribute("isLoggedIn", true);
		
		response.sendRedirect("ricerca");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public static boolean isLoggedIn(HttpServletRequest request) {
		return (request.isRequestedSessionIdValid() 
				&& request.getSession().getAttribute("isLoggedIn") != null
				&& (boolean) request.getSession().getAttribute("isLoggedIn"));
	}
	
	

}
