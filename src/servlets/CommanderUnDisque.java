package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metier.*;

public class CommanderUnDisque extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommanderUnDisque() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nomUser = null;
		int nbreProduit = 0;
		Cookie[] cookies = request.getCookies();
		nomUser = Identification.chercheNom(cookies);

		HttpSession httpSession = request.getSession();

		ArrayList<disque> ListeDisque = null;
		if (httpSession.getAttribute("ListeDisquePanier") != null)
			ListeDisque = (ArrayList<disque>) httpSession.getAttribute("ListeDisquePanier");
		else
			ListeDisque = new ArrayList<disque>();

		if (httpSession.getAttribute("nbreProduit") != null)
			nbreProduit = (Integer) httpSession.getAttribute("nbreProduit");

		String order = request.getParameter("order");
		if (order != null) {
			if (order.equals("ajouter")) {
				if (httpSession.getAttribute("nbreProduit") == null)
					nbreProduit++;
				String element = request.getParameter("element");
				String nomDisque = request.getParameter("nom");
				String prixDisque = request.getParameter("prix");
				ListeDisque.add(new disque(nomDisque, Double.parseDouble(prixDisque)));
				httpSession.setAttribute("ListeDisquePanier", ListeDisque);
				httpSession.setAttribute("nbreProduit", nbreProduit + 1);
			}
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<head>");
		out.println("<title> Commande de disques </title>");
		out.println("<style>" + "th {background-color: gray;color: white;}"
				+ " table, td, th {  border: 3px solid;text-align: center;}"
				+ "table { border-collapse: collapse; width: 50%} " + "th, td {padding: 10px;}" + "</style>");
		out.println("</head>");
		out.println("<body style=\"background-color:aqua;\">");
		out.println("<center>");
		out.println("<h3>" + "Bonjour " + nomUser + " dans votre store" + "</h3>");
		out.println("<h3>(Votre panier)</h3>");
		// affichage de tous les disques présents dans le panier (éléments de la
		// session)
		// si parametre ordre == ajouter affichage du disque à ajouter au panier
		// ajout du nouveau disque dans le panier
		out.println("<table>");
		out.println("<tr><th>Informations disque commandes</th></tr>");

		for (disque el : ListeDisque) {
			out.println("<tr><td>" + el.getNom() + " | " + el.getPrix() + " euros</td></tr>");
		}
		out.println("</table>");
		out.println("<p> Votre panier contient : " + nbreProduit + " disque(s)<p><br> ");
		out.println("<a href='achat'> Vous pouvez commandez un autre disque </a><br> ");
		out.println("<a href='enregistre'> Vous pouvez enregistrer votre commande </a><br> ");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
