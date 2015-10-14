package com.empresa.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class UTabla {

	public static DefaultTableModel buildTableModel(String tabla) {
		 DefaultTableModel modelo = null;
		try {
			StringBuilder sql = new StringBuilder("SELECT * FROM ");
			sql.append(tabla);
			
			
			Connection conecion = UConnection.getConexion();
			PreparedStatement pstm = conecion.prepareStatement(String.valueOf(sql));
		
			ResultSet rs = pstm.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			
			Vector<String> columnNames = new Vector<String>();
			int columnCount = metaData.getColumnCount();
			for (int column = 1; column <= columnCount; column++) {
				columnNames.add(metaData.getColumnName(column));
			}

			// data of the table
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			while (rs.next()) {
				Vector<Object> vector = new Vector<Object>();
				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
					vector.add(rs.getObject(columnIndex));
				}
				data.add(vector);
			}
			 modelo = new DefaultTableModel(data, columnNames) {

				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return false;
				}
			};

		} catch (Exception e) {
			throw new RuntimeException("Error creando modelo de tabla "+e);
		}

		return modelo;

	}

	
	public static DefaultTableModel buildTableModel(String tabla,String[] columnas) {
		StringBuilder sql = new StringBuilder("SELECT * FROM ");
		sql.append(tabla);
		DefaultTableModel modelo = null;
		try {
			Connection conecion = UConnection.getConexion();
			PreparedStatement pstm = conecion.prepareStatement(String.valueOf(sql));

			ResultSet rs = pstm.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			
			Vector<String> columnNames = new Vector<String>();
			int columnCount = metaData.getColumnCount();
			for (int i = 0; i< columnas.length; i++) {
				columnNames.add(columnas[i]);
			}

			// data of the table
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			while (rs.next()) {
				Vector<Object> vector = new Vector<Object>();
				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
					vector.add(rs.getObject(columnIndex));
				}
				data.add(vector);
			}
			 modelo = new DefaultTableModel(data, columnNames) {

				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return false;
				}
			};

		} catch (Exception e) {
			throw new RuntimeException("Error creando modelo de tabla "+e);
		}

		return modelo;

	}
}
