package com.misael.farmacia;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class Connection {

    public java.sql.Connection db_connection;

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            db_connection = DriverManager.getConnection("jdbc:mysql://localhost/farmacia?allowMultiQueries=true&user=root&password=");
            System.out.println("Conexión realizada con la base de datos");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Problemas al conectar");
            System.exit(0);
        }
    }

    public void disconnect() {
        try {
            if (db_connection != null) {
                db_connection.close();
            }
            db_connection = null;
            System.out.println("Desconectado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Métodos de inserción

    public void insertarEmpleado(Empleado empleado) {
        String sentenciaSQL = "INSERT empleado VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = db_connection.prepareStatement(sentenciaSQL);

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
            PreparedStatement preparedStatement = db_connection.prepareStatement(sentenciaSQL);

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
            PreparedStatement preparedStatement = db_connection.prepareStatement(sentenciaSQL);

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
            PreparedStatement preparedStatement = db_connection.prepareStatement(sentenciaSQL);

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
            PreparedStatement preparedStatement = db_connection.prepareStatement(sentenciaSQL);

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

        actualizarProducto(detalleAbastecimiento.getCantidad(), detalleAbastecimiento.getFolioProducto(), true);

    }

    public void insertarVenta(Venta venta) {
        String sentenciaSQL = "INSERT into venta VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = db_connection.prepareStatement(sentenciaSQL);

            preparedStatement.setString(1, null);
            preparedStatement.setTimestamp(2, venta.getFecha());
            preparedStatement.setString(3, venta.getIdDetalles());
            preparedStatement.setString(4, venta.getIdEmpleado());
            preparedStatement.setDouble(5, venta.getTotalAPagar());
            preparedStatement.setDouble(6, venta.getTotalPagado());

            int filasAfectadas = preparedStatement.executeUpdate();

            System.out.println("Filas afectadas: " + filasAfectadas);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertarDetalleVenta(DetalleVenta detalleVenta) {
        String sentenciaSQL = "INSERT into detalle_venta VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = db_connection.prepareStatement(sentenciaSQL);

            preparedStatement.setString(1, null);
            preparedStatement.setString(2, detalleVenta.getIdDetalles());
            preparedStatement.setString(3, detalleVenta.getFolioProducto());
            preparedStatement.setInt(4, detalleVenta.getCantidad());

            int filasAfectadas = preparedStatement.executeUpdate();

            System.out.println("Filas afectadas: " + filasAfectadas);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        actualizarProducto(detalleVenta.getCantidad(), detalleVenta.getFolioProducto(), false);
    }


    // Métodos de actualización

    public void actualizarEmpleado(String idEmpleado) {
        String SQLQuery  = "SELECT * FROM empleado WHERE id_empleado = ?";
        String SQLUpdate = "UPDATE empleado SET nombre=?, genero=?, fecha_nacimiento=?, domicilio=?, telefono=?, correo=? WHERE id_empleado=?";

        ArrayList<JTextField> listaTextFields = crearInterfaz(SQLQuery, idEmpleado);
        actualizarTabla(SQLUpdate, listaTextFields);
    }

    public void actualizarProducto(Producto producto) {
        String SQLUpdate = """
                UPDATE producto SET descripcion=?, id_proveedor=?, precio=?, existencia=?
                WHERE folio_producto=?
                """;
        try {
            PreparedStatement preparedStatement = db_connection.prepareStatement(SQLUpdate);
            preparedStatement.setString(1, producto.getDescripcion());
            preparedStatement.setString(2, producto.getIdProveedor());
            preparedStatement.setDouble(3, producto.getPrecio());
            preparedStatement.setInt(4, producto.getExistencia());
            preparedStatement.setString(5, producto.getFolioProducto());

            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.println("Actualización producto:" + filasAfectadas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarProveedor(String proveedorClave) {
        String SQLQuery  = "SELECT * FROM proveedor WHERE proveedor_clave=?";
        String SQLUpdate = "UPDATE proveedor SET nombre=?, domicilio=?, telefono=?, correo=?, rfc=? WHERE proveedor_clave=?";

        ArrayList<JTextField> listaTextFields = crearInterfaz(SQLQuery, proveedorClave);
        actualizarTabla(SQLUpdate, listaTextFields);
    }

    // Métodos de eliminación

    public void eliminarEmpleado(String idEmpleado) {
        String sentenciaSQL = "DELETE FROM empleado WHERE id_empleado=?";

        try {
            PreparedStatement preparedStatement = db_connection.prepareStatement(sentenciaSQL);
            preparedStatement.setString(1, idEmpleado);
            int filasAfectadas = preparedStatement.executeUpdate();

            System.out.println("Filas afectadas: " + filasAfectadas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarProveedor(String proveedorClave) {
        String sentenciaSQL = "DELETE FROM proveedor WHERE proveedor_clave=?";

        try {
            PreparedStatement preparedStatement = db_connection.prepareStatement(sentenciaSQL);
            preparedStatement.setString(1, proveedorClave);
            int filasAfectadas = preparedStatement.executeUpdate();

            System.out.println("Filas afectadas: " + filasAfectadas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarProducto(String folioProducto) {
        String sentenciaSQL = "DELETE FROM producto WHERE folio_producto=?";

        try {
            PreparedStatement preparedStatement = db_connection.prepareStatement(sentenciaSQL);
            preparedStatement.setString(1, folioProducto);
            int filasAfectadas = preparedStatement.executeUpdate();

            System.out.println("Filas afectadas: " + filasAfectadas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarAbastecimiento(String clave) {
        String sentenciaSQLAbastecimiento        = "DELETE FROM abastecimiento WHERE clave=?";
        String sentenciaSQLDetalleAbastecimiento = "DELETE FROM detalle_abastecimiento WHERE folio_abastecimiento=?";

        try {
            db_connection.setAutoCommit(false);

            PreparedStatement psAbastecimiento = db_connection.prepareStatement(sentenciaSQLAbastecimiento);
            PreparedStatement psDetalles       = db_connection.prepareStatement(sentenciaSQLDetalleAbastecimiento);

            psAbastecimiento.setString(1, clave);
            psDetalles.setString(1, clave);

            psAbastecimiento.executeUpdate();
            psDetalles.executeUpdate();

            db_connection.commit();
            db_connection.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();

            try {
                db_connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }

    // Métodos extra

    public void actualizarTabla(String SQLUpdate, ArrayList<JTextField> textFields) {
        if (!textFields.isEmpty()) {

            int numeroColumnas = textFields.size();

            try {
                PreparedStatement psUpdate = db_connection.prepareStatement(SQLUpdate);

                for (int i = 1; i < textFields.size(); i++) {
                    psUpdate.setString(i, String.valueOf(textFields.get(i).getText()));
                }

                psUpdate.setString(numeroColumnas, textFields.get(0).getText());

                psUpdate.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, "Actualización exitosa.");
        }

    }

    public ArrayList<JTextField> crearInterfaz(String SQLQuery, String llave) {
        ArrayList<String>     datosIniciales    = new ArrayList<>();
        ArrayList<String>     nombresColumnas   = new ArrayList<>();
        ArrayList<Object>     elementosInterfaz = new ArrayList<>();
        ArrayList<JTextField> textFields        = new ArrayList<>();

        try {
            PreparedStatement psQuery = db_connection.prepareStatement(SQLQuery);
            psQuery.setString(1, llave);
            ResultSet resultSet = psQuery.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "Registro no encontrado");
                return null;
            }

            int columnCount = resultSet.getMetaData().getColumnCount();

            if (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    nombresColumnas.add(resultSet.getMetaData().getColumnName(i));
                    datosIniciales.add(resultSet.getString(i));
                    textFields.add(new JTextField(datosIniciales.get(i - 1)));
                }
            }

            // Bloquear textField de la llave primaria
            textFields.get(0).setEnabled(false);

            int i = 0;
            while (i < nombresColumnas.size() || i < datosIniciales.size()) {
                // Intercalar entre jLabel y textFields

                if (!nombresColumnas.get(i).isEmpty()) {
                    elementosInterfaz.add(new JLabel(nombresColumnas.get(i)));
                }

                if (!datosIniciales.get(i).isEmpty()) {
                    elementosInterfaz.add(textFields.get(i));
                }
                i++;
            }

            Object[] interfazObjeto = elementosInterfaz.toArray();

            int confirmacion = JOptionPane.showConfirmDialog(null, interfazObjeto, "Hola", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);

            if (confirmacion == JOptionPane.OK_OPTION) {
                return textFields;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void actualizarProducto(int cantidadNueva, String folioProducto, boolean aumento) {
        String sentenciaQuery   = "SELECT existencia FROM producto where folio_producto = '" + folioProducto + "'";
        int    cantidadAnterior = 0;
        int    cantidadTotal    = 0;

        try {
            Statement statement = db_connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sentenciaQuery);

            while (resultSet.next()) {
                cantidadAnterior = resultSet.getInt("existencia");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (aumento) {
            cantidadTotal = cantidadAnterior + cantidadNueva;
        } else {
            cantidadTotal = cantidadAnterior - cantidadNueva;
        }

        String sentenciaSQL = "UPDATE producto SET existencia = ? WHERE producto.folio_producto = ?";

        try {
            PreparedStatement preparedStatement = db_connection.prepareStatement(sentenciaSQL);
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

    // Consultas

    public void realizarConsulta(String SQLQuery) {
        JTable      tablaConsultas = new JTable();
        JScrollPane jScrollPane    = new JScrollPane(tablaConsultas);

        tablaConsultas.setModel(fillTable(SQLQuery));

        Object[] elementosinterfaz = {
                new JLabel("Resultado consulta"),
                jScrollPane
        };

        JOptionPane.showMessageDialog(null, elementosinterfaz);
    }

    public DefaultTableModel fillTable(String sqlQuery) {
        Vector<Vector<Object>> data        = new Vector<>();
        int                    columns;
        Vector<Object>         columnNames = new Vector<>();

        try {
            Statement statement = db_connection.createStatement();
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


    public Connection() {
        connect();
    }

}
