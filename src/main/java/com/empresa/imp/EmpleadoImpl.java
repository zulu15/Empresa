package com.empresa.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

import com.empresa.beans.EmpleadoDTO;
import com.empresa.interfaces.EmpleadoDAO;
import com.empresa.util.UConnection;

public class EmpleadoImpl implements EmpleadoDAO {

	@Override
	public Collection<EmpleadoDTO> buscarXDepartamento(int deptno) {
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conexion = UConnection.getConexion();
			String sql = "SELECT * FROM empleados WHERE deptno = ?";
			pstm = conexion.prepareStatement(sql);
			pstm.setInt(1, deptno);
			rs = pstm.executeQuery();
			// Creo la lista que voy a devolver
			Vector<EmpleadoDTO> empleados = new Vector();
			EmpleadoDTO empleado = null;
			while (rs.next()) {
				empleado = new EmpleadoDTO();
				empleado.setEmpno(rs.getInt("empno"));
				empleado.setEname(rs.getString("ename"));
				empleado.setHiredate(rs.getDate("hiredate"));
				empleado.setDeptno(rs.getInt("deptno"));
				empleados.add(empleado);
			}
			return empleados;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("No pude listar los empleados por id de deptno " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();

			} catch (Exception e2) {
				e2.printStackTrace();
				throw new RuntimeException();
			}
		}

	}

}
