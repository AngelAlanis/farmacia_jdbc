package com.misael.farmacia;

public class Proveedor {

    private String proveedorClave;
    private String nombre;
    private String domicilio;
    private String telefono;
    private String correo;
    private String rfc;

    public String getProveedorClave() {
        return proveedorClave;
    }

    public void setProveedorClave(String proveedorClave) {
        this.proveedorClave = proveedorClave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    @Override
    public String toString() {
        return proveedorClave + " - " + nombre;
    }

    public Proveedor() {
    }

    public Proveedor(String proveedorClave, String nombre) {
        this.proveedorClave = proveedorClave;
        this.nombre         = nombre;
    }

    public Proveedor(String proveedorClave, String nombre, String domicilio, String telefono, String correo, String rfc) {
        this.proveedorClave = proveedorClave;
        this.nombre         = nombre;
        this.domicilio      = domicilio;
        this.telefono       = telefono;
        this.correo         = correo;
        this.rfc            = rfc;
    }
}