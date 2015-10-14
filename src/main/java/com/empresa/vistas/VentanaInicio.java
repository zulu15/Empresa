package com.empresa.vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class VentanaInicio extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	// Declaracion de la ventana contenedora 
	protected static javax.swing.JDesktopPane escritorio;
	//Declaracion de las variables que conforman el menu de la aplicacion
	//Declaro la barra de menu
	private JMenuBar barraMenu;
	//Declaro los menu
	private JMenu menuOpciones;
	private JMenu menuMasInformacion;
	private JMenu menuPanelControl;
	//Declaro los item de los menu
	protected static JMenuItem itemAbrirPanel;
	private JMenuItem itemInfoPanel;
	private JMenuItem itemSalirPrograma;
	private JMenuItem itemAcerca;
	
	


	// Vistas que conforman la ventana principal
	protected static InternalDepartamentos ventanaDepartamentos;
	protected static InternalLogin ventanaLogin;
	protected static InternalPanelUsuario panelControl;

	//Controlador de eventos
	private static ControladorEventos controlador = new ControladorEventos();


	public VentanaInicio() {
		initComponents();
		agregarMenu();

	}



	private void initComponents() {
		panelControl = new InternalPanelUsuario();
		ventanaLogin = new InternalLogin();
		ventanaDepartamentos = new InternalDepartamentos();
		escritorio = new javax.swing.JDesktopPane();


		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Aplicacion");

		// Agrego las ventanas internas
		escritorio.add(ventanaDepartamentos);
		escritorio.add(ventanaLogin);
		escritorio.add(panelControl);
		// Dejo por defecto visible la ventana de administrador abierta para el
		// login
		ventanaLogin.show();

		

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE));

		pack();
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	private void agregarMenu() {
		//Creo la barra que contendra todos los menues 
		barraMenu = new JMenuBar();
		//Creo el menu de opciones
		menuOpciones = new JMenu("Opciones");
		//Creo el menu de informacion
		menuMasInformacion = new JMenu("Más información");
		//Creo el menu del panel de control
		menuPanelControl = new JMenu("Panel de control");
		//Creo el item abrir para el menu de panel de control
		itemAbrirPanel = new JMenuItem("Abrir");
		itemAbrirPanel.addActionListener(controlador);
		//Creo el item abrir para el menu de panel de control
		itemInfoPanel= new JMenuItem("Información");
		itemInfoPanel.addActionListener(new ControladorSimple());
		//Creo el item para salir del programa
		itemSalirPrograma = new JMenuItem("Salir del programa");
		itemSalirPrograma.addActionListener(new ControladorSimple());
		//Creo el item para la informacion
		itemAcerca = new JMenuItem("Sobre el programa");
		itemAcerca.addActionListener(new ControladorSimple());
		
		//Agrego los item al menu
		menuPanelControl.add(itemAbrirPanel);
		menuPanelControl.add(itemInfoPanel);
		menuMasInformacion.add(itemAcerca);
		
		
		//Agreo el menu de panel de control al menu de opciones
		menuOpciones.add(menuPanelControl);
		menuOpciones.add(itemSalirPrograma);
		
		
		//Agrego los menu padre a la barra principal
		barraMenu.add(menuOpciones);
		barraMenu.add(menuMasInformacion);
		
		//Indico cual sera la barra de menu
		setJMenuBar(barraMenu);
		
	}
	
	class ControladorSimple implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//Si se clickeo para salir del programa
			if(e.getSource() == itemSalirPrograma){
				int respuesta=JOptionPane.showInternalConfirmDialog(escritorio,"¿Desea salir del programa?", "Salir", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
				if(respuesta==JOptionPane.YES_OPTION) System.exit(0);
			}
			//Si se clickeo para obtener informacion
			if(e.getSource() == itemAcerca){
				JOptionPane.showInternalMessageDialog(escritorio, "Programa open source desarrollado por Joaquin Sanchez \n Email: joaquinsanchez1994@hotmail.com \n Github: zulu15 \n Proyecto: https://github.com/zulu15/Empresa", "Información", JOptionPane.INFORMATION_MESSAGE);
			}
			
			if(e.getSource() == itemInfoPanel){
				JOptionPane.showInternalMessageDialog(escritorio, "El panel de control es la herramienta que le permite gestionar: \n -Los departamentos que se encuentran cargados. \n -Un registro de clientes. \n -Los reportes realizados. \n -Las ganancias obtenidas. \n *Administrador (todos los modulos), Operador (Clientes, Departamentos)", "Información", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}

}
