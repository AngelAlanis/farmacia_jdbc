package com.misael.farmacia;

import com.formdev.flatlaf.FlatClientProperties;
import org.jdatepicker.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.UtilDateModel;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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

        //Productos
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
                try {
                    Producto producto  = new Producto();
                    String[] proveedor = cbProveedor.getSelectedItem().toString().split("-", 2);
                    producto.setFolioProducto(utilidades.verificarTexto(tfFolio.getText()));
                    producto.setDescripcion(utilidades.verificarTexto(tfDescripcion.getText()));
                    producto.setIdProveedor(proveedor[0]);
                    producto.setPrecio(Double.parseDouble(utilidades.verificarTexto(tfPrecio.getText())));
                    producto.setExistencia(Integer.parseInt(utilidades.verificarTexto(tfExistencia.getText())));
                    connection.insertarProducto(producto);

                    JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
                    actualizarTablaProductos();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Verifique los datos ingresados", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnModificarProducto.addActionListener(e -> {
            int selectedRow = tablaProductos.getSelectedRow();

            String folioProducto = String.valueOf(tablaProductos.getValueAt(selectedRow, 0));
            String descripcion   = String.valueOf(tablaProductos.getValueAt(selectedRow, 1));
            String idProveedor   = String.valueOf(tablaProductos.getValueAt(selectedRow, 2)).trim();
            String precio        = String.valueOf(tablaProductos.getValueAt(selectedRow, 4));
            String existencia    = String.valueOf(tablaProductos.getValueAt(selectedRow, 5));

            JTextField           tfFolio       = new JTextField(folioProducto);
            JTextField           tfDescripcion = new JTextField(descripcion);
            JComboBox<Proveedor> cbProveedor   = new JComboBox();
            JTextField           tfPrecio      = new JTextField(precio);
            JTextField           tfExistencia  = new JTextField(existencia);

            tfFolio.setEnabled(false);
            proveedores.forEach(cbProveedor::addItem);

            int size = proveedores.size();
            for (int i = 1; i < size; i++) {
                if (proveedores.get(i).getProveedorClave().trim().equals(idProveedor)) {
                    cbProveedor.setSelectedIndex(i);
                }
            }

            Object[] interfaz = {
                    new JLabel("Modificar producto"),
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

            int confirmacion = JOptionPane.showConfirmDialog(null, interfaz, "Modificar producto", JOptionPane.OK_CANCEL_OPTION);

            if (confirmacion == JOptionPane.OK_OPTION) {
                try {
                    Producto producto = new Producto();

                    producto.setFolioProducto(utilidades.verificarTexto(tfFolio.getText()));
                    producto.setDescripcion(utilidades.verificarTexto(tfDescripcion.getText()));
                    String[] proveedor = cbProveedor.getSelectedItem().toString().split("-", 2);
                    producto.setIdProveedor(proveedor[0].trim());
                    producto.setPrecio(Double.parseDouble(utilidades.verificarTexto(tfPrecio.getText())));
                    producto.setExistencia(Integer.parseInt(utilidades.verificarTexto(tfExistencia.getText())));

                    connection.actualizarProducto(producto);
                    JOptionPane.showMessageDialog(null, "Producto actualizado correctamente");
                    actualizarTablaProductos();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Verifique los datos ingresados", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEliminarProducto.addActionListener(e -> {
            int    selectedRow = tablaProductos.getSelectedRow();
            String idProducto  = String.valueOf(tablaProductos.getValueAt(selectedRow, 0));
            String descripcion = String.valueOf(tablaProductos.getValueAt(selectedRow, 1));

            String mensaje = "¿Está seguro que desea eliminar a " + idProducto + " - " + descripcion + "?\nEsta acción no se puede deshacer";

            int confirmacion = JOptionPane.showConfirmDialog(null, mensaje, "Eliminación de producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirmacion == JOptionPane.OK_OPTION) {
                connection.eliminarProducto(idProducto);
                JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
                actualizarTablaProductos();
            }

        });

        //Empleados
        btnAgregarEmpleado.addActionListener(e -> {
            String[] generos = {"- - Seleccione un género - -", "Masculino", "Femenino"};

            JTextField        tfIdEmpleado = new JTextField();
            JTextField        tfNombre     = new JTextField();
            JComboBox<String> cbGenero     = new JComboBox(generos);
            JDatePickerImpl   datePicker   = generarCalendario();
            JTextField        tfDomicilio  = new JTextField();
            JTextField        tfTelefono   = new JTextField();
            JTextField        tfCorreo     = new JTextField();

            Object[] interfaz = {
                    new JLabel("Agregar empleado"),
                    new JLabel("ID Empleado"),
                    tfIdEmpleado,
                    new JLabel("Nombre"),
                    tfNombre,
                    new JLabel("Género"),
                    cbGenero,
                    new JLabel("Fecha de nacimiento"),
                    datePicker,
                    new JLabel("Domicilio"),
                    tfDomicilio,
                    new JLabel("Teléfono"),
                    tfTelefono,
                    new JLabel("Correo electrónico"),
                    tfCorreo
            };

            int confirmacion = JOptionPane.showConfirmDialog(null, interfaz, "Agregar empleado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (confirmacion == JOptionPane.OK_OPTION) {
                try {
                    Empleado empleado = new Empleado();

                    empleado.setIdEmpleado(utilidades.verificarTexto(tfIdEmpleado.getText()));
                    empleado.setNombre(utilidades.verificarTexto(tfNombre.getText()));
                    empleado.setGenero(utilidades.verificarTexto(String.valueOf(cbGenero.getSelectedItem())));
                    Date selectedDate = (Date) datePicker.getModel().getValue();
                    empleado.setFechaNacimiento(selectedDate);
                    empleado.setDomicilio(utilidades.verificarTexto(tfDomicilio.getText()));
                    empleado.setTelefono(utilidades.verificarTexto(tfTelefono.getText()));
                    empleado.setCorreo(utilidades.verificarTexto(tfCorreo.getText()));

                    connection.insertarEmpleado(empleado);
                    JOptionPane.showMessageDialog(null, "Empleado agregado correctamente");
                    actualizarTablaEmpleados();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Verifique los datos ingresados", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnModificarEmpleado.addActionListener(e -> {
            String[] generos     = {"- - Seleccione un género - -", "Masculino", "Femenino"};
            Empleado empleado    = new Empleado();
            int      selectedRow = tablaEmpleados.getSelectedRow();

            empleado.setIdEmpleado(String.valueOf(tablaEmpleados.getValueAt(selectedRow, 0)));
            empleado.setNombre(String.valueOf(tablaEmpleados.getValueAt(selectedRow, 1)));
            empleado.setGenero(String.valueOf(tablaEmpleados.getValueAt(selectedRow, 2)));
            empleado.setFechaNacimiento((Date) tablaEmpleados.getValueAt(selectedRow, 3));
            empleado.setDomicilio(String.valueOf(tablaEmpleados.getValueAt(selectedRow, 4)));
            empleado.setTelefono(String.valueOf(tablaEmpleados.getValueAt(selectedRow, 5)));
            empleado.setCorreo(String.valueOf(tablaEmpleados.getValueAt(selectedRow, 6)));

            JTextField        tfIdEmpleado = new JTextField(empleado.getIdEmpleado());
            JTextField        tfNombre     = new JTextField(empleado.getNombre());
            JComboBox<String> cbGenero     = new JComboBox(generos);
            JTextField        tfDomicilio  = new JTextField(empleado.getDomicilio());
            JTextField        tfTelefono   = new JTextField(empleado.getTelefono());
            JTextField        tfCorreo     = new JTextField(empleado.getCorreo());
            JDatePickerImpl   datePicker   = generarCalendario();

            String[] date = empleado.getFechaNacimiento().toString().split("-");

            datePicker.getModel().setDate(Integer.parseInt(date[0]), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[2]));
            datePicker.getModel().setSelected(true);

            tfIdEmpleado.setEnabled(false);
            cbGenero.setSelectedItem(empleado.getGenero());

            Object[] interfaz = {
                    new JLabel("Agregar empleado"),
                    new JLabel("ID Empleado"),
                    tfIdEmpleado,
                    new JLabel("Nombre"),
                    tfNombre,
                    new JLabel("Género"),
                    cbGenero,
                    new JLabel("Fecha de nacimiento"),
                    datePicker,
                    new JLabel("Domicilio"),
                    tfDomicilio,
                    new JLabel("Teléfono"),
                    tfTelefono,
                    new JLabel("Correo electrónico"),
                    tfCorreo
            };

            int confirmacion = JOptionPane.showConfirmDialog(null, interfaz, "Modificar empleado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (confirmacion == JOptionPane.OK_OPTION) {
                try {
                    empleado.setIdEmpleado(utilidades.verificarTexto(tfIdEmpleado.getText()));
                    empleado.setNombre(utilidades.verificarTexto(tfNombre.getText()));
                    empleado.setGenero(utilidades.verificarTexto(String.valueOf(cbGenero.getSelectedItem())));
                    Date selectedDate = (Date) datePicker.getModel().getValue();
                    empleado.setFechaNacimiento(selectedDate);
                    empleado.setDomicilio(utilidades.verificarTexto(tfDomicilio.getText()));
                    empleado.setTelefono(utilidades.verificarTexto(tfTelefono.getText()));
                    empleado.setCorreo(utilidades.verificarTexto(tfCorreo.getText()));

                    connection.actualizarEmpleado(empleado);
                    JOptionPane.showMessageDialog(null, "Empleado actualizado correctamente");
                    actualizarTablaEmpleados();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Verifique los datos ingresados", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEliminarEmpleado.addActionListener(e -> {
            int    selectedRow = tablaEmpleados.getSelectedRow();
            String idEmpleado  = String.valueOf(tablaEmpleados.getValueAt(selectedRow, 0));
            String nombre      = String.valueOf(tablaEmpleados.getValueAt(selectedRow, 1));

            String mensaje = "¿Está seguro que desea eliminar a " + idEmpleado + " - " + nombre + "?\nEsta acción no se puede deshacer";

            int confirmacion = JOptionPane.showConfirmDialog(null, mensaje, "Eliminación de producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirmacion == JOptionPane.OK_OPTION) {
                connection.eliminarEmpleado(idEmpleado);
                JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
                actualizarTablaEmpleados();
            }

        });

        //Proveedores

        btnAgregarProveedor.addActionListener(e -> {
            Proveedor proveedor = new Proveedor();

            JTextField tfProveedorClave = new JTextField();
            JTextField tfNombre         = new JTextField();
            JTextField tfDomicilio      = new JTextField();
            JTextField tfTelefono       = new JTextField();
            JTextField tfCorreo         = new JTextField();
            JTextField tfRfc            = new JTextField();

            Object[] interfaz = {
                    new JLabel("Agregar proveedor"),
                    new JLabel("Proveedor clave"),
                    tfProveedorClave,
                    new JLabel("Nombre"),
                    tfNombre,
                    new JLabel("Domicilio"),
                    tfDomicilio,
                    new JLabel("Teléfono"),
                    tfTelefono,
                    new JLabel("Correo"),
                    tfCorreo,
                    new JLabel("RFC"),
                    tfRfc
            };

            int confirmacion = JOptionPane.showConfirmDialog(null, interfaz, "Agregar empleado", JOptionPane.OK_CANCEL_OPTION);

            if (confirmacion == JOptionPane.OK_OPTION) {
                proveedor.setProveedorClave(utilidades.verificarTexto(tfProveedorClave.getText()));
                proveedor.setNombre(utilidades.verificarTexto(tfNombre.getText()));
                proveedor.setDomicilio(utilidades.verificarTexto(tfDomicilio.getText()));
                proveedor.setTelefono(utilidades.verificarTexto(tfTelefono.getText()));
                proveedor.setCorreo(utilidades.verificarTexto(tfCorreo.getText()));
                proveedor.setRfc(utilidades.verificarTexto(tfRfc.getText()));

                connection.insertarProveedor(proveedor);
                JOptionPane.showMessageDialog(null, "Proveedor agregado correctamente.");
                actualizarTablaProveedores();
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

    public JDatePickerImpl generarCalendario() {
        SqlDateModel dateModel  = new SqlDateModel();
        Properties   properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanelImpl  datePanel  = new JDatePanelImpl(dateModel, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        return datePicker;
    }

    public void actualizarTablaProductos() {
        String sqlQuery = """
                SELECT producto.folio_producto, producto.descripcion, producto.id_proveedor, proveedor.nombre, producto.precio, producto.existencia
                FROM producto, proveedor
                WHERE producto.id_proveedor = proveedor.proveedor_clave
                ORDER BY producto.folio_producto;
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
        proveedores.clear();
        proveedores.add(new Proveedor("", "- Seleccione un proveedor - -"));
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
