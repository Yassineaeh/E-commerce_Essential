package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

import DAO.ConnectionBda;
import metier.Client;

/**
 * Servlet implementation class identification
 */
public class identification extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ConnectionBda c;
	String u = "";

	public identification() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<head>");
		out.println("<title> identification d'un client  </title>");
		out.println("</head>");
		out.println("<body bgcolor='aqua' >");
		out.println(InscriptionClient.nomRecu + " | " + InscriptionClient.motPasseRecu + " | "
				+ InscriptionClient.nomCookie + " | " + InscriptionClient.motPasseCookie);
		out.println("<h3>" + "Bonjour,  pour s'identifier " + "</h3>");
		out.println("<h3>" + "entrez votre nom et mot de pass (y)" + "</h3>");

		out.print(" <form action='achat' method='GET' > ");
		out.println("nom");
		out.println("<input type='text' size='20' name='nom'  >");
		out.println("<br>");
		out.println("mot de passe");
		out.println("<input type='password' size='20' name='motdepasse'> <br>");
		out.println("<input type='submit' value='identifier'>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Client Client = null;
		try {
			Client = Client.getClient(c.getConnection(), request.getParameter("nom"), request.getParameter("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (Client == null) {
			u = "nom ou mot de passe incorrect !!";
			doGet(request, response);
		} else {
			// ArrayList<Product> l=new ArrayList<Product>();
			HttpSession se = request.getSession(true);
			String nomClient = request.getParameter("nom");
			String passwd = request.getParameter("motdepasse");
			se.setAttribute("nom", nomClient);
			se.setAttribute("password", passwd);
			request.getRequestDispatcher("/achat").forward(request, response);

		}

	}
}