package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Utente;

public class ChangePassword extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		String oldPwd = request.getParameter("password");

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/login/changePwd.jsp");
		if (utente.getPassword().equals(oldPwd)) {
			String newPwd = request.getParameter("newPassword");
			if (Utente.passwordCheck(newPwd)) {
				utente.setPassword(newPwd);
				request.setAttribute("success", true);
			} else {
				request.setAttribute("error",
						"La password non rispetta i controlli.<br>" + "Controlli: dimensione > 5 caratteri");
			}
		} else {
			request.setAttribute("error", "La old password non e` corretta");
		}
		rd.forward(request, response);
	}
}
