package Servlet;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import Beans.LocalPaths;
import Beans.Organizzatore;

public class VerificaOrganizzatore extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Gson g;

	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		this.g = new Gson();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String user = request.getParameter("verifica");
		System.out.println(user);

		// Load from json file
		String pathJson = LocalPaths.pathVerificaOrganizzatore;//"D:/Gabri/b/web/verificaOrganizzatore.json";

		try (Reader reader = new FileReader(pathJson)) {
			Set<Organizzatore> org = new HashSet<Organizzatore>();
			Set<Organizzatore> newOrg = new HashSet<Organizzatore>();

			Type setType = new TypeToken<HashSet<Organizzatore>>() {
			}.getType();
			org = new Gson().fromJson(new JsonReader(reader), setType);

			for (Organizzatore i : org) {
				System.out.println(i.getUsername());
				if (!i.getUsername().equals(user)) {
					newOrg.add(i);
				}
			}

			// Write into json file
			try (FileWriter writer = new FileWriter(pathJson)) {
				new Gson().toJson(newOrg, writer);
			} catch (JsonIOException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/admin.jsp?verificato=true");
	}

}
