package com.empresa.vistas;

public class VentanaInicio extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	// Declaracion de variables
	protected static javax.swing.JDesktopPane escritorio;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JMenu menuEdit;
	private javax.swing.JMenu menuFile;
	private javax.swing.JMenuItem subMenuEdit1;
	private javax.swing.JMenuItem subMenuEdit2;
	private javax.swing.JMenuItem subMenuFile1;
	private static ControladorEventos controlador = new ControladorEventos();
	//

	// Vistas que conforman la ventana principal
	protected static InternalDepartamentos ventanaDepartamentos;
	protected static InternalLogin admin;
	protected static InternalPanelUsuario panelAdministrador;

	//

	public VentanaInicio() {
		initComponents();

	}

	private void initComponents() {
		panelAdministrador = new InternalPanelUsuario();
		admin = new InternalLogin();
		ventanaDepartamentos = new InternalDepartamentos();
		escritorio = new javax.swing.JDesktopPane();
		menuBar = new javax.swing.JMenuBar();
		menuFile = new javax.swing.JMenu();
		subMenuFile1 = new javax.swing.JMenuItem();
		menuEdit = new javax.swing.JMenu();
		subMenuEdit1 = new javax.swing.JMenuItem();
		subMenuEdit2 = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Aplicacion");

		// Agrego las ventanas internas
		escritorio.add(ventanaDepartamentos);
		escritorio.add(admin);
		escritorio.add(panelAdministrador);
		// Dejo por defecto visible la ventana de administrador abierta para el
		// login
		admin.show();

		menuFile.setText("File");

		subMenuFile1.setText("jMenuItem1");

		menuFile.add(subMenuFile1);

		menuBar.add(menuFile);

		menuEdit.setText("Edit");

		subMenuEdit1.setText("CLICKEAME");
		subMenuEdit1.addActionListener(controlador);
		subMenuEdit1.setActionCommand("PRUEBA");

		menuEdit.add(subMenuEdit1);

		subMenuEdit2.setText("jMenuItem3");
		menuEdit.add(subMenuEdit2);

		menuBar.add(menuEdit);

		setJMenuBar(menuBar);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE));

		pack();
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
