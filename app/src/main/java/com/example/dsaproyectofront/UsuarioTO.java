package com.example.dsaproyectofront;

import java.util.LinkedList;

public class UsuarioTO {


    String id;
    String nombre;
    int dinero;
    int puntuacion;

    public UsuarioTO() {
    }

    public UsuarioTO(String nombre, String pass) {

        this.nombre = nombre;
        this.id = "id" + nombre;
        this.dinero = 0;
        this.puntuacion = 0;
    }

    /*public Usuario(String idUser, String nombre, String pass, int dinero, int puntuacionTotal) {
        this.idUser = idUser;
        this.nombre = nombre;
        this.pass = pass;
        this.dinero = dinero;
        this.puntuacionTotal = puntuacionTotal;
        }
    */

    public String getId() {
        return id;
    }

    public void setId(String idUser) {
        this.id = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }


    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacionTotal) {
        this.puntuacion = puntuacionTotal;
    }

}