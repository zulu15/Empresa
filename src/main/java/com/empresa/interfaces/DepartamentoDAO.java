package com.empresa.interfaces;

import java.util.Collection;

import com.empresa.beans.DepartamentoDTO;

public interface DepartamentoDAO {

	public Collection<DepartamentoDTO> listar();

	public void eliminar(int id) throws Exception;

	public void actualizar(DepartamentoDTO departamento) throws Exception;

	public void insertar(DepartamentoDTO departamento) throws Exception;

}
