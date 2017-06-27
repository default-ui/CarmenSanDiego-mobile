package com.unq.tpi.uis.carmensandiego_mobile.model;

import java.util.List;

public class MiniPaisConConexiones extends MiniPais {

    List<String> lugares;
    List<MiniPais> conexiones;
    List<String> caracteristicas;

    public MiniPaisConConexiones(int id, String nombre, List<MiniPais> conexiones, List<String> lugares) {
        super(id, nombre);
        this.conexiones = conexiones;
        this.lugares = lugares;
    }

    public List<MiniPais> getConexiones(){
        return this.conexiones;
    }

    public void setConexiones(List<MiniPais> conexiones){
        this.conexiones = conexiones;
    }

    public List<String> getLugares(){
        return this.lugares;
    }

    public void setLugares(List<String> lugares){
        this.lugares = lugares;
    }

    public void setCaracteristicas(List<String> caracteristicas) { this.caracteristicas = caracteristicas; }
    public List<String> getCaracteristicas() { return this.caracteristicas; }
}
