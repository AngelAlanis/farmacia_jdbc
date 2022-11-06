package com.misael.farmacia;

import javax.swing.ImageIcon;
import java.util.Objects;

public class Utilidades {

    public ImageIcon imageIcon(String URL) {
        return new ImageIcon(Objects.requireNonNull(getClass().getResource(URL)));
    }

    public String verificarTexto(String texto) {
        if (texto.trim().isEmpty() || texto.trim().equals(null)) {
            return null;
        }

        return texto;
    }

}
