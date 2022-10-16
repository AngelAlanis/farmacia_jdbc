package com.misael.farmacia;

import java.sql.Timestamp;
import java.util.Date;

public class Launcher {

    public static void main(String[] args) {
        Connection db_connection = new Connection();

        //db_connection.insertarDetalleAbastecimiento(new DetalleAbastecimiento("4", "94235", "4", 20));
        //db_connection.insertarDetalleAbastecimiento(new DetalleAbastecimiento("4", "54662", "4", 15));

        //Timestamp fecha = new Timestamp(new Date().getTime());
        //db_connection.insertarAbastecimiento(new Abastecimiento(fecha, "ABGO01", "4", 400, 400, 0));

        //java.sql.Date fechaNacimiento = java.sql.Date.valueOf("1998-10-14");
        //db_connection.insertarEmpleado(new Empleado("MLHT98", "María de la Luz Huerta del Toro", "2", fechaNacimiento, "Calle Escondida #139", "6183335385", "mariahuerta@gmail.com"));

        //db_connection.insertarProducto(new Producto("8224","Dimegran Jarabe 70ml", "POHE75", 300, 10));
        //db_connection.insertarProducto(new Producto("47170","Teraflu Limón 6 unidades", "POHE75", 113, 20));

        //db_connection.insertarProveedor(new Proveedor("JMCG04", "José Miguel Cabrales Guerrero", "Calle Pepino #584", "6185840102", "josemiguel@gmail.com", "JMCG04HDLR75"));

        //db_connection.actualizarProducto(10, "47170");

        db_connection.insertarDetalleAbastecimiento(new DetalleAbastecimiento("5", "47170", "5", 10));
        Timestamp fecha = new Timestamp(new Date().getTime());
        db_connection.insertarAbastecimiento(new Abastecimiento(fecha, "ABGO01", "5", 600, 600, 0));

        db_connection.disconnect();
    }

}
