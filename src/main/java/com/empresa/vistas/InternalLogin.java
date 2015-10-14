package com.empresa.vistas;

import javax.swing.SwingConstants;



public class InternalLogin extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;

	// Variables declaration - do not modify
    protected static javax.swing.JLabel labelError;
	private javax.swing.JButton btnIngresar;
	private javax.swing.JLabel labelIcono;
	private javax.swing.JLabel labelPassword;
	private javax.swing.JLabel labelUsuario;
	private javax.swing.JPanel panelCentral;
	protected static javax.swing.JPasswordField txtPassword;
	protected static javax.swing.JTextField txtUsuario;

	// End of variables declaration

	public InternalLogin() {
		initComponents();

	}

	private void initComponents() {


        panelCentral = new javax.swing.JPanel();
        labelIcono = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        labelPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton("Iniciar");
        labelError = new javax.swing.JLabel();

        setBackground(new java.awt.Color(133, 128, 184));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(false);
        setResizable(true);
        setTitle("Inicio ");

        labelIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png"))); // NOI18N

        labelUsuario.setFont(new java.awt.Font("Waree", 2, 14)); // NOI18N
        labelUsuario.setText("Usuario");

        labelPassword.setFont(new java.awt.Font("Waree", 2, 14)); // NOI18N
        labelPassword.setText("Password");

        btnIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ok.png"))); // NOI18N
        btnIngresar.setActionCommand("Ingresar");
        btnIngresar.addActionListener(new ControladorEventos());

        labelError.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        labelError.setForeground(new java.awt.Color(240, 72, 72));
        labelError.setHorizontalAlignment(SwingConstants.CENTER);

        javax.swing.GroupLayout panelCentralLayout = new javax.swing.GroupLayout(panelCentral);
        panelCentral.setLayout(panelCentralLayout);
        panelCentralLayout.setHorizontalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCentralLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(labelIcono)
                .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCentralLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(labelUsuario))
                    .addGroup(panelCentralLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(labelPassword)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(txtUsuario))
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCentralLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelCentralLayout.setVerticalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentralLayout.createSequentialGroup()
                .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCentralLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(labelIcono))
                    .addGroup(panelCentralLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelUsuario)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelPassword)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(labelError, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

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
