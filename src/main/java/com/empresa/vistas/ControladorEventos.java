package com.empresa.vistas;

import java.applet.Applet;
import java.applet.AudioClip;
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

	// Declaracion de constantes que son los eventos que genera el usuario
	public static final String MENU_ABRIR_PANEL = "Abrir";
	public static final String MENU_CERRAR_PANEL = "Cerrar";
	public static final String BOTON_ACTUALIZAR_DEPARTAMENTO = "Actualizar Departamento";
	public static final String BOTON_ELIMINAR_DEPARTAMENTO = "Eliminar Departamento";
	public static final String BOTON_AGREGAR_DEPARTAMENTO = "Nuevo Departamento";
	public static final String BOTON_LOGIN_USUARIO = "Ingresar";
	public static final String BOTON_MOSTRAR_DEPARTAMENTOS = "btnAdminDepartamentos";
	private static UsuarioDTO usuarioActivo = new UsuarioDTO();

	// Servicios para realizar las acciones sobre la base de datos
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

		case MENU_ABRIR_PANEL:

			mostrarPanelControl();

			break;
		default:
			JOptionPane.showInternalMessageDialog(VentanaInicio.escritorio, "La función " + evento + " no se encuentra disponible", "Operacion no identificada", JOptionPane.ERROR_MESSAGE);
		}
		actualizarTabla();
		limpiar();
	}

	/**
	 * Metodo que abre la ventana interna de departamentos si es que esta
	 * cerrada
	 */
	private void mostrarDepartamentos() {
		if (!VentanaInicio.ventanaDepartamentos.isVisible()) {
			VentanaInicio.ventanaDepartamentos.setVisible(true);
		}

	}

	/**
	 * Metodo que valida los datos ingresados por el usuario en su login y de
	 * ser un usuario correcto habilita el panel de administracion activando las
	 * funciones que le corresponda segun su cargo
	 */
	private void validarLoginUsuario() {
		usuarioActivo = obtenerUsuarioInputs();
		if (isUsuarioCamposValido(usuarioActivo) && existeUsuario(usuarioActivo)) {
			// Ejecutamos un sonido de bienvenida
			new HiloSonido().start();
			// Mostramos el panel de operaciones
			VentanaInicio.ventanaLogin.setVisible(false);
			VentanaInicio.ventanaLogin.dispose();
			mostrarPanelControl();
		} else {
			InternalLogin.labelError.setText("Error, usuario o contraseña incorrectos.");
		}

	}

	/**
	 * Metodo que muestra la ventana interna del panel de control dependiendo si
	 * el usuario se logeo y de su nivel de usuario habilita o desactiva
	 * funciones
	 */
	private void mostrarPanelControl() {

		// Buscamos al usuario que esta usando el sistema y lo dejamos en
		// memoria
		usuarioActivo = usuarioService.findUsuario(usuarioActivo);
		if (usuarioActivo != null) {
			if (!VentanaInicio.panelControl.isVisible())
				VentanaInicio.panelControl.show();
			System.out.println("El usuario que maneja el sistema es: " + usuarioActivo);
			if (usuarioService.isAdministrador(usuarioActivo)) {
				activarAdministrador();
			} else {
				activarOperador();
			}
		} else {
			JOptionPane.showInternalMessageDialog(VentanaInicio.escritorio, "Debe iniciar sesión, antes de manejar el sistema", "Usuario no identificado", JOptionPane.WARNING_MESSAGE);
		}

	}

	/**
	 * Metodo que obtiene los datos de los campos de texto y los actualiza
	 */
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
	/**
	 * Metodo que elimina un departamento seleccionado
	 * a partir de la tabla por el usuario
	 */
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
	 * Metodo que recoge los campos de texto y los convierte en un departamento
	 * 
	 * @return nuevo departamento
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
	 * @return un nuevo usuario a partir de los campos ingresados del login
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
		// VentanaInicio.ventanaDepartamentos.labelCamposVacios.setText("");

	}

	/**
	 * Actualiza la tabla obteniendo los registros desde la base de datos
	 */
	private void actualizarTabla() {
		DefaultTableModel modelo = UTabla.buildTableModel("departamento", new String[] { "Número", "Nombre", "Localidad" });
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
	 * para el uso del operador
	 */
	private void activarOperador() {

		VentanaInicio.panelControl.btnGanancias.setEnabled(false);
		VentanaInicio.panelControl.btnReportes.setEnabled(false);

	}

	/**
	 * Activa todos los paneles para el control del administrador
	 */
	private void activarAdministrador() {

		VentanaInicio.panelControl.btnDepartamentos.setEnabled(true);
		VentanaInicio.panelControl.btnEmpleados.setEnabled(true);
		VentanaInicio.panelControl.btnGanancias.setEnabled(true);
		VentanaInicio.panelControl.btnReportes.setEnabled(true);

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

	/**
	 *Clase que permite ejecutar un sonido 
	 * cuando el usuario hace su logeo
	 */
	class HiloSonido extends Thread {
		public void run() {
			try {
				AudioClip audio;
				audio = Applet.newAudioClip(getClass().getResource("/audio/vista.wav"));
				audio.play();
			} catch (Exception e) {
				System.out.println("Error ejecutando sonido " + e);
			}
		}
	}
}
