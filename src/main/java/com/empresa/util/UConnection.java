package com.empresa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class UConnection {
	// Declaramos la variable estatica conexion para mantener una unica
	// instancia de ella
	private static Connection conexion;

	public static Connection getConexion() {

		// Creamos la conexion y la levantamos
		try {
			if (conexion == null) {
				Runtime.getRuntime().addShutdownHook(new Hook());

				// Obtengo los datos del archivo de propiedades
				ResourceBundle rs = ResourceBundle.getBundle("jdbc");
				String usuario = rs.getString("usuario");
				String password = rs.getString("password");
				String url = rs.getString("url");
				String driver = rs.getString("driver");

				// System.out.println("Checkeo si lee bien la configuracion"+usuario
				// + password + url + driver);

				Class.forName(driver);
				conexion = DriverManager.getConnection(url, usuario, password);
			}
			return conexion;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error al levantar la conexion ", e);
		}

	}

	static class Hook extends Thread {
		// Justo antes de finalizar el programa la JVM invocara a este metodo
		// que
		// cerrara la conexion
		public void run() {

			try {
				Connection con = UConnection.getConexion();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

}
