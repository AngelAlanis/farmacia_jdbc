package com.misael.farmacia;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.JOptionPane;

public class Launcher {

    public static void main(String[] args) {

        try {
            FlatDarkLaf.setup();
            VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion(null);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

}
