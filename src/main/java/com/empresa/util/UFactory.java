package com.empresa.util;

import java.util.HashMap;
import java.util.ResourceBundle;

public class UFactory {
	private static HashMap<String, Object> instancias = new HashMap<String, Object>();

	public static Object getInstancia(String tipo) {

		try {
			Object instancia = instancias.get(tipo);
			if (instancia == null) {
				ResourceBundle rs = ResourceBundle.getBundle("factory");
				String sClassName = rs.getString(tipo);
				instancia = Class.forName(sClassName).newInstance();
				instancias.put(tipo, instancia);
			}
			return instancia;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Excepcion leyendo el recurso factory ", e);
		}

	}
}
