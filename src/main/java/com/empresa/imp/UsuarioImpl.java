package com.empresa.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.empresa.beans.UsuarioDTO;
import com.empresa.interfaces.UsuarioDAO;
import com.empresa.util.UConnection;
import com.empresa.util.UFactory;

public class UsuarioImpl implements UsuarioDAO {

	private Connection conexion = UConnection.getConexion();

	@Override
	public boolean isAdministrador(UsuarioDTO usuario) {
		PreparedStatement pstm = null;
		ResultSet resultado = null;
		try {
			String sql = "SELECT acceso FROM usuario WHERE id = ?";
			pstm = conexion.prepareStatement(sql);
			pstm.setLong(1, usuario.getId());
			resultado = pstm.executeQuery();
			while (resultado.next()) {

				int acceso = resultado.getInt("acceso");
				return (acceso == 1);

			}
		} catch (Exception e) {
			throw new RuntimeException("Error obteniendo acceso usuario " + usuario + " -- " + e);
		}
		return false;
	}

	@Override
	public List<UsuarioDTO> listarUsuario() {
		PreparedStatement pstm = null;
		ResultSet resultado = null;
		List<UsuarioDTO> listadoUsuario = new ArrayList<UsuarioDTO>();
		try {
			String sql = "SELECT * FROM usuario";
			pstm = conexion.prepareStatement(sql);
			resultado = pstm.executeQuery();
			UsuarioDTO usuario = null;
			while (resultado.next()) {
				usuario = new UsuarioDTO();
				usuario.setId(resultado.getLong("id"));
				usuario.setNombre(resultado.getString("nombre"));
				usuario.setPassword(resultado.getString("password"));
				usuario.setAcceso(resultado.getInt("acceso"));
				listadoUsuario.add(usuario);
			}
		} catch (Exception e) {
			throw new RuntimeException("Error listando los usuarios: " + e);
		} finally {
			try {
				if (resultado != null)
					resultado.close();
				if (pstm != null)
					pstm.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return listadoUsuario;
	}

	@Override
	public UsuarioDTO findUsuario(UsuarioDTO usuario) {
		UsuarioDTO usuarioEncontrado = null;
		PreparedStatement pstm = null;
		ResultSet resultado = null;
		if (usuario != null) {
			try {
				String sql = "SELECT * FROM usuario WHERE password = ? AND nombre = ?";
				pstm = conexion.prepareStatement(sql);
				pstm.setString(1, usuario.getPassword());
				pstm.setString(2, usuario.getNombre());
				resultado = pstm.executeQuery();
				while (resultado.next()) {
					usuarioEncontrado = new UsuarioDTO();
					usuarioEncontrado.setId(resultado.getLong("id"));
					usuarioEncontrado.setNombre(resultado.getString("nombre"));
					usuarioEncontrado.setPassword(resultado.getString("password"));
					usuarioEncontrado.setAcceso(resultado.getInt("acceso"));
				}

			} catch (Exception e) {
				throw new RuntimeException("Error buscando el usuario " + usuario + " -- " + e);
			} finally {
				try {
					if (resultado != null)
						resultado.close();
					if (pstm != null)
						pstm.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return usuarioEncontrado;
	}

	public static void main(String a[]) {

		UsuarioDAO service = (UsuarioDAO) UFactory.getInstancia("USR");
		UsuarioDTO usuarioPrueba = new UsuarioDTO(1, "joaquin", "c2f6d50c988c8320a487587a9eb44e77", 1);

	}

	/*
	 * 
	 * 
	 * @Override public UsuarioDTO findUsuario(int id) { UsuarioDTO
	 * usuarioEncontrado = null; PreparedStatement pstm = null; ResultSet
	 * resultado = null; try { String sql =
	 * "SELECT * FROM usuario WHERE id = ?"; pstm =
	 * conexion.prepareStatement(sql); pstm.setInt(1, id); resultado =
	 * pstm.executeQuery(); while (resultado.next()) { usuarioEncontrado = new
	 * UsuarioDTO(); usuarioEncontrado.setId(resultado.getLong("id"));
	 * usuarioEncontrado.setNombre(resultado.getString("nombre"));
	 * usuarioEncontrado.setPassword(resultado.getString("password"));
	 * usuarioEncontrado.setAcceso(resultado.getInt("acceso")); }
	 * 
	 * } catch (Exception e) { throw new
	 * RuntimeException("Error buscando el usuario con id" + id + " -- " + e); }
	 * finally { try { if (resultado != null) resultado.close(); if (pstm !=
	 * null) pstm.close(); } catch (Exception e2) { e2.printStackTrace(); } }
	 * 
	 * return usuarioEncontrado; }
	 */

}
