package com.unq.tpi.uis.carmensandiego_mobile.model;

import java.io.Serializable;

/**
 * Created by lalopsb on 27/06/2017.
 */

public class EmitirOrdenRequest implements Serializable {
    public int getVillanoId() {
        return villanoId;
    }

    public void setVillanoId(int villanoId) {
        this.villanoId = villanoId;
    }

    public int getCasoId() {
        return casoId;
    }

    public void setCasoId(int casoId) {
        this.casoId = casoId;
    }

    private int villanoId;
    private Integer casoId;

    public EmitirOrdenRequest() {
        this.villanoId = 0;
        this.casoId = 0;
    }

    public EmitirOrdenRequest(int villanoid, Integer casoid) {
        this.villanoId = villanoid;
        this.casoId = casoid;
    }
}
