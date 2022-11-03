package com.misael.farmacia;

import java.sql.SQLException;

public class Launcher {

    public static void main(String[] args) {
        Connection db_connection = new Connection();

        db_connection.realizarConsulta("SELECT * FROM proveedor WHERE proveedor_clave = 'ABGO01'");

        db_connection.disconnect();
    }

}
