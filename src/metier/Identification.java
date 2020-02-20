package metier;


import javax.servlet.http.Cookie;

public class Identification {

	public static String chercheNom(Cookie[] cookies) {
		// TODO Auto-generated method stub
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("nomCookie")) {
				return cookie.getValue();
			}
		}
		return "inconnu";
	}

}
