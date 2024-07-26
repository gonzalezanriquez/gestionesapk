package com.example.davicio.entidades;

import java.io.Serializable;

public class Productos implements Serializable {

    private int id;
    private String nombre;
    private String descripcion;
    private String precio;
    private int usuario;

    public Productos(int id, String nombre, String descripcion, String precio, int usuario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.usuario = usuario;
    }

    public Productos(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return "$ "+precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }




}
