package com.example.davicio.entidades;
import java.io.Serializable;

public class Sucursales  implements Serializable {

    private int idsucursales;
    private String nombre;
    private String direccion;
    private String telefono;




    public Sucursales(int idsucursales, String nombre, String direccion, String telefono){
        this.idsucursales=idsucursales;
        this.nombre=nombre;
        this.direccion=direccion;
        this.telefono=telefono;
    }
    public Sucursales(){}

    public int getIdsucursales() {
        return idsucursales;
    }

    public void setIdsucursales(int idsucursales) {
        this.idsucursales = idsucursales;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
