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
import com.empresa.interfaces.DepartamentoDAO;
import com.empresa.util.UFactory;
import com.empresa.util.UTabla;

public class ControladorEventos implements ActionListener, MouseListener {

	public static final String BOTON_ACTUALIZAR = "Actualizar Departamento";
	public static final String BOTON_ELIMINAR = "Eliminar Departamento";
	public static final String BOTON_AGREGAR = "Nuevo Departamento";
	private DepartamentoDAO departamentoService = (DepartamentoDAO) UFactory.getInstancia("DEPT");

	@Override
	public void actionPerformed(ActionEvent e) {
		String evento = e.getActionCommand();
		switch (evento) {

		case BOTON_ACTUALIZAR:

			updateRegistro();

			break;

		case BOTON_AGREGAR:

			persistirDepartamento();

			break;

		case BOTON_ELIMINAR:

			eliminarDepartamento();

			break;
		default:
			throw new UnsupportedOperationException("No se reconocio el evento: " + evento);

		}
		actualizarTabla();
		limpiarInputs();
	}

	private void updateRegistro() {
		// Obtengo los campos de texto
		DepartamentoDTO departamento = obtenerDepartamentoInputs();
		// Verifico el contenido de los mismos
		if (isDepartamentoValido(departamento)) {
			try {
				departamentoService.actualizar(departamento);
			} catch (Exception e) {

				throw new RuntimeException("Error actualizando registro " + e);
			}
		} else {
			System.out.println("No paso los requisitos campos vacios o nulos !!");
		}

	}

	private void eliminarDepartamento() {
		// Obtengo la fila seleccionada
		int row = InternalTabla.tabla.getSelectedRow();
		// Obtengo el campo fila,0 es decir el primer campo que corresponde al
		// id
		if (row > 0) {
			int idEliminar = (int) InternalTabla.tabla.getValueAt(row, 0);

			try {
				departamentoService.eliminar(idEliminar);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}else{
			JOptionPane.showInternalMessageDialog(InternalTabla.panelInputs,"Debe seleccionar un registro", "Eliminar",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Crea una ventana desplegable que permite crear un departamento nuevo
	 */
	private void persistirDepartamento() {

		try {
			ImageIcon icon = createImageIcon("/img/icon_add.png", "descricion");
			JTextField txtNombre = new JTextField();
			JTextField txtLocalidad = new JTextField();
			Object[] campos = { "Nombre", txtNombre, "Localidad", txtLocalidad };
			int r = JOptionPane.showConfirmDialog(InternalTabla.panelInputs, campos, "Agregar departamento", JOptionPane.OK_CANCEL_OPTION, 1, icon);
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

		int row = InternalTabla.tabla.getSelectedRow();
		InternalTabla.txtNumero.setText(String.valueOf(InternalTabla.tabla.getValueAt(row, 0)));
		InternalTabla.txtNombre.setText((String) InternalTabla.tabla.getValueAt(row, 1));
		InternalTabla.txtLocalidad.setText((String) InternalTabla.tabla.getValueAt(row, 2));

		// Desactivo el campo del id del departamento ya que la clave unica no
		// deberia modificarse
		InternalTabla.txtNumero.setEditable(false);
	}

	/**
	 * Metodo que recoge los campos de texto y los parsea en un departamento
	 * 
	 * @return departamento
	 */
	private DepartamentoDTO obtenerDepartamentoInputs() {
		int id = 0;
		if (!String.valueOf(InternalTabla.txtNumero.getText()).isEmpty())
			id = Integer.parseInt(InternalTabla.txtNumero.getText());
		String nombre = InternalTabla.txtNombre.getText();
		String localidad = InternalTabla.txtLocalidad.getText();
		return new DepartamentoDTO(id, nombre, localidad);
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

	/**
	 * Metodo que setea campos vacios limpiando los campos de texto
	 */

	private void limpiarInputs() {
		InternalTabla.txtLocalidad.setText("");
		InternalTabla.txtNombre.setText("");
		InternalTabla.txtNumero.setText("");

	}

	/**
	 * Actualiza la tabla obteniendo los registros desde la base de datos
	 */
	private void actualizarTabla() {
		DefaultTableModel modelo = UTabla.buildTableModel();
		InternalTabla.tabla.setModel(modelo);
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
