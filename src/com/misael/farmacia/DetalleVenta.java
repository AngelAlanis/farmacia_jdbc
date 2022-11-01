package com.misael.farmacia;

public class DetalleVenta {

    private String folioDetalleVenta;
    private String idDetalles;
    private String folioProducto;
    private int    cantidad;

    public String getFolioDetalleVenta() {
        return folioDetalleVenta;
    }

    public void setFolioDetalleVenta(String folioDetalleVenta) {
        this.folioDetalleVenta = folioDetalleVenta;
    }

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

    public DetalleVenta(String folioDetalleVenta, String idDetalles, String folioProducto, int cantidad) {
        this.folioDetalleVenta = folioDetalleVenta;
        this.idDetalles        = idDetalles;
        this.folioProducto     = folioProducto;
        this.cantidad          = cantidad;
    }
}
