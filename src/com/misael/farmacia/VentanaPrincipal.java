package com.misael.farmacia;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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
    private JButton     btnDetallesAbastecimiento;
    private JPanel      panelPieAbastecimientos;
    private JTextField  tfBusquedaHistorialVentas;
    private JLabel      labelBusquedaHistorialVentas;
    private JButton     btnDetallesHistorialVentas;
    private JToolBar    trailing;

    private Utilidades utilidades;

    private Connection connection;

    private final Vector<String> columnasProductos       = new Vector<>(Arrays.asList("Folio", "Descripción del producto", "Clave Proveedor", "Nombre proveedor", "Precio", "Existencia"));
    private final Vector<String> columnasVenta           = new Vector<>(Arrays.asList("Folio", "Fecha", "Descripción del producto", "Precio de venta", "Cantidad", "Importe", "Existencia"));
    private final Vector<String> columnasEmpleados       = new Vector<>(Arrays.asList("ID Empleado", "Nombre", "Genero", "Fecha de Nacimiento", "Domicilio", "Teléfono", "Correo"));
    private final Vector<String> columnasProvedores      = new Vector<>(Arrays.asList("Clave Proveedor", "Nombre", "Domicilio", "Teléfono", "Correo", "RFC"));
    private final Vector<String> columnasHistorialVentas = new Vector<>(Arrays.asList("Folio Venta", "Fecha", "ID Detalles", "ID Empleado", "Nombre empleado", "Importe", "Total pagado"));
    private final Vector<String> columnasAbastecimientos = new Vector<>(Arrays.asList("Clave", "Fecha", "Clave Proveedor", "Nombre Proveedor", "ID Detalles", "Importe", "Total pagado", "Restante"));

    private ArrayList<Proveedor> proveedores = new ArrayList<>();

    public void initActionListeners() {
        btnSalir.addActionListener(e -> System.exit(0));

        btnAgregarProducto.addActionListener(e -> {
            JTextField           tfFolio       = new JTextField();
            JTextField           tfDescripcion = new JTextField();
            JComboBox<Proveedor> cbProveedor   = new JComboBox();
            JTextField           tfPrecio      = new JTextField();
            JTextField           tfExistencia  = new JTextField();


            proveedores.forEach(cbProveedor::addItem);

            Object[] interfaz = {
                    new JLabel("Agregar producto"),
                    new JLabel("Folio"),
                    tfFolio,
                    new JLabel("Descripción"),
                    tfDescripcion,
                    new JLabel("Proveedor"),
                    cbProveedor,
                    new JLabel("Precio"),
                    tfPrecio,
                    new JLabel("Existencia"),
                    tfExistencia
            };

            int confirmacion = JOptionPane.showConfirmDialog(null, interfaz, "Agregar producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (confirmacion == JOptionPane.OK_OPTION) {
                String   folioProducto = tfFolio.getText();
                String   descripcion   = tfDescripcion.getText();
                String[] proveedor     = cbProveedor.getSelectedItem().toString().split("-", 2);
                String   idProveedor   = proveedor[0];
                double   precio        = Double.parseDouble(tfPrecio.getText());
                int      existencia    = Integer.parseInt(tfExistencia.getText());

                connection.insertarProducto(new Producto(folioProducto, descripcion, idProveedor, precio, existencia));

                JOptionPane.showMessageDialog(null, "Producto agregado correctamente");

                actualizarTablaProductos();
            }
        });
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
        actualizarTablaProductos();
        actualizarTablaEmpleados();
        actualizarTablaProveedores();
        actualizarTablaHistorialVentas();
        actualizarTablaAbastecimientos();

        //Iconos de las pestañas del tabbedPane
        tabbedPane.setIconAt(0, iconoVenta);
        tabbedPane.setIconAt(1, iconoInventario);
        tabbedPane.setIconAt(2, iconoEmpleado);
        tabbedPane.setIconAt(3, iconoProveedores);
        tabbedPane.setIconAt(4, iconoHistorialVentas);
        tabbedPane.setIconAt(5, iconoAbastecimientos);
    }

    public Vector<Vector<Object>> obtenerDatosTabla(String sqlQuery) {
        Vector<Vector<Object>> data = new Vector<>();
        int                    columns;

        try {
            Statement statement = connection.db_connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            columns = resultSet.getMetaData().getColumnCount();

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

        return data;

    }

    public void actualizarTablaProductos() {
        String sqlQuery = """
                SELECT producto.folio_producto, producto.descripcion, producto.id_proveedor, proveedor.nombre, producto.precio, producto.existencia
                FROM producto, proveedor
                WHERE producto.id_proveedor = proveedor.proveedor_clave;
                """;
        Vector<Vector<Object>> data = obtenerDatosTabla(sqlQuery);
        tablaProductos.setModel(new DefaultTableModel(data, columnasProductos));
    }

    public void actualizarTablaEmpleados() {
        String                 sqlQuery = "SELECT * FROM empleado";
        Vector<Vector<Object>> data     = obtenerDatosTabla(sqlQuery);
        tablaEmpleados.setModel(new DefaultTableModel(data, columnasEmpleados));
    }

    public void actualizarTablaProveedores() {
        String                 sqlQuery = "SELECT * FROM proveedor";
        Vector<Vector<Object>> data     = obtenerDatosTabla(sqlQuery);
        tablaProveedores.setModel(new DefaultTableModel(data, columnasProvedores));

        // Filas
        for (int i = 0; i < data.size(); i++) {
            String proveedorClave = data.get(i).get(0).toString();
            String nombre         = data.get(i).get(1).toString();
            proveedores.add(new Proveedor(proveedorClave, nombre));
        }
    }

    public void actualizarTablaHistorialVentas() {
        String sqlQuery = """ 
                SELECT venta.folio_venta, venta.fecha, venta.id_detalles, venta.id_empleado, empleado.nombre, venta.total_a_pagar, venta.total_pagado
                FROM venta, empleado
                WHERE venta.id_empleado = empleado.id_empleado;
                """;
        Vector<Vector<Object>> data = obtenerDatosTabla(sqlQuery);
        tablaHistorialVentas.setModel(new DefaultTableModel(data, columnasHistorialVentas));
    }

    public void actualizarTablaAbastecimientos() {
        String sqlQuery = """
                SELECT abastecimiento.clave, abastecimiento.fecha, abastecimiento.clave_proveedor, proveedor.nombre, abastecimiento.id_detalles, abastecimiento.importe_pagado, abastecimiento.total_a_pagar, abastecimiento.pago_restante
                FROM abastecimiento, proveedor
                WHERE abastecimiento.clave_proveedor = proveedor.proveedor_clave;
                """;
        Vector<Vector<Object>> data = obtenerDatosTabla(sqlQuery);
        tablaAbastecimientos.setModel(new DefaultTableModel(data, columnasAbastecimientos));
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
