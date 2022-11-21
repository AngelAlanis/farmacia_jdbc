package com.misael.farmacia;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaInicioSesion extends JFrame {
    private JPanel         panelPrincipal;
    private JLabel         labelLogo;
    private JLabel         labelIngreso;
    private JLabel         labelInstrucciones;
    private JLabel         labelUsuario;
    private JTextField     tfUsuario;
    private JPasswordField tfContraseña;
    private JLabel         labelContraseña;
    private JButton        btnIngresar;
    private boolean        isAdmin;
    private boolean        hasAccess;
    Connection connection;

    public void initActionListeners() {
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connection = new Connection();
                String usuario  = tfUsuario.getText();
                String password = String.valueOf(tfContraseña.getPassword());

                boolean inicioCorrecto = connection.inicioCorrecto(usuario, password);

                if (inicioCorrecto) {
                    isAdmin = connection.isAdmin(usuario);
                    VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(connection, isAdmin);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrecto");
                }
            }
        });
    }

    public VentanaInicioSesion() {
        hasAccess = false;
        isAdmin   = false;
        setTitle("Inicio de sesión");
        setSize(480, 600);
        setContentPane(panelPrincipal);
        initActionListeners();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
