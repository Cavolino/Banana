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


import Beans.Utente;

public class Registrazione extends HttpServlet {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		
		Init init = (Init)this.getServletContext().getAttribute("init");
		if(init == null) {
			init = new Init();
			init.init(this.getServletConfig());	
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
		String pwd1 = request.getParameter("password");
		String pwd2 = request.getParameter("ripetiPassword");
		String gruppo = request.getParameter("gruppo");
		
		Map<String, Utente> utentiRegistrati = (Map<String, Utente>)this.getServletContext().getAttribute("utentiRegistrati");
		
		if(utentiRegistrati.containsKey(username)){
			request.setAttribute("error", "Errore nella registrazione, username gia` esistente!");
			rd = getServletContext().getRequestDispatcher("/login/registrazione.jsp");
		}else if(!pwd1.equals(pwd2)) {
			request.setAttribute("error", "Errore nella registrazione, le password non corrispondono!");
			rd = getServletContext().getRequestDispatcher("/login/registrazione.jsp");
		}else {
			utentiRegistrati.put(username, new Utente(username, pwd1));
			this.getServletContext().setAttribute("utentiRegistrati", utentiRegistrati);
			rd = getServletContext().getRequestDispatcher("/login/login.jsp");
		}
		
		
		
		rd.forward(request, response);
	}

}
