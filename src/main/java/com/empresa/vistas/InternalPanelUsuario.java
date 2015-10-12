package com.empresa.vistas;



public class InternalPanelUsuario extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;
	// Variables declaration - do not modify                     
    protected  javax.swing.JButton btnDepartamentos;
    protected  javax.swing.JButton btnEmpleados;
    protected  javax.swing.JButton btnGanancias;
    protected  javax.swing.JMenuItem btnPantallaTamaño;
    protected  javax.swing.JButton btnReportes;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel labelDepartamentos;
    private javax.swing.JLabel labelEmpleados;
    private javax.swing.JLabel labelGanancias;
    private javax.swing.JLabel labelReportes;
    private javax.swing.JMenuItem menuPerfil;
    private javax.swing.JPanel panelCentral;
    private static final ControladorEventos controlador = new ControladorEventos();
    // End of variables declaration   
    public InternalPanelUsuario() {
        initComponents();
        agregarEventos();
    }
                     
    private void agregarEventos() {
        btnDepartamentos.addActionListener(controlador);
        btnDepartamentos.setActionCommand("btnAdminDepartamentos");
        btnEmpleados.addActionListener(controlador);
        btnEmpleados.setActionCommand("btnEmpleados");
        btnGanancias.addActionListener(controlador);
        btnGanancias.setActionCommand("btnGanancias");
        btnReportes.addActionListener(controlador);
        btnReportes.setActionCommand("btnReportes");
    }

	private void initComponents() {

        panelCentral = new javax.swing.JPanel();
        btnEmpleados = new javax.swing.JButton();
        btnDepartamentos = new javax.swing.JButton();
        labelDepartamentos = new javax.swing.JLabel();
        labelEmpleados = new javax.swing.JLabel();
        btnReportes = new javax.swing.JButton();
        labelReportes = new javax.swing.JLabel();
        btnGanancias = new javax.swing.JButton();
        labelGanancias = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuPerfil = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        btnPantallaTamaño = new javax.swing.JMenuItem();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Panel de control");

        btnEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png"))); // NOI18N

        btnDepartamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/departamentos.png"))); // NOI18N

        labelDepartamentos.setFont(new java.awt.Font("gargi", 1, 14)); // NOI18N
        labelDepartamentos.setForeground(new java.awt.Color(62, 181, 41));
        labelDepartamentos.setText("Administrar departamentos");

        labelEmpleados.setFont(new java.awt.Font("gargi", 1, 14)); // NOI18N
        labelEmpleados.setForeground(new java.awt.Color(62, 181, 41));
        labelEmpleados.setText("  Administrar empleados");

        btnReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reportes.png"))); // NOI18N

        labelReportes.setFont(new java.awt.Font("gargi", 1, 14)); // NOI18N
        labelReportes.setForeground(new java.awt.Color(19, 131, 228));
        labelReportes.setText("  Administrar reportes");

        btnGanancias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ganancias.png"))); // NOI18N

        labelGanancias.setFont(new java.awt.Font("gargi", 1, 14)); // NOI18N
        labelGanancias.setForeground(new java.awt.Color(19, 131, 228));
        labelGanancias.setText("  Administrar ganancias");

        javax.swing.GroupLayout panelCentralLayout = new javax.swing.GroupLayout(panelCentral);
        panelCentral.setLayout(panelCentralLayout);
        panelCentralLayout.setHorizontalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCentralLayout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCentralLayout.createSequentialGroup()
                        .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDepartamentos)
                            .addGroup(panelCentralLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCentralLayout.createSequentialGroup()
                        .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelReportes)
                            .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80)))
                .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEmpleados)
                    .addComponent(btnEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelGanancias)
                    .addComponent(btnGanancias, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86))
        );
        panelCentralLayout.setVerticalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentralLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelCentralLayout.createSequentialGroup()
                        .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCentralLayout.createSequentialGroup()
                                .addComponent(btnEmpleados)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelEmpleados))
                            .addGroup(panelCentralLayout.createSequentialGroup()
                                .addComponent(btnDepartamentos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelDepartamentos)))
                        .addGap(28, 28, 28)
                        .addComponent(btnGanancias)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelGanancias))
                    .addGroup(panelCentralLayout.createSequentialGroup()
                        .addComponent(btnReportes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelReportes)))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jMenu1.setText("Opciones");

        menuPerfil.setText("Modificar perfil");
        jMenu1.add(menuPerfil);

        jMenu5.setText("Pantalla");

        btnPantallaTamaño.setText("Modificar tamaño");
        jMenu5.add(btnPantallaTamaño);

        jMenu1.add(jMenu5);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }                 


                
}
