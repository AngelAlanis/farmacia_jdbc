package com.misael.farmacia;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

public class VentanaPrincipal extends JFrame {
    private JPanel      panelPrincipal;
    private JPanel      panelEncabezado;
    private JTabbedPane tabbedPane;
    private JPanel      panelPie;
    private JPanel      panelVenta;
    private JPanel      panelInventario;
    private JPanel      panelHistorialVentas;
    private JPanel      panelProveedores;
    private JPanel      panelAbastecimientos;
    private JLabel      iconoLogo;
    private JPanel      panelUsuario;
    private JLabel      labelLeAtiende;
    private JButton btnUsuario;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnCobrar;
    private JLabel  labelCantidadProductos;
    private JLabel labelCostoTotal;
    private JToolBar trailing;
    private JButton     btnSalir;
    private ImageIcon   iconoSalir;
    private ImageIcon   imagenLogo;
    private Utilidades  utilidades = new Utilidades();


    public void initActionListeners() {
        btnSalir.addActionListener(e -> System.exit(0));
    }

    public void inicializarIconos() {
        iconoSalir = utilidades.imageIcon("/icons/salir.png");
        imagenLogo = utilidades.imageIcon("/logoFarmacia.png");
    }

    public void configurarComponentes() {
        trailing = new JToolBar();
        trailing.setFloatable(false);
        trailing.setBorder(null);
        btnSalir = new JButton("Salir");
        btnSalir.setIcon(iconoSalir);
        trailing.add(Box.createHorizontalGlue());
        trailing.add(btnSalir);
        tabbedPane.putClientProperty(FlatClientProperties.TABBED_PANE_TRAILING_COMPONENT, trailing);

        iconoLogo.setIcon(imagenLogo);
    }

    public VentanaPrincipal() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1366, 728);
        setLocationRelativeTo(null);
        setResizable(false);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setContentPane(panelPrincipal);
        inicializarIconos();
        configurarComponentes();
        initActionListeners();
        setVisible(true);
    }
}
