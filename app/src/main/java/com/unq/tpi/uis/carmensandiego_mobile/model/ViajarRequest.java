package com.unq.tpi.uis.carmensandiego_mobile.model;

/**
 * Created by lalopsb on 28/06/2017.
 */

public class ViajarRequest {

    private int destinoId;
    private int casoId;

    public ViajarRequest(){
        this.destinoId = 0;
        this.casoId = 0;
    }

    public ViajarRequest(int destinoID, int juegoID){
        this.destinoId = destinoID;
        this.casoId = juegoID;
    }

    public int getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(int destinoID) {
        this.destinoId = destinoID;
    }

    public int getCasoId() {
        return casoId;
    }

    public void setCasoId(int juegoID) {
        this.casoId = juegoID;
    }
}
