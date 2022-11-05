package com.misael.farmacia;

import com.formdev.flatlaf.FlatLightLaf;

public class Launcher {

    public static void main(String[] args) {
        FlatLightLaf.setup();
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
        //Connection db_connection = new Connection();

        //db_connection.crearInterfaz("SELECT * FROM empleado WHERE id_empleado=?", "AMAH01");
        //db_connection.actualizarProducto("47170");
        //db_connection.actualizarEmpleado("AMAH01");
        //db_connection.actualizarProveedor("JMCG04");
        //db_connection.disconnect();
    }

}
