package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	public static final String url = "jdbc:mysql://localhost/gestionstock";
	public static final String login = "root";
	public final static String passwd = "root";

	public static Connection getConnexion() {
		Connection cn = null;
		try {
			// Chargement du driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// R�cup�ration de la connexion
			cn = DriverManager.getConnection(url, login, passwd);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cn;

	}

}
