package com.misael.farmacia;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

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
    private JButton     btnUsuario;
    private JButton     btnBuscar;
    private JButton     btnEliminar;
    private JButton     btnCobrar;
    private JLabel      labelCantidadProductos;
    private JLabel      labelCostoTotal;
    private JTable      tableVenta;
    private JScrollPane spVenta;
    private JTable      tablaInventario;
    private JScrollPane spInventario;
    private JTable      tablaProveedores;
    private JScrollPane spProveedores;
    private JTable      tablaHistorialVentas;
    private JScrollPane spHistorialVentas;
    private JTable      tablaAbastecimientos;
    private JScrollPane spAbastecimientos;
    private JToolBar    trailing;
    private JButton     btnSalir;
    private ImageIcon   iconoSalir;
    private ImageIcon   iconoVenta;
    private ImageIcon   iconoInventario;
    private ImageIcon   iconoProveedores;
    private ImageIcon   iconoHistorialVentas;
    private ImageIcon   iconoAbastecimientos;
    private Utilidades  utilidades = new Utilidades();


    public void initActionListeners() {
        btnSalir.addActionListener(e -> System.exit(0));
    }

    public void inicializarIconos() {
        iconoSalir           = utilidades.imageIcon("/icons/salir.png");
        iconoVenta           = utilidades.imageIcon("/icons/ventas.png");
        iconoInventario      = utilidades.imageIcon("/icons/inventario.png");
        iconoProveedores     = utilidades.imageIcon("/icons/proveedor.png");
        iconoHistorialVentas = utilidades.imageIcon("/icons/registro.png");
        iconoAbastecimientos = utilidades.imageIcon("/icons/abastecimiento.png");
    }

    public void configurarComponentes() {
        //Botón de salir en el tabbedPane
        trailing = new JToolBar();
        trailing.setFloatable(false);
        trailing.setBorder(null);
        btnSalir = new JButton("Salir");
        btnSalir.setIcon(iconoSalir);
        trailing.add(Box.createHorizontalGlue());
        trailing.add(btnSalir);
        tabbedPane.putClientProperty(FlatClientProperties.TABBED_PANE_TRAILING_COMPONENT, trailing);

        //Modelo default de la tabla de Ventas
        String[] columnasVenta = {"Folio", "Descripción del producto", "Precio de venta", "Cantidad", "Importe", "Existencia"};
        tableVenta.setModel(new DefaultTableModel(null, columnasVenta));

        //Iconos de las pestañas del tabbedPane
        tabbedPane.setIconAt(0, iconoVenta);
        tabbedPane.setIconAt(1, iconoInventario);
        tabbedPane.setIconAt(2, iconoProveedores);
        tabbedPane.setIconAt(3, iconoHistorialVentas);
        tabbedPane.setIconAt(4, iconoAbastecimientos);
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
