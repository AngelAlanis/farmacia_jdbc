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
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class VentanaPrincipal extends JFrame {

    private ImageIcon   iconoAbastecimientos;
    private ImageIcon   iconoEmpleado;
    private ImageIcon   iconoHistorialVentas;
    private ImageIcon   iconoInventario;
    private ImageIcon   iconoProveedores;
    private ImageIcon   iconoSalir;
    private ImageIcon   iconoVenta;
    private JButton     btnAgregarAbastecimiento;
    private JButton     btnAgregarEmpleado;
    private JButton     btnAgregarProducto;
    private JButton     btnAgregarProveedor;
    private JButton     btnBuscar;
    private JButton     btnCobrar;
    private JButton     btnEliminar;
    private JButton     btnEliminarEmpleado;
    private JButton     btnEliminarProducto;
    private JButton     btnEliminarProveedor;
    private JButton     btnModificarEmpleado;
    private JButton     btnModificarProducto;
    private JButton     btnModificarProveedor;
    private JButton     btnSalir;
    private JButton     btnUsuario;
    private JLabel      iconoLogo;
    private JLabel      labelBuscarProducto;
    private JLabel      labelBuscarProveedor;
    private JLabel      labelBusquedaAbastecimiento;
    private JLabel      labelBusquedaEmpleado;
    private JLabel      labelCantidadProductos;
    private JLabel      labelCostoTotal;
    private JLabel      labelLeAtiende;
    private JPanel      panelAbastecimientos;
    private JPanel      panelEmpleados;
    private JPanel      panelEncabezado;
    private JPanel      panelHistorialVentas;
    private JPanel      panelPieEmpleados;
    private JPanel      panelPieProductos;
    private JPanel      panelPieProveedores;
    private JPanel      panelPieVenta;
    private JPanel      panelPrincipal;
    private JPanel      panelProductos;
    private JPanel      panelProveedores;
    private JPanel      panelUsuario;
    private JPanel      panelVenta;
    private JScrollPane spAbastecimientos;
    private JScrollPane spEmpleados;
    private JScrollPane spHistorialVentas;
    private JScrollPane spProductos;
    private JScrollPane spProveedores;
    private JScrollPane spVenta;
    private JTabbedPane tabbedPane;
    private JTable      tablaAbastecimientos;
    private JTable      tablaEmpleados;
    private JTable      tablaHistorialVentas;
    private JTable      tablaProductos;
    private JTable      tablaProveedores;
    private JTable      tableVenta;
    private JTextField  tfBusquedaAbastecimiento;
    private JTextField  tfBusquedaEmpleado;
    private JTextField  tfBusquedaProducto;
    private JTextField  tfBusquedaProveedor;
    private JToolBar    trailing;

    private Utilidades utilidades;

    private Connection connection;

    private final String[] columnasVenta           = {"Folio", "Fecha", "Descripción del producto", "Precio de venta", "Cantidad", "Importe", "Existencia"};
    private final String[] columnasProductos       = {"Folio", "Descripción del producto", "Clave Proveedor", "Nombre proveedor", "Precio", "Existencia"};
    private final String[] columnasEmpleados       = {"ID Empleado", "Nombre", "Genero", "Fecha de Nacimiento", "Domicilio", "Teléfono", "Correo"};
    private final String[] columnasProvedores      = {"Clave Proveedor", "Nombre", "Domicilio", "Teléfono", "Correo", "RFC"};
    private final String[] columnasHistorialVentas = {"Folio Venta", "ID Detalles", "ID Empleado", "Importe", "Total pagado"};
    private final String[] columnasAbastecimientos = {"Clave", "Fecha", "Clave Proveedor", "ID Detalles", "Importe", "Total pagado", "Restante"};


    public void initActionListeners() {
        btnSalir.addActionListener(e -> System.exit(0));
    }

    public void inicializarIconos() {
        iconoSalir           = utilidades.imageIcon("/icons/salir.png");
        iconoVenta           = utilidades.imageIcon("/icons/ventas.png");
        iconoInventario      = utilidades.imageIcon("/icons/inventario.png");
        iconoProveedores     = utilidades.imageIcon("/icons/proveedor.png");
        iconoEmpleado        = utilidades.imageIcon("/icons/empleados.png");
        iconoHistorialVentas = utilidades.imageIcon("/icons/registro.png");
        iconoAbastecimientos = utilidades.imageIcon("/icons/abastecimiento.png");
    }

    public void configurarComponentes() {
        //Botón de salir en el tabbedPane
        trailing = new JToolBar();
        btnSalir = new JButton("Salir");
        btnSalir.setIcon(iconoSalir);
        trailing.setFloatable(false);
        trailing.setBorder(null);
        trailing.add(Box.createHorizontalGlue());
        trailing.add(btnSalir);
        tabbedPane.putClientProperty(FlatClientProperties.TABBED_PANE_TRAILING_COMPONENT, trailing);

        //Modelo default de la tablas
        tableVenta.setModel(new DefaultTableModel(null, columnasVenta));
        tablaProductos.setModel(new DefaultTableModel(null, columnasProductos));
        tablaEmpleados.setModel(new DefaultTableModel(null, columnasEmpleados));
        tablaProveedores.setModel(new DefaultTableModel(null, columnasProvedores));
        tablaHistorialVentas.setModel(new DefaultTableModel(null, columnasHistorialVentas));
        tablaAbastecimientos.setModel(new DefaultTableModel(null, columnasAbastecimientos));

        //Iconos de las pestañas del tabbedPane
        tabbedPane.setIconAt(0, iconoVenta);
        tabbedPane.setIconAt(1, iconoInventario);
        tabbedPane.setIconAt(2, iconoEmpleado);
        tabbedPane.setIconAt(3, iconoProveedores);
        tabbedPane.setIconAt(4, iconoHistorialVentas);
        tabbedPane.setIconAt(5, iconoAbastecimientos);
    }

    public DefaultTableModel fillTable(String sqlQuery) {
        Vector<Vector<Object>> data        = new Vector<>();
        int                    columns;
        Vector<Object>         columnNames = new Vector<>();

        try {
            Statement statement = connection.db_connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            columns = resultSet.getMetaData().getColumnCount();

            for (int i = 1; i <= columns; i++) {
                columnNames.add(resultSet.getMetaData().getColumnName(i));
            }

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();

                for (int i = 1; i <= columns; i++) {
                    row.add(resultSet.getObject(i));
                }

                data.add(row);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new DefaultTableModel(data, columnNames);

    }

    public void actualizarTablaProductos(){
        String sqlQuery = "SELECT * FROM proveedores";
        tablaProveedores.setModel(fillTable(sqlQuery));
    }

    public VentanaPrincipal() {
        utilidades = new Utilidades();
        connection = new Connection();
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
