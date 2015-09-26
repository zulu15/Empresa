package com.empresa.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import com.empresa.beans.DepartamentoDTO;
import com.empresa.interfaces.DepartamentoDAO;
import com.empresa.util.UConnection;

import java.sql.PreparedStatement;

public class DepartamentoImpl implements DepartamentoDAO {

	@Override
	public Collection<DepartamentoDTO> buscarTodos() {
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		// La lista de departamentos a devolver
		ArrayList<DepartamentoDTO> departamentos = null;
		try {

			conexion = UConnection.getConexion();
			String sql = "SELECT * FROM departamento";
			pstm = conexion.prepareStatement(sql);
			rs = pstm.executeQuery();

			departamentos = new ArrayList<DepartamentoDTO>();
			DepartamentoDTO departamento = null;
			while (rs.next()) {
				departamento = new DepartamentoDTO();
				departamento.setDeptno(rs.getInt("deptno"));
				departamento.setDname(rs.getString("dname"));
				departamento.setLoc(rs.getString("loc"));
				departamentos.add(departamento);

			}
			return departamentos;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Problema listando los departamentos");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();

			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}

	}
}
