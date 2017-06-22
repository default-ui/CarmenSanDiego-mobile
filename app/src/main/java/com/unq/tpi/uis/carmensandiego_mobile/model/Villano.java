package com.unq.tpi.uis.carmensandiego_mobile.model;

/**
 * Created by lalopsb on 21/06/2017.
 */

public class Villano {
    private int id;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Villano(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

}
