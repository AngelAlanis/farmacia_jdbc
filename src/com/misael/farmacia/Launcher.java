package com.misael.farmacia;

import java.sql.Timestamp;
import java.util.Date;

public class Launcher {

    public static void main(String[] args) {
        Connection db_connection = new Connection();


        db_connection.actualizarProducto("47170");
        db_connection.disconnect();
    }

}
