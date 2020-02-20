package metier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Client {

	private String nomClient;
	private String passwd;

	public Client(String nomClient, String motDePasse) {
		this.nomClient = nomClient;
		this.passwd = motDePasse;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public static Client getClient(Connection c, String nomClient, String passwd) throws SQLException {
		Client client = null;
		Statement s = c.createStatement();
		String query = "SELECT * FROM Client WHERE password= '" + passwd + "' AND nom='" + nomClient + "'";
		ResultSet rs = s.executeQuery(query);
		while (rs.next()) {
			String Name = rs.getString("nom");
			String Password = rs.getString("Password");
			client = new Client(Name, Password);
		}
		return client;
	}

	public void addClient(Connection c) throws SQLException {
		Statement s = c.createStatement();
		String query = "INSERT INTO Client VALUES('" + this.getNomClient() + "','" + this.getPasswd() + "')";
		s.executeUpdate(query);
	}

}
