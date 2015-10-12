package com.empresa.vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.empresa.beans.DepartamentoDTO;
import com.empresa.beans.UsuarioDTO;
import com.empresa.interfaces.DepartamentoDAO;
import com.empresa.interfaces.UsuarioDAO;
import com.empresa.util.UFactory;
import com.empresa.util.UHash;
import com.empresa.util.UTabla;

public class ControladorEventos implements ActionListener, MouseListener {

	public static final String BOTON_ACTUALIZAR_DEPARTAMENTO = "Actualizar Departamento";
	public static final String BOTON_ELIMINAR_DEPARTAMENTO = "Eliminar Departamento";
	public static final String BOTON_AGREGAR_DEPARTAMENTO = "Nuevo Departamento";
	public static final String BOTON_LOGIN_USUARIO = "Ingresar";
	public static final String BOTON_MOSTRAR_DEPARTAMENTOS = "btnAdminDepartamentos";
	private static UsuarioDTO usuarioActivo = new UsuarioDTO();

	private DepartamentoDAO departamentoService = (DepartamentoDAO) UFactory.getInstancia("DEPT");
	private UsuarioDAO usuarioService = (UsuarioDAO) UFactory.getInstancia("USR");

	@Override
	public void actionPerformed(ActionEvent e) {
		String evento = e.getActionCommand();
		switch (evento) {

		case BOTON_MOSTRAR_DEPARTAMENTOS:

			mostrarDepartamentos();

			break;

		case BOTON_LOGIN_USUARIO:

			validarLoginUsuario();

			break;

		case BOTON_ACTUALIZAR_DEPARTAMENTO:

			actualizarDepartamento();

			break;

		case BOTON_AGREGAR_DEPARTAMENTO:

			guardarNuevoDepartamento();

			break;

		case BOTON_ELIMINAR_DEPARTAMENTO:

			eliminarDepartamento();

			break;
		default:
			throw new UnsupportedOperationException("No se reconocio el evento: " + evento);

		}
		actualizarTabla();
		limpiar();
	}

	private void mostrarDepartamentos() {
		// Volvemos a verificar que dicho usuario sea administrador

		// Mostramos la ventana de departamentos si esta cerrada
		if (!VentanaInicio.ventanaDepartamentos.isVisible()) {
			VentanaInicio.ventanaDepartamentos.setVisible(true);
		}

	}

	private void validarLoginUsuario() {
		usuarioActivo = obtenerUsuarioInputs();
		if (isUsuarioCamposValido(usuarioActivo) && existeUsuario(usuarioActivo)) {
			// Mostramos el panel de operaciones
			VentanaInicio.panelAdministrador.show();
			VentanaInicio.admin.setVisible(false);
			VentanaInicio.admin.dispose();
			usuarioActivo = usuarioService.findUsuario(usuarioActivo);
			System.out.println("El usuario que maneja el sistema es: " + usuarioActivo);
			if (usuarioService.isAdministrador(usuarioActivo)) {
				activarAdministrador();
			} else {
				activarOperador();
			}
		} else {
			InternalLogin.labelError.setText("Error, usuario o contraseña incorrectos.");
		}

	}

	private void actualizarDepartamento() {
		// Obtengo los campos de texto
		DepartamentoDTO departamento = obtenerDepartamentoInputs();
		// Verifico el contenido de los mismos
		if (isDepartamentoValido(departamento)) {
			try {
				departamentoService.actualizar(departamento);
				VentanaInicio.ventanaDepartamentos.labelCamposVacios.setText("");
			} catch (Exception e) {

				throw new RuntimeException("Error actualizando registro " + e);
			}
		} else {
			System.out.println("No cumple los requisitos");
			// Si los campos a actualizar son vacios o nulos muestro un mensaje
			// de error
			VentanaInicio.ventanaDepartamentos.labelCamposVacios.setText("Error, no se puede actualizar un registro vacio.");
		}

	}

	private void eliminarDepartamento() {
		// Obtengo la fila seleccionada
		int row = InternalDepartamentos.tabla.getSelectedRow();
		// Obtengo el campo fila,0 es decir el primer campo que corresponde al
		// id

		if (row >= 0) {
			int idEliminar = (int) InternalDepartamentos.tabla.getValueAt(row, 0);

			try {
				departamentoService.eliminar(idEliminar);
			} catch (Exception e) {

				e.printStackTrace();
			}
		} else {
			JOptionPane.showInternalMessageDialog(InternalDepartamentos.panelInputs, "Debe seleccionar un registro", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Crea una ventana desplegable que permite crear un departamento nuevo
	 */
	private void guardarNuevoDepartamento() {

		try {
			ImageIcon icon = createImageIcon("/img/agregar_dto.png", "descricion");
			JTextField txtNombre = new JTextField();
			JTextField txtLocalidad = new JTextField();
			Object[] campos = { "Nombre", txtNombre, "Localidad", txtLocalidad };
			int r = JOptionPane.showConfirmDialog(InternalDepartamentos.panelInputs, campos, "Agregar departamento", JOptionPane.OK_CANCEL_OPTION, 1, icon);
			DepartamentoDTO departamento = new DepartamentoDTO(0, txtNombre.getText(), txtLocalidad.getText());
			if (r == JOptionPane.OK_OPTION && isDepartamentoValido(departamento)) {
				departamentoService.insertar(departamento);
			}
		} catch (Exception e) {

			throw new RuntimeException("No pude persistir el departamento " + e);
		}

	}

	/**
	 * Cuando el usuario hace click en una fila de la tabla se copian esos
	 * elementos y se los coloca en los campos de texto
	 */
	private void manejarClickTabla() {

		int row = InternalDepartamentos.tabla.getSelectedRow();
		VentanaInicio.ventanaDepartamentos.txtNumero.setText(String.valueOf(InternalDepartamentos.tabla.getValueAt(row, 0)));
		VentanaInicio.ventanaDepartamentos.txtNombre.setText((String) InternalDepartamentos.tabla.getValueAt(row, 1));
		VentanaInicio.ventanaDepartamentos.txtLocalidad.setText((String) InternalDepartamentos.tabla.getValueAt(row, 2));

		// Desactivo el campo del id del departamento ya que la clave unica no
		// deberia modificarse
		VentanaInicio.ventanaDepartamentos.txtNumero.setEditable(false);
	}

	/**
	 * Metodo que recoge los campos de texto y los parsea en un departamento
	 * 
	 * @return departamento
	 */
	private DepartamentoDTO obtenerDepartamentoInputs() {
		int id = 0;
		if (!String.valueOf(VentanaInicio.ventanaDepartamentos.txtNumero.getText()).isEmpty())
			id = Integer.parseInt(VentanaInicio.ventanaDepartamentos.txtNumero.getText());
		String nombre = VentanaInicio.ventanaDepartamentos.txtNombre.getText();
		String localidad = VentanaInicio.ventanaDepartamentos.txtLocalidad.getText();
		return new DepartamentoDTO(id, nombre, localidad);
	}

	/**
	 * 
	 * @return un nuevo usuario a partir de los campos ingresados
	 */
	private UsuarioDTO obtenerUsuarioInputs() {
		usuarioActivo = new UsuarioDTO();
		try {
			String nombre = InternalLogin.txtUsuario.getText();
			String password = InternalLogin.txtPassword.getText();
			String hashPassword = UHash.hash(password);
			usuarioActivo.setNombre(nombre);
			usuarioActivo.setPassword(hashPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarioActivo;
	}

	/**
	 * Verifica que el departamento no sea nulo y que sus componentes sean
	 * persistibles
	 * 
	 * @param departamento
	 *            (el departamento a checkear)
	 * @return true or false
	 */
	private boolean isDepartamentoValido(DepartamentoDTO departamento) {

		return (departamento.getDname() != null && !departamento.getDname().isEmpty() && departamento.getLoc() != null && !departamento.getLoc().isEmpty() && String.valueOf(departamento.getDeptno()) != null);
	}

	private boolean isUsuarioCamposValido(UsuarioDTO usuario) {

		return (usuario.getPassword() != null && usuario.getNombre() != null && !usuario.getNombre().isEmpty() && !usuario.getPassword().isEmpty());
	}

	private boolean existeUsuario(UsuarioDTO usuario) {
		return (usuarioService.findUsuario(usuario) != null);
	}

	/**
	 * Metodo que setea campos vacios limpiando los campos de texto
	 */

	private void limpiar() {
		VentanaInicio.ventanaDepartamentos.txtLocalidad.setText("");
		VentanaInicio.ventanaDepartamentos.txtNombre.setText("");
		VentanaInicio.ventanaDepartamentos.txtNumero.setText("");
//		VentanaInicio.ventanaDepartamentos.labelCamposVacios.setText("");

	}

	/**
	 * Actualiza la tabla obteniendo los registros desde la base de datos
	 */
	private void actualizarTabla() {
		DefaultTableModel modelo = UTabla.buildTableModel();
		InternalDepartamentos.tabla.setModel(modelo);
		modelo.fireTableDataChanged();

	}

	/**
	 * Busca y devuelve la imagen especificada
	 */
	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Desactiva los paneles de ganancias y reportes
	 */
	private void activarOperador() {

		VentanaInicio.panelAdministrador.btnGanancias.setEnabled(false);
		VentanaInicio.panelAdministrador.btnReportes.setEnabled(false);

	}

	/**
	 * Activa todos los paneles para el control del administrador
	 */
	private void activarAdministrador() {

		VentanaInicio.panelAdministrador.btnDepartamentos.setEnabled(true);
		VentanaInicio.panelAdministrador.btnEmpleados.setEnabled(true);
		VentanaInicio.panelAdministrador.btnGanancias.setEnabled(true);
		VentanaInicio.panelAdministrador.btnReportes.setEnabled(true);

	}

	@Override
	public void mouseClicked(MouseEvent me) {
		manejarClickTabla();
	}

	// Metodos de sobra por la implementacion

	@Override
	public void mouseReleased(MouseEvent me) {

	}

	@Override
	public void mouseEntered(MouseEvent me) {

	}

	@Override
	public void mouseExited(MouseEvent me) {

	}

	@Override
	public void mousePressed(MouseEvent me) {

	}

}
