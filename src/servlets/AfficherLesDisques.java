package servlets;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.Identification;
import metier.Stock;

public class AfficherLesDisques extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AfficherLesDisques() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Stock uneVente = new Stock();
		String nom = null;
		Cookie[] cookies = request.getCookies();
		nom = Identification.chercheNom(cookies);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<head>");
		out.println("<title> Commande de disques </title>");
		out.println("<style>"
					+"th {background-color: gray;color: RED;}"
					+ " table, td, th {  border: 3x solid;text-align: center;}"
					+ "table { border-collapse: collapse; width: 50%} "
					+ "th, td {padding: 10px;}"
					+ "</style>");
		out.println("</head>");
		out.println("<body style=\"background-color:aqua;\">");
		out.println("<center>");
		out.println("<h3>" + "Bienvenu " + nom + " dans votre store" + "</h3>");
		out.println("<h3>La liste des produits de notre stock </h3>");
		uneVente.vente(out);
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
