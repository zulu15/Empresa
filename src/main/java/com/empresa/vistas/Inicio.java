package com.empresa.vistas;



public class Inicio extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private javax.swing.JDesktopPane escritorio;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JMenu menuEdit;
	private javax.swing.JMenu menuFile;
	private javax.swing.JMenuItem subMenuEdit1;
	private javax.swing.JMenuItem subMenuEdit2;
	private javax.swing.JMenuItem subMenuFile1;
	private InternalTabla tabla;

	
	public Inicio() {
		initComponents();
		
	}



	private void initComponents() {
		tabla = new InternalTabla();
		escritorio = new javax.swing.JDesktopPane();
		menuBar = new javax.swing.JMenuBar();
		menuFile = new javax.swing.JMenu();
		subMenuFile1 = new javax.swing.JMenuItem();
		menuEdit = new javax.swing.JMenu();
		subMenuEdit1 = new javax.swing.JMenuItem();
		subMenuEdit2 = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Aplicacion");
		escritorio.add(tabla);
		tabla.show();
		menuFile.setText("File");

		subMenuFile1.setText("jMenuItem1");

		menuFile.add(subMenuFile1);

		menuBar.add(menuFile);

		menuEdit.setText("Edit");

		subMenuEdit1.setText("jMenuItem2");

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
		setLocationRelativeTo(null);
		setVisible(true);
	}


}
