package com.example.dsaproyectofront;

public class UsuarioTO {

    String idUser;
    String nombre;
    int dinero;
    int puntuacionTotal;

    public UsuarioTO() {
    }


    public UsuarioTO(String id, String nombre, int dinero, int puntuacion) {
        this.idUser = id;
        this.nombre = nombre;
        this.dinero = dinero;
        this.puntuacionTotal = puntuacion;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String id) {
        this.idUser = id;
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

    public int getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public void setPuntuacionTotal(int puntuacion) {
        this.puntuacionTotal = puntuacion;
    }
}
