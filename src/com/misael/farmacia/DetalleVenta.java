package com.misael.farmacia;

public class DetalleVenta {

    private int idDetalles;
    private String folioProducto;
    private int    cantidad;

    public int getIdDetalles() {
        return idDetalles;
    }

    public void setIdDetalles(int idDetalles) {
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

    @Override
    public String toString() {
        return "DetalleVenta{" +
                "idDetalles=" + idDetalles +
                ", folioProducto='" + folioProducto + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }

    public DetalleVenta() {
    }

    public DetalleVenta(String folioProducto, int cantidad) {
        this.folioProducto = folioProducto;
        this.cantidad      = cantidad;
    }

    public DetalleVenta(int idDetalles, String folioProducto, int cantidad) {
        this.idDetalles    = idDetalles;
        this.folioProducto = folioProducto;
        this.cantidad      = cantidad;
    }
}
