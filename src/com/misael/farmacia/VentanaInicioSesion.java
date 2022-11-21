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
    private String         idEmpleado;
    Connection connection;

    public void initActionListeners() {
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (connection == null) {
                    connection = new Connection();
                }

                String usuario  = tfUsuario.getText();
                String password = String.valueOf(tfContraseña.getPassword());

                hasAccess = connection.inicioCorrecto(usuario, password);

                if (hasAccess) {
                    isAdmin = connection.isAdmin(usuario);
                    idEmpleado = connection.getIdEmpleado(usuario);
                    VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(connection, isAdmin, idEmpleado);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Datos de inicio de sesión incorrectos o no existen", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public VentanaInicioSesion(Connection connection) {
        hasAccess       = false;
        isAdmin         = false;
        this.connection = connection;
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
