package com.example.dsaproyectofront;

public class Mapa {

    private String id;
    private String mapa;

    public Mapa(){

        this.id = null;
        this.mapa = null;
    }

    public Mapa (String id, String mapa){
        this.id = id;
        this.mapa = mapa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }
}