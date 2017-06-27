package com.unq.tpi.uis.carmensandiego_mobile.model;


import java.io.Serializable;
import java.util.List;

public class EstadoJuego implements Serializable {

    private int id;
    private MiniPais pais;

    private List<MiniPais> recorrido;
  //  private List<MiniPais> paisesFallidos;

    public EstadoJuego(int id, MiniPais pais ){
        this.id = id;
        this.pais = pais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public MiniPais getPais() {
        return pais;
    }

    public void setPais(MiniPais pais) {
        this.pais = pais;
    }

    public List<MiniPais> getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(List<MiniPais> recorrido) {
        this.recorrido = recorrido;
    }

}
