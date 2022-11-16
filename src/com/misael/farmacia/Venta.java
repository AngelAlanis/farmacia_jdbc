package com.misael.farmacia;

import java.sql.Timestamp;

public class Venta {

    private Timestamp fecha;
    private int       idDetalles;
    private String    idEmpleado;
    private Double    totalAPagar;
    private Double    totalPagado;

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public int getIdDetalles() {
        return idDetalles;
    }

    public void setIdDetalles(int idDetalles) {
        this.idDetalles = idDetalles;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Double getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(Double totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public Double getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(Double totalPagado) {
        this.totalPagado = totalPagado;
    }

    public Venta() {
    }

    public Venta(String idEmpleado, Double totalAPagar, Double totalPagado) {
        this.fecha       = new Timestamp(System.currentTimeMillis());
        this.idEmpleado  = idEmpleado;
        this.totalAPagar = totalAPagar;
        this.totalPagado = totalPagado;
    }

    public Venta(Timestamp fecha, int idDetalles, String idEmpleado, Double totalAPagar, Double totalPagado) {
        this.fecha       = fecha;
        this.idDetalles  = idDetalles;
        this.idEmpleado  = idEmpleado;
        this.totalAPagar = totalAPagar;
        this.totalPagado = totalPagado;
    }
}
