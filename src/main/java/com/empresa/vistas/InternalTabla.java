package com.empresa.vistas;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.empresa.util.UTabla;

public class InternalTabla extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;
	private javax.swing.JPanel panelCentral;
	private javax.swing.JScrollPane scrollista;
	protected static javax.swing.JTable tabla;
	protected static JPanel panelInputs;
	private javax.swing.JLabel labelLocalidad;
	private javax.swing.JLabel labelNombre;
	private javax.swing.JLabel labelNumero;
	protected static JTextField txtNombre;
	protected static JTextField txtLocalidad;
	protected static JTextField txtNumero;
	private JPanel panelBotones;
	protected static JButton btnActualizar;
	protected static JButton btnAgregar;
	protected static JButton btnEliminar;
	private ControladorEventos controlador;

	public InternalTabla() {

		initComponents();
		agregarControlador();
	}

	private void agregarControlador() {
		controlador = new ControladorEventos();
		btnActualizar.addActionListener(controlador);
		btnEliminar.addActionListener(controlador);
		btnAgregar.addActionListener(controlador);
		tabla.addMouseListener(controlador);
	}

	private void initComponents() {

		panelCentral = new javax.swing.JPanel();
		scrollista = new javax.swing.JScrollPane();
		tabla = new javax.swing.JTable();
		panelInputs = new javax.swing.JPanel();
		labelNombre = new javax.swing.JLabel();
		labelLocalidad = new javax.swing.JLabel();
		labelNumero = new JLabel();
		txtNumero = new JTextField();
		txtNombre = new javax.swing.JTextField();
		txtLocalidad = new javax.swing.JTextField();
		panelBotones = new javax.swing.JPanel();
		btnActualizar = new javax.swing.JButton();
		btnAgregar = new javax.swing.JButton();
		btnEliminar = new javax.swing.JButton();

		// Atributos de redimension del InternalFrame
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);

		scrollista.setToolTipText("");

		tabla.setModel(UTabla.buildTableModel());
		scrollista.setViewportView(tabla);
		if (tabla.getColumnModel().getColumnCount() > 0) {
			tabla.getColumnModel().getColumn(0).setResizable(false);
			tabla.getColumnModel().getColumn(1).setResizable(false);
			tabla.getColumnModel().getColumn(2).setResizable(false);
		}

		panelInputs.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Modificar Departamentos")));

		labelNombre.setFont(new java.awt.Font("Waree", 0, 14)); // NOI18N
		labelNombre.setText("Nombre ");

		labelLocalidad.setFont(new java.awt.Font("Waree", 0, 14)); // NOI18N
		labelLocalidad.setText("Localidad");

		labelNumero.setFont(new java.awt.Font("Waree", 0, 14)); // NOI18N
		labelNumero.setText("Número ");

		javax.swing.GroupLayout panelInputsLayout = new javax.swing.GroupLayout(panelInputs);
		panelInputs.setLayout(panelInputsLayout);
		panelInputsLayout.setHorizontalGroup(panelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				panelInputsLayout
						.createSequentialGroup()
						.addGap(23, 23, 23)
						.addGroup(
								panelInputsLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addGroup(panelInputsLayout.createSequentialGroup().addComponent(labelNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(txtNumero))
										.addGroup(
												panelInputsLayout
														.createSequentialGroup()
														.addGroup(
																panelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(labelLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(panelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(txtLocalidad).addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panelInputsLayout.setVerticalGroup(panelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				panelInputsLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								panelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(labelNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(3, 3, 3)
						.addGroup(
								panelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								panelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(labelLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txtLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(21, Short.MAX_VALUE)));

		panelBotones.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(1, 15, 12)));

		btnActualizar.setForeground(new java.awt.Color(94, 102, 214));
		btnActualizar.setText("Actualizar Departamento");
		panelBotones.add(btnActualizar);

		btnAgregar.setForeground(new java.awt.Color(117, 220, 103));
		btnAgregar.setText("Nuevo Departamento");
		panelBotones.add(btnAgregar);

		btnEliminar.setForeground(new java.awt.Color(223, 77, 77));
		btnEliminar.setText("Eliminar Departamento");
		panelBotones.add(btnEliminar);

		javax.swing.GroupLayout panelCentralLayout = new javax.swing.GroupLayout(panelCentral);
		panelCentral.setLayout(panelCentralLayout);
		panelCentralLayout.setHorizontalGroup(panelCentralLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(scrollista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
				.addGroup(
						panelCentralLayout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(panelInputs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)).addContainerGap()));
		panelCentralLayout.setVerticalGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				panelCentralLayout.createSequentialGroup().addComponent(scrollista, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18)
						.addComponent(panelInputs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(30, 30, 30)
						.addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(36, 36, 36)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(panelCentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addComponent(panelCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)));

		pack();

	}

}
