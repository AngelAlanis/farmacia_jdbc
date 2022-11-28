package com.misael.farmacia;

public class VentaTienda {

    private String folioProducto;
    private String descripcion;
    private double precio;
    private int    cantidad;
    private double importe;
    private int    existencia;

    public String[] getValores() {
        String[] valores = new String[6];
        valores[0] = folioProducto;
        valores[1] = descripcion;
        valores[2] = String.valueOf(precio);
        valores[3] = String.valueOf(cantidad);
        valores[4] = String.valueOf(importe);
        valores[5] = String.valueOf(existencia);

        return valores;
    }

    public String getFolioProducto() {
        return folioProducto;
    }

    public void setFolioProducto(String folioProducto) {
        this.folioProducto = folioProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public VentaTienda() {
    }

    public VentaTienda(String descripcion, double precio, int cantidad) {
        this.descripcion = descripcion;
        this.precio      = precio;
        this.cantidad    = cantidad;
        this.importe     = precio * cantidad;
    }

    public VentaTienda(String folioProducto, String descripcion, double precio, int cantidad) {
        this.folioProducto = folioProducto;
        this.descripcion   = descripcion;
        this.precio        = precio;
        this.cantidad      = cantidad;
        this.importe       = precio * cantidad;
    }
}
