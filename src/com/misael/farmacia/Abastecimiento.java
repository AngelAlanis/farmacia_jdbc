package com.misael.farmacia;

import java.sql.Timestamp;

public class Abastecimiento {

    private int clave;
    private Timestamp fecha;
    private String claveProveedor;
    private String idListaProductos;
    private double totalAPagar;
    private double importePagado;
    private double pagoRestante;

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getClaveProveedor() {
        return claveProveedor;
    }

    public void setClaveProveedor(String claveProveedor) {
        this.claveProveedor = claveProveedor;
    }

    public String getIdListaProductos() {
        return idListaProductos;
    }

    public void setIdListaProductos(String idListaProductos) {
        this.idListaProductos = idListaProductos;
    }

    public double getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(double totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public double getImportePagado() {
        return importePagado;
    }

    public void setImportePagado(double importePagado) {
        this.importePagado = importePagado;
    }

    public double getPagoRestante() {
        return pagoRestante;
    }

    public void setPagoRestante(double pagoRestante) {
        this.pagoRestante = pagoRestante;
    }

    public Abastecimiento() {
    }

    public Abastecimiento(Timestamp fecha, String claveProveedor, String idListaProductos, double totalAPagar, double importePagado, double pagoRestante) {
        this.fecha            = fecha;
        this.claveProveedor   = claveProveedor;
        this.idListaProductos = idListaProductos;
        this.totalAPagar      = totalAPagar;
        this.importePagado    = importePagado;
        this.pagoRestante     = pagoRestante;
    }
}
