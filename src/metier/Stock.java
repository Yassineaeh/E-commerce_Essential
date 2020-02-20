package metier;

import java.io.PrintWriter;

public class Stock {
	static String[][] leStock = { { "Disque CD - AMOR TICINES", "15", "disque897TR566" },
			{ "Disque CD - Los Mayas", "19", "disque78UUNYT67" }, { "Disque CD - Dick Anglas", "25", "disque87YHG564" },
			{ "Disque CD - Frederic Angonas", "35", "disque98HUYU56" } };

	public static void vente(PrintWriter out) {
		out.println("<table>");
		out.println("<tr><th>Nom disque</th><th>Prix</th><th>Ajouter a la panier</th></tr>");
		for (int i = 0; i < leStock.length; i++) {
			out.println("<tr><td>"  +leStock[i][0] +" "+leStock[i][2] +"</td><td>" + leStock[i][1] +" Euros"+ 
				"</td><td><a href='commande?element=disque&order=ajouter&nom="+leStock[i][0]+"&prix="+leStock[i][1]+"&code="+leStock[i][2]+"'>"
				+ "<img width=60% src='C:\\Users\\Fala Omar\\Desktop\\panier.png'</a></td></tr>");
			}
		out.println("</table>");
	}
}
