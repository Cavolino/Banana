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

import Beans.Utente;
import Beans.LocalPaths;
import Beans.Membro;

public class MostraInteresse extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("utente");
		String user = utente.getUsername();

		if (user == null)
			return;

		String idEvento = request.getParameter("IdEvento");

		// Load from json file
		String pathJson = LocalPaths.pathEventiInteresse;// "D:/Gabri/b/web/eventiInteresse.json";

		try (Reader reader = new FileReader(pathJson)) {
			Set<Membro> membri = new HashSet<Membro>();

			Type setType = new TypeToken<HashSet<Membro>>() {
			}.getType();
			membri = new Gson().fromJson(new JsonReader(reader), setType);

			String[] eventiUtente;
			int f = 0;
			for (Membro i : membri) {
				System.out.println(i.getUsername());
				if (i.getUsername().equals(user)) {
					f = 1;
					if (i.getEventi().length > 0) {
						System.out.println(i.getEventi().length);
						eventiUtente = i.getEventi();
						eventiUtente[eventiUtente.length] = idEvento;
						i.setEventi(eventiUtente);
					} else {
						eventiUtente = new String[] {};
						eventiUtente[0] = idEvento;
						i.setEventi(eventiUtente);
					}

				}
			}
			
			if(f==0) {
				String[] a = new String[1];
				a[0] = idEvento;
				membri.add(new Membro("", user, a));
			}
			
			

			/*
			 * String[] eventiUtente = new String[15]; for (Membro i : membri) {
			 * eventiUtente = i.getEventi(); for (int x = 0; x < eventiUtente.length; x++) {
			 * String s = eventiUtente[x]; System.out.println(s); if (s == null ||
			 * s.isEmpty() || s.isBlank()) { eventiUtente[x] = idEvento; break; } }
			 * 
			 * i.setEventi(eventiUtente); }
			 */

			// Write into json file
			try (FileWriter writer = new FileWriter(pathJson)) {
				new Gson().toJson(membri, writer);
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
	}

}