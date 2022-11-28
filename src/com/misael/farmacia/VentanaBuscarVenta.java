package com.misael.farmacia;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class VentanaBuscarVenta extends JFrame {
    private Connection  db_connection;
    private JButton     btnAgregar;
    private JButton     btnCancelar;
    private JLabel      labeNombreProducto;
    private JLabel      labelCantidad;
    private JPanel      panelBotones;
    private JPanel      panelPrincipal;
    private JScrollPane spProductos;
    private JTable      tableProductos;
    private JTextField  tfCantidad;
    private JTextField  tfNombreProducto;

    ArrayList<VentaTienda> listaProductos;
    VentanaPrincipal       ventanaPrincipal;
    Utilidades             utilidades;
    private final Vector<String> columnas = new Vector<>(Arrays.asList("Folio", "Descripción", "Precio"));

    public void initComponents() {
        String sqlQuery = """
                SELECT folio_producto, descripcion, precio
                FROM producto
                ORDER BY folio_producto;
                """;

        Vector<Vector<Object>> data = db_connection.obtenerDatosTabla(sqlQuery);
        tableProductos.setModel(new DefaultTableModel(data, columnas));
    }

    public void initActionListeners() {

        tfNombreProducto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String busqueda = tfNombreProducto.getText();
                utilidades.filtrarLista(busqueda, tableProductos);

            }
        });

        btnAgregar.addActionListener(e -> {
            int    selectedRow   = tableProductos.getSelectedRow();
            String folioProducto = String.valueOf(tableProductos.getValueAt(selectedRow, 0));
            String descripcion   = String.valueOf(tableProductos.getValueAt(selectedRow, 1));
            double precio        = Double.parseDouble(String.valueOf(tableProductos.getValueAt(selectedRow, 2)));
            int    cantidad      = Integer.parseInt(tfCantidad.getText());

            ventanaPrincipal.agregarProductoTablaVenta(new VentaTienda(folioProducto, descripcion, precio, cantidad));

            tfCantidad.setText("");
            this.dispose();
        });

        btnCancelar.addActionListener(e -> this.dispose());
    }

    public VentanaBuscarVenta(VentanaPrincipal ventanaPrincipal, Connection db_connection) {
        this.db_connection    = db_connection;
        this.ventanaPrincipal = ventanaPrincipal;
        listaProductos        = new ArrayList<>();
        utilidades            = new Utilidades();

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panelPrincipal);
        initComponents();
        initActionListeners();
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
