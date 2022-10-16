package com.misael.farmacia;

public class DetalleAbastecimiento {

    private String folioDetalleAbastecimiento;
    private String idListaProductos;
    private String folioProducto;
    private String folioAbastecimiento;
    private int cantidad;

    public String getFolioDetalleAbastecimiento() {
        return folioDetalleAbastecimiento;
    }

    public void setFolioDetalleAbastecimiento(String folioDetalleAbastecimiento) {
        this.folioDetalleAbastecimiento = folioDetalleAbastecimiento;
    }

    public String getIdListaProductos() {
        return idListaProductos;
    }

    public void setIdListaProductos(String idListaProductos) {
        this.idListaProductos = idListaProductos;
    }

    public String getFolioProducto() {
        return folioProducto;
    }

    public void setFolioProducto(String folioProducto) {
        this.folioProducto = folioProducto;
    }

    public String getFolioAbastecimiento() {
        return folioAbastecimiento;
    }

    public void setFolioAbastecimiento(String folioAbastecimiento) {
        this.folioAbastecimiento = folioAbastecimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public DetalleAbastecimiento(){

    }

    public DetalleAbastecimiento(String idListaProductos, String folioProducto, String folioAbastecimiento, int cantidad) {
        this.idListaProductos           = idListaProductos;
        this.folioProducto              = folioProducto;
        this.folioAbastecimiento        = folioAbastecimiento;
        this.cantidad                   = cantidad;
    }
}
