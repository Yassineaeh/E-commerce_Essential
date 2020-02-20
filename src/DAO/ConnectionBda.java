package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBda {
	private static Connection con;
	private ConnectionBda(){}
	
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Mini_projet","root","");
			System.out.println("cnx succée ...............................");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return con;
	}	
}
