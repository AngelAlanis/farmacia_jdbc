package com.misael.farmacia;

import java.sql.*;

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
