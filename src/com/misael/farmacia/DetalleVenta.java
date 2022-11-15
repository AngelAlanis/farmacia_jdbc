package com.misael.farmacia;

public class DetalleVenta {

    private String idDetalles;
    private String folioProducto;
    private int    cantidad;

    public String getIdDetalles() {
        return idDetalles;
    }

    public void setIdDetalles(String idDetalles) {
        this.idDetalles = idDetalles;
    }

    public String getFolioProducto() {
        return folioProducto;
    }

    public void setFolioProducto(String folioProducto) {
        this.folioProducto = folioProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public DetalleVenta() {
    }

    public DetalleVenta(String folioProducto, int cantidad) {
        this.folioProducto = folioProducto;
        this.cantidad      = cantidad;
    }

    public DetalleVenta(String idDetalles, String folioProducto, int cantidad) {
        this.idDetalles    = idDetalles;
        this.folioProducto = folioProducto;
        this.cantidad      = cantidad;
    }
}
