package com.empresa.interfaces;

import java.util.Collection;

import com.empresa.beans.EmpleadoDTO;

public interface EmpleadoDAO {
	public Collection<EmpleadoDTO> buscarXDepartamento(int deptno);

}
