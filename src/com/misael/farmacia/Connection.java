package com.misael.farmacia;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {

    private java.sql.Connection connection;

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/farmacia?allowMultiQueries=true&user=root&password=");
            System.out.println("Conexión realizada con la base de datos");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Problemas al conectar");
            System.exit(0);
        }
    }

    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = null;
            System.out.println("Desconectado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarEmpleado(Empleado empleado) {
        String sentenciaSQL = "INSERT empleado VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sentenciaSQL);

            preparedStatement.setString(1, empleado.getIdEmpleado());
            preparedStatement.setString(2, empleado.getNombre());
            preparedStatement.setString(3, empleado.getGenero());
            preparedStatement.setDate(4, empleado.getFechaNacimiento());
            preparedStatement.setString(5, empleado.getDomicilio());
            preparedStatement.setString(6, empleado.getTelefono());
            preparedStatement.setString(7, empleado.getCorreo());

            int filasAfectadas = preparedStatement.executeUpdate();

            System.out.println("Filas afectadas: " + filasAfectadas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarProveedor(Proveedor proveedor) {
        String sentenciaSQL = "INSERT proveedor VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sentenciaSQL);

            preparedStatement.setString(1, proveedor.getProveedorClave());
            preparedStatement.setString(2, proveedor.getNombre());
            preparedStatement.setString(3, proveedor.getDomicilio());
            preparedStatement.setString(4, proveedor.getTelefono());
            preparedStatement.setString(5, proveedor.getCorreo());
            preparedStatement.setString(6, proveedor.getRfc());

            int filasAfectadas = preparedStatement.executeUpdate();

            System.out.println("Filas afectadas: " + filasAfectadas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarProducto(Producto producto) {
        String sentenciaSQL = "INSERT producto VALUES(?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sentenciaSQL);

            preparedStatement.setString(1, producto.getFolioProducto());
            preparedStatement.setString(2, producto.getDescripcion());
            preparedStatement.setString(3, producto.getIdProveedor());
            preparedStatement.setDouble(4, producto.getPrecio());
            preparedStatement.setInt(5, producto.getExistencia());

            int filasAfectadas = preparedStatement.executeUpdate();

            System.out.println("Filas afectadas: " + filasAfectadas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarAbastecimiento(Abastecimiento abastecimiento) {
        String sentenciaSQL = "INSERT into abastecimiento VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sentenciaSQL);

            preparedStatement.setString(1, null);
            preparedStatement.setTimestamp(2, abastecimiento.getFecha());
            preparedStatement.setString(3, abastecimiento.getClaveProveedor());
            preparedStatement.setString(4, abastecimiento.getIdListaProductos());
            preparedStatement.setDouble(5, abastecimiento.getTotalAPagar());
            preparedStatement.setDouble(6, abastecimiento.getImportePagado());
            preparedStatement.setDouble(7, abastecimiento.getPagoRestante());

            int filasAfectadas = preparedStatement.executeUpdate();

            System.out.println("Filas afectadas: " + filasAfectadas);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertarDetalleAbastecimiento(DetalleAbastecimiento detalleAbastecimiento) {
        String sentenciaSQL = "INSERT into detalle_abastecimiento VALUES(?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sentenciaSQL);

            preparedStatement.setString(1, null);
            preparedStatement.setString(2, detalleAbastecimiento.getIdListaProductos());
            preparedStatement.setString(3, detalleAbastecimiento.getFolioProducto());
            preparedStatement.setString(4, detalleAbastecimiento.getFolioAbastecimiento());
            preparedStatement.setInt(5, detalleAbastecimiento.getCantidad());

            int filasAfectadas = preparedStatement.executeUpdate();

            System.out.println("Filas afectadas: " + filasAfectadas);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        actualizarProducto(detalleAbastecimiento.getCantidad(), detalleAbastecimiento.getFolioProducto());

    }

    public void actualizarEmpleado(String idEmpleado) {
        String   SQLQuery = "SELECT * FROM empleado WHERE id_empleado = ?";
        Empleado empleado = new Empleado();

        try {
            PreparedStatement psQuery = connection.prepareStatement(SQLQuery);
            psQuery.setString(1, idEmpleado);

            ResultSet resultSet = psQuery.executeQuery();

            while (resultSet.next()) {
                empleado.setIdEmpleado(resultSet.getString("id_empleado"));
                empleado.setNombre(resultSet.getString("nombre"));
                empleado.setGenero(resultSet.getString("genero"));
                empleado.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
                empleado.setDomicilio(resultSet.getString("domicilio"));
                empleado.setTelefono(resultSet.getString("telefono"));
                empleado.setCorreo(resultSet.getString("correo"));
            }


            // Definición de los componentes del cuadro de diálogo
            JTextField tfIdEmpleado = new JTextField(empleado.getIdEmpleado());
            tfIdEmpleado.setEnabled(false);
            JTextField tfNombre = new JTextField(empleado.getNombre());
            JComboBox  cbGenero = new JComboBox(new String[]{"Masculino", "Femenino"});
            cbGenero.setSelectedItem(empleado.getGenero());
            JTextField tfFechaNacimiento = new JTextField(empleado.getFechaNacimiento().toString());
            JTextField tfDomicilio       = new JTextField(empleado.getDomicilio());
            JTextField tfTelefono        = new JTextField(empleado.getTelefono());
            JTextField tfCorreo          = new JTextField(empleado.getCorreo());

            Object[] interfazEmpleado = {
                    tfIdEmpleado,
                    tfNombre,
                    cbGenero,
                    tfFechaNacimiento,
                    tfDomicilio,
                    tfTelefono,
                    tfCorreo,
            };

            int confirmacion = JOptionPane.showConfirmDialog(null, interfazEmpleado, "Actualizar empleado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);


            // Actualización del empleado con los parámetros ingresados en los cuadros de texto

            if (confirmacion == JOptionPane.OK_OPTION) {
                String            SQLUpdate = "UPDATE empleado SET nombre=?, genero=?, fecha_nacimiento=?, domicilio=?, telefono=?, correo=? WHERE id_empleado=?";
                PreparedStatement psUpdate  = connection.prepareStatement(SQLUpdate);
                psUpdate.setString(1, tfNombre.getText());
                psUpdate.setString(2, cbGenero.getSelectedItem().toString());
                psUpdate.setString(3, tfFechaNacimiento.getText());
                psUpdate.setString(4, tfDomicilio.getText());
                psUpdate.setString(5, tfTelefono.getText());
                psUpdate.setString(6, tfCorreo.getText());
                psUpdate.setString(7, tfIdEmpleado.getText());

                psUpdate.executeUpdate();

                JOptionPane.showMessageDialog(null, "Actualización a " + tfIdEmpleado.getText() + " exitosa.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarProducto(String folioProducto) {
        String   SQLQuery = "SELECT * FROM producto WHERE folio_producto=?";
        Producto producto = new Producto();

        try {
            PreparedStatement psQuery = connection.prepareStatement(SQLQuery);
            psQuery.setString(1, folioProducto);

            ResultSet resultSet = psQuery.executeQuery();

            while (resultSet.next()) {
                producto.setFolioProducto(resultSet.getString("folio_producto"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                producto.setIdProveedor(resultSet.getString("id_proveedor"));
                producto.setPrecio(resultSet.getDouble("precio"));
                producto.setExistencia(resultSet.getInt("existencia"));
            }

            System.out.println(producto);

            JTextField tfFolioProducto = new JTextField(producto.getFolioProducto());
            tfFolioProducto.setEnabled(false);
            JTextField tfDescripcion = new JTextField(producto.getDescripcion());
            JTextField tfProveedor   = new JTextField(producto.getIdProveedor());
            JTextField tfPrecio      = new JTextField(String.valueOf(producto.getPrecio()));
            JTextField tfExistencia  = new JTextField(String.valueOf(producto.getExistencia()));

            Object[] interfazProducto = {
                    tfDescripcion,
                    tfProveedor,
                    tfPrecio,
                    tfExistencia
            };

            int confirmacion = JOptionPane.showConfirmDialog(null, interfazProducto, "Actualizar producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);

            if (confirmacion == JOptionPane.OK_OPTION) {
                String SQLUpdate = "UPDATE producto SET descripcion=?, id_proveedor=?, precio=?, existencia=? WHERE folio_producto=?";

                PreparedStatement psUpdate = connection.prepareStatement(SQLUpdate);

                psUpdate.setString(1, tfDescripcion.getText());
                psUpdate.setString(2, tfProveedor.getText());
                psUpdate.setString(3, tfPrecio.getText());
                psUpdate.setString(4, tfExistencia.getText());
                psUpdate.setString(5, folioProducto);

                psUpdate.executeUpdate();

                JOptionPane.showMessageDialog(null, "Actualización a " + folioProducto + " exitosa.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarProveedor(String proveedorClave) {
        String    SQLQuery  = "SELECT * FROM proveedor WHERE proveedor_clave=?";
        Proveedor proveedor = new Proveedor();

        try {
            PreparedStatement psQuery = connection.prepareStatement(SQLQuery);
            psQuery.setString(1, proveedorClave);
            ResultSet resultSet = psQuery.executeQuery();

            while (resultSet.next()) {
                proveedor.setProveedorClave(resultSet.getString("proveedor_clave"));
                proveedor.setNombre(resultSet.getString("nombre"));
                proveedor.setDomicilio(resultSet.getString("domicilio"));
                proveedor.setTelefono(resultSet.getString("telefono"));
                proveedor.setCorreo(resultSet.getString("correo"));
                proveedor.setRfc(resultSet.getString("rfc"));
            }

            JTextField tfProveedorClave = new JTextField(proveedor.getProveedorClave());
            tfProveedorClave.setEnabled(false);
            JTextField tfNombre    = new JTextField(proveedor.getNombre());
            JTextField tfDomicilio = new JTextField(proveedor.getDomicilio());
            JTextField tfTelefono  = new JTextField(proveedor.getTelefono());
            JTextField tfCorreo    = new JTextField(proveedor.getCorreo());
            JTextField tfRFC       = new JTextField(proveedor.getRfc());

            Object[] interfazProveedor = {
                    tfProveedorClave,
                    tfNombre,
                    tfDomicilio,
                    tfTelefono,
                    tfCorreo,
                    tfRFC
            };

            int confirmacion = JOptionPane.showConfirmDialog(null, interfazProveedor, "Actualización proveedor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);

            if (confirmacion == JOptionPane.OK_OPTION) {
                String SQLUpdate = "UPDATE proveedor SET nombre=?, domicilio=?, telefono=?, correo=?, rfc=? WHERE proveedor_clave=?";

                PreparedStatement psUpdate = connection.prepareStatement(SQLUpdate);
                psUpdate.setString(1, tfNombre.getText());
                psUpdate.setString(2, tfDomicilio.getText());
                psUpdate.setString(3, tfTelefono.getText());
                psUpdate.setString(4, tfCorreo.getText());
                psUpdate.setString(5, tfRFC.getText());
                psUpdate.setString(6, proveedorClave);

                psUpdate.executeUpdate();

                JOptionPane.showMessageDialog(null, "Actualización a " + proveedorClave + " exitosa.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarProducto(int cantidadNueva, String folioProducto) {
        String sentenciaQuery   = "SELECT existencia FROM producto where folio_producto = '" + folioProducto + "'";
        int    cantidadAnterior = 0;
        int    cantidadTotal    = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sentenciaQuery);

            while (resultSet.next()) {
                cantidadAnterior = resultSet.getInt("existencia");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cantidadTotal = cantidadAnterior + cantidadNueva;

        String sentenciaSQL = "UPDATE producto SET existencia = ? WHERE producto.folio_producto = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sentenciaSQL);
            preparedStatement.setInt(1, cantidadTotal);
            preparedStatement.setString(2, folioProducto);

            int filasAfectadas = preparedStatement.executeUpdate();

            System.out.println("Cantidad anterior: " + cantidadAnterior);
            System.out.println("Cantidad nueva: " + cantidadTotal);
            System.out.println("Filas afectadas: " + filasAfectadas);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection() {
        connect();
    }

}
