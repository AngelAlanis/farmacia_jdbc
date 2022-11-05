package com.misael.farmacia;

public class Venta {

    private String idDetalles;
    private String idEmpleado;
    private Double totalAPagar;
    private Double totalPagado;

    public String getIdDetalles() {
        return idDetalles;
    }

    public void setIdDetalles(String idDetalles) {
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

    public Venta(String idDetalles, String idEmpleado, Double totalAPagar, Double totalPagado) {
        this.idDetalles  = idDetalles;
        this.idEmpleado  = idEmpleado;
        this.totalAPagar = totalAPagar;
        this.totalPagado = totalPagado;
    }
}
