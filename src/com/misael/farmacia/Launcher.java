package com.misael.farmacia;

public class Launcher {

    public static void main(String[] args) {
        Connection db_connection = new Connection();

        db_connection.crearInterfaz("SELECT * FROM empleado WHERE id_empleado=?", "AMAH01");
        db_connection.disconnect();
    }

}
