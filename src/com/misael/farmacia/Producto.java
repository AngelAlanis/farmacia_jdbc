package com.misael.farmacia;

public class Producto {

    private String folioProducto;
    private String descripcion;
    private String idProveedor;
    private double precio;
    private int    existencia;

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

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "folioProducto='" + folioProducto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", idProveedor='" + idProveedor + '\'' +
                ", precio=" + precio +
                ", existencia=" + existencia +
                '}';
    }

    public Producto() {
    }

    public Producto(String folioProducto, String descripcion, String idProveedor, double precio, int existencia) {
        this.folioProducto = folioProducto;
        this.descripcion   = descripcion;
        this.idProveedor   = idProveedor;
        this.precio        = precio;
        this.existencia    = existencia;
    }
}
