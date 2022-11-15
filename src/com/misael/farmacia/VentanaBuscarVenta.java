package com.misael.farmacia;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.util.ArrayList;

public class VentanaBuscarVenta extends JFrame {
    private JPanel      panelPrincipal;
    private JScrollPane spProductos;
    private JTable      tableProductos;
    private JTextField  tfNombreProducto;
    private JLabel      labeNombreProducto;
    private JTextField  tfCantidad;
    private JLabel      labelCantidad;
    private JButton     btnAgregar;
    private JButton     btnCancelar;
    private JPanel      panelBotones;
    private Connection  db_connection;

    ArrayList<DetalleVenta> listaProductos;
    VentanaPrincipal        ventanaPrincipal;

    public void initComponents() {
        String sqlQuery = """
                SELECT producto.folio_producto, producto.descripcion, producto.id_proveedor, proveedor.nombre, producto.precio, producto.existencia
                FROM producto, proveedor
                WHERE producto.id_proveedor = proveedor.proveedor_clave
                ORDER BY producto.folio_producto;
                """;

        tableProductos.setModel(db_connection.fillTable(sqlQuery));
    }

    public void initActionListeners() {
        btnAgregar.addActionListener(e -> {
            int    selectedRow   = tableProductos.getSelectedRow();
            int    cantidad      = Integer.parseInt(tfCantidad.getText());
            String folioProducto = String.valueOf(tableProductos.getValueAt(selectedRow, 0));

            listaProductos.add(new DetalleVenta(folioProducto, cantidad));


            tfCantidad.setText("");
            this.dispose();
        });

        btnCancelar.addActionListener(e -> this.dispose());
    }

    public VentanaBuscarVenta(VentanaPrincipal ventanaPrincipal, Connection db_connection) {
        this.db_connection    = db_connection;
        this.ventanaPrincipal = ventanaPrincipal;
        listaProductos        = new ArrayList<>();

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panelPrincipal);
        initComponents();
        initActionListeners();
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
