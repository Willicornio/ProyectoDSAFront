package com.example.dsaproyectofront;

public class Auth {

    String nombre;
    String pass;

    public Auth(String nombre, String pass) {
        this.nombre = nombre;
        this.pass = pass;
    }

    public Auth() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    @Override
    public String toString() {
        return "Ath{" +
                "nombre=" + nombre +
                ", pass=" + pass +
                '}';
    }
}
