package com.misael.farmacia;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Image;
import java.util.Objects;

public class Utilidades {

    public ImageIcon imageIcon(String URL) {
        return new ImageIcon(Objects.requireNonNull(getClass().getResource(URL)));
    }

    public Image image(String URL) {
        return new ImageIcon(Objects.requireNonNull(getClass().getResource(URL))).getImage();
    }

    public String verificarTexto(String texto) {
        if (texto.trim().isEmpty() || texto.trim().equals(null)) {
            return null;
        }

        return texto;
    }

    public void filtrarLista(String busqueda, JTable table) {
        DefaultTableModel                 tableModel = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> trs        = new TableRowSorter<>(tableModel);
        table.setRowSorter(trs);

        trs.setRowFilter(RowFilter.regexFilter("(?i)" + busqueda));
    }

}
