package com.misael.farmacia;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import java.sql.Timestamp;

public class Launcher {

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();

    }

}
