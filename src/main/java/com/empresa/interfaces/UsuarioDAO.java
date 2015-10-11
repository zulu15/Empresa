package com.empresa.interfaces;

import java.util.List;

import com.empresa.beans.UsuarioDTO;

public interface UsuarioDAO {
	
	public boolean isAdministrador(UsuarioDTO usuario);
	public List<UsuarioDTO> listarUsuario();
	public UsuarioDTO findUsuario(int id);
	public UsuarioDTO findUsuario(UsuarioDTO usuario);
}
