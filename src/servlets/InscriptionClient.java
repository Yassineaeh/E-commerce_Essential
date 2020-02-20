package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import DAO.ConnectionBda;
import metier.Client;

/**
 * Servlet implementation class InscriptionClient
 */
public class InscriptionClient extends HttpServlet {

	String u = "";
	java.sql.Connection c = ConnectionBda.getConnection();

	static String nomRecu = null, motPasseRecu = null;
	static String nomCookie = null, motPasseCookie = null;

	public InscriptionClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		// initialisation cookies et paramètres

		Cookie cookies[] = request.getCookies();

		if (cookies != null) {
			for (Cookie k : cookies) {
				if (k.getName().equals("nomCookie")) {
					nomCookie = k.getValue();
					k.setMaxAge(0);
					response.addCookie(k);
				}
				if (k.getName().equals("motPasseCookie")) {
					motPasseCookie = k.getValue();
					k.setMaxAge(0);
					response.addCookie(k);
				}

			}

		}
		nomRecu = request.getParameter("nom");
		motPasseRecu = request.getParameter("motdepasse");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if (nomCookie == null && nomRecu == null) {
			// Cas 1 : cas où il n'y a ni de cookies ni de parametres
			out.println("<html>");
			out.println("<body>");
			out.println("<head>");
			out.println("<title>   inscription d'un client  </title>");
			out.println("</head>");
			out.println("<body bgcolor='white' >");
			out.println(nomRecu + " | " + motPasseRecu + " | " + nomCookie + " | " + motPasseCookie);
			out.println("<h3>" + "Bonjour,  vous devez vous inscrire " + "</h3>");
			out.println("<h3>" + "Attention mettre  nom et le mot de passe avec plus de 3 caracteres" + "</h3>");
			out.print("<div>");
			out.print(" <form action='sinscrire' method='GET' > ");

			out.println("nom");
			out.println("<input type='text' size='20' name='nom'  >>");
			out.println("<br>");
			out.println("mot de passe");
			out.println("<input type='password' size='20' name='motdepasse'> </td><br>");
			out.println("<input type='submit' value='inscription'>");
			out.println("</form>");
			out.print("</div>");
			out.println("</body>");
			out.println("</html>");
		} else if (nomCookie == null && nomRecu != null) {
			// Cas 2 : cas où il n'y a pas de cookies mais les paramètres nom et mot de
			// passes sont présents :
			Cookie nom = new Cookie("nomCookie", nomRecu);
			Cookie pass = new Cookie("motPasseCookie", motPasseRecu);
			response.addCookie(nom);
			response.addCookie(pass);
			response.sendRedirect("/E-comerce/sinscrire");

		} else if (identique(nomRecu, nomCookie) && identique(motPasseRecu, motPasseCookie)) {
			// Cas 4 : cas où le nom et le mot passe sont correctes, appel à la servlet
			// achat
			response.sendRedirect("/E-comerce/achat");
		} else {
			// les cookies sont présents demande de s'identifier
			Client cn = new Client(cookies[0].getValue(), cookies[1].getValue());
			try {
				cn.addClient(c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			u = "You have a new account now, Welcome";

			response.sendRedirect("/E-comerce/identification");

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		doGet(request, response);
	}

	boolean identique(String recu, String cookie) {
		return ((recu != null) && (recu.length() > 3) && (cookie != null) && (recu.equals(cookie)));
	}
}
