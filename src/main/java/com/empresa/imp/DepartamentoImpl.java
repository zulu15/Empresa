package com.empresa.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.empresa.beans.DepartamentoDTO;
import com.empresa.interfaces.DepartamentoDAO;
import com.empresa.util.UConnection;

import java.sql.PreparedStatement;

public class DepartamentoImpl implements DepartamentoDAO {

	@Override
	public Collection<DepartamentoDTO> listar() {
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

			}
		}

	}

	@Override
	public void eliminar(int id) {
		PreparedStatement pstm = null;
		try {
			String sql = "DELETE FROM departamento WHERE deptno = ?";
			Connection conexion = UConnection.getConexion();
			pstm = conexion.prepareStatement(sql);
			pstm.setInt(1, id);
			int registros = pstm.executeUpdate();
			if (registros != 1)
				throw new RuntimeException("Error se afectaron varios registros");

		} catch (Exception e) {
			throw new RuntimeException("No pude eliminar el departamento con id " + id + " -- " + e);
		} finally {
			try {
				if (pstm != null)
					pstm.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public void actualizar(DepartamentoDTO departamento) {
		PreparedStatement pstm = null;
		try {
			String sql = "UPDATE departamento SET dname = ? , loc = ? WHERE deptno = ?";
			Connection conexion = UConnection.getConexion();
			pstm = conexion.prepareStatement(sql);
			pstm.setString(1, departamento.getDname());
			pstm.setString(2, departamento.getLoc());
			pstm.setInt(3, departamento.getDeptno());
			int registros = pstm.executeUpdate();
			if (registros != 1)
				throw new RuntimeException("Error se afectaron varios registros");
		} catch (Exception e) {
			throw new RuntimeException("No pude actualizar el departamento " + departamento + " --" + e);
		} finally {
			try {
				if (pstm != null)
					pstm.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	@Override
	public void insertar(DepartamentoDTO departamento) {
		PreparedStatement pstm = null;
		try {
			String sql = "INSERT INTO departamento (deptno, dname, loc) VALUES (?,?,?)";
			Connection conexion = UConnection.getConexion();
			pstm = conexion.prepareStatement(sql);
			pstm.setInt(1, 0);
			pstm.setString(2, departamento.getDname());
			pstm.setString(3, departamento.getLoc());
			int registros = pstm.executeUpdate();
			if (registros != 1)
				throw new RuntimeException("Error se afectaron " + registros + " registros");
		} catch (Exception e) {
			throw new RuntimeException("Error insertando departamento " + e);
		} finally {
			if (pstm != null)
				try {
					pstm.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

	// public static void main(String... a){
	// DepartamentoImpl impl = new DepartamentoImpl();
	//
	// }
}
