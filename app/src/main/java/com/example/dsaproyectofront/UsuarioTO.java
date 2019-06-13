package com.example.dsaproyectofront;

public class UsuarioTO {

    String idUser;
    String nombre;
    int dinero;
    int puntuacion;

    public UsuarioTO() {
    }


    public UsuarioTO(String id, String nombre, int dinero, int puntuacion) {
        this.idUser = id;
        this.nombre = nombre;
        this.dinero = dinero;
        this.puntuacion = puntuacion;
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

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
