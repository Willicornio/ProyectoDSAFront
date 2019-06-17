package com.example.dsaproyectofront;
import java.util.List;


public class Inventario {

    private String idUser;
    private String idObjeto;
    private String activado;

    public Inventario() {


    }

    public Inventario(String idUser,String idObjeto) {

        this.idUser = idUser;
        this.idObjeto = idObjeto;
        this.activado = "false";

    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(String idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getActivado() {
        return activado;
    }

    public void setActivado(String activado) {
        this.activado = activado;
    }
}
