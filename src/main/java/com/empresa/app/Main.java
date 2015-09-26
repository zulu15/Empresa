package com.empresa.app;

import java.sql.Connection;
import java.util.Collection;

import com.empresa.beans.EmpleadoDTO;
import com.empresa.interfaces.DepartamentoDAO;
import com.empresa.interfaces.EmpleadoDAO;
import com.empresa.util.UConnection;
import com.empresa.util.UFactory;

public class Main {

	public static void main(String[] args) {

//		DepartamentoDAO departamentoService = new DepartamentoImpl();
//		Collection<DepartamentoDTO> dptos = departamentoService.buscarTodos();
//		for (DepartamentoDTO departamentoDTO : dptos) {
//			System.out.println(departamentoDTO);
//		}

		EmpleadoDAO empleadoService = (EmpleadoDAO) UFactory.getInstancia("EMP");

		Collection<EmpleadoDTO> empleados = empleadoService.buscarXDepartamento(2);
		for (EmpleadoDTO empleadoDTO : empleados) {
			System.out.println(empleadoDTO);
		}
		
	}

}
