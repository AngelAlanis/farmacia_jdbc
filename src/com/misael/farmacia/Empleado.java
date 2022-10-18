package com.misael.farmacia;

import java.sql.Date;

public class Empleado {

    private String idEmpleado;
    private String nombre;
    private String genero;
    private Date   fechaNacimiento;
    private String domicilio;
    private String telefono;
    private String correo;

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado='" + idEmpleado + '\'' +
                ", nombre='" + nombre + '\'' +
                ", genero='" + genero + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", domicilio='" + domicilio + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }

    public Empleado() {
    }

    public Empleado(String idEmpleado, String nombre, String genero, Date fechaNacimiento, String domicilio, String telefono, String correo) {
        this.idEmpleado      = idEmpleado;
        this.nombre          = nombre;
        this.genero          = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.domicilio       = domicilio;
        this.telefono        = telefono;
        this.correo          = correo;
    }
}
