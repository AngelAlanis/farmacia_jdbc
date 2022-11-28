package com.misael.farmacia;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
    Utilidades utilidades = new Utilidades();

    public void initActionListeners() {
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (connection == null) {
                    connection = new Connection();
                }

                String usuario  = utilidades.verificarTexto(tfUsuario.getText());
                String password = utilidades.verificarTexto(String.valueOf(tfContraseña.getPassword()));

                hasAccess = connection.inicioCorrecto(usuario, password);

                if (hasAccess) {
                    isAdmin    = connection.isAdmin(usuario);
                    idEmpleado = connection.getIdEmpleado(usuario);
                    VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(connection, isAdmin, idEmpleado);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Datos de inicio de sesión incorrectos o no existen", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        tfContraseña.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

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
        getRootPane().setDefaultButton(btnIngresar);
        initActionListeners();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
