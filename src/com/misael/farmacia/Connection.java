package com.misael.farmacia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

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


            if (!resultSet.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "Registro no encontrado");
                return;
            }

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
                    new JLabel("ID Empleado"), tfIdEmpleado,
                    new JLabel("Nombre"), tfNombre,
                    new JLabel("Género"), cbGenero,
                    new JLabel("Fecha de nacimiento"), tfFechaNacimiento,
                    new JLabel("Domicilio"), tfDomicilio,
                    new JLabel("Teléfono"), tfTelefono,
                    new JLabel("Correo electrónico"), tfCorreo
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
        String                SQLUpdate       = "UPDATE producto SET descripcion=?, id_proveedor=?, precio=?, existencia=? WHERE folio_producto=?";

        ArrayList<JTextField> listaTextFields = crearInterfaz(SQLQuery, folioProducto);
        actualizarTabla(SQLUpdate, listaTextFields);
    }

    public void actualizarTabla(String SQLUpdate, ArrayList<JTextField> textFields) {
        if (!textFields.isEmpty()) {
            int numeroParametros = textFields.size();
            try {
                PreparedStatement psUpdate = connection.prepareStatement(SQLUpdate);

                for (int i = 1; i < textFields.size(); i++) {
                    psUpdate.setString(i, String.valueOf(textFields.get(i).getText()));
                }

                psUpdate.setString(numeroParametros, textFields.get(0).getText());

                psUpdate.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, "Actualización exitosa.");
        }

    }

    public ArrayList<JTextField> crearInterfaz(String SQLQuery, String llave) {
        ArrayList<String>     datos             = new ArrayList<>();
        ArrayList<String>     nombresParametros = new ArrayList<>();
        ArrayList<Object>     elementosInterfaz = new ArrayList<>();
        ArrayList<JTextField> textFields        = new ArrayList<>();
        Object[]              interfazObjeto    = new Object[0];

        try {
            PreparedStatement psQuery = connection.prepareStatement(SQLQuery);
            psQuery.setString(1, llave);
            ResultSet resultSet = psQuery.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "Registro no encontrado");
                return null;
            }

            int columnas = resultSet.getMetaData().getColumnCount();

            if (resultSet.next()) {
                for (int i = 1; i <= columnas; i++) {
                    nombresParametros.add(resultSet.getMetaData().getColumnName(i));
                    datos.add(resultSet.getString(i));
                    textFields.add(new JTextField(datos.get(i - 1)));
                }
            }

            // Bloquear textField de la llave primaria
            textFields.get(0).setEnabled(false);

            int i = 0;
            while (i < nombresParametros.size() || i < datos.size()) {
                // Intercalar entre jLabel y textFields

                if (!nombresParametros.get(i).isEmpty()) {
                    elementosInterfaz.add(new JLabel(nombresParametros.get(i)));
                }

                if (!datos.get(i).isEmpty()) {
                    elementosInterfaz.add(textFields.get(i));
                }
                i++;
            }

            interfazObjeto = elementosInterfaz.toArray();

            int confirmacion = JOptionPane.showConfirmDialog(null, interfazObjeto, "Hola", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);

            if (confirmacion == JOptionPane.OK_OPTION) {
                return textFields;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void actualizarProveedor(String proveedorClave) {
        String    SQLQuery  = "SELECT * FROM proveedor WHERE proveedor_clave=?";
        Proveedor proveedor = new Proveedor();

        try {
            PreparedStatement psQuery = connection.prepareStatement(SQLQuery);
            psQuery.setString(1, proveedorClave);
            ResultSet resultSet = psQuery.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "Registro no encontrado");
                return;
            }

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
                    new JLabel("Clave de proveedor"),
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

    public DefaultTableModel fillTable(String sqlQuery) {
        Vector<Vector<Object>> data        = new Vector<>();
        int                    columns;
        Vector<Object>         columnNames = new Vector<>();

        try {
            Statement statement = connection.createStatement();
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
