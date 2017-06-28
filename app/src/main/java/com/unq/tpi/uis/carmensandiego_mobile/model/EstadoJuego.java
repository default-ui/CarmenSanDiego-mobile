package com.unq.tpi.uis.carmensandiego_mobile.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EstadoJuego implements Serializable {

    private int id;
    private MiniPaisConConexiones pais;
    private String ordenEmitidaPara;
    private List<MiniPaisConConexiones> recorrido;
  //  private List<MiniPais> paisesFallidos;

    public EstadoJuego(int id, MiniPaisConConexiones pais ){
        this.id = id;
        this.pais = pais;
        this.ordenEmitidaPara = "No se ha emitido orden aun";

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MiniPaisConConexiones getPais() {
        return pais;
    }

    public void setPais(MiniPaisConConexiones pais) {
        this.pais = pais;
    }

    public List<MiniPaisConConexiones> getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(List<MiniPaisConConexiones> recorrido) {
        this.recorrido = recorrido;
    }

    public String getOrdenEmitidaPara() {
        return ordenEmitidaPara;
    }

    public void setOrdenEmitidaPara(String ordenEmitidaPara) {
        this.ordenEmitidaPara = ordenEmitidaPara;
    }
}
