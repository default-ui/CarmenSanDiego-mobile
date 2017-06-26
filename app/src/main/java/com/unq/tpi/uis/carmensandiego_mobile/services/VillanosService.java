package com.unq.tpi.uis.carmensandiego_mobile.services;

import com.unq.tpi.uis.carmensandiego_mobile.model.EstadoJuego;
import com.unq.tpi.uis.carmensandiego_mobile.model.Villano;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;

//TODO cambiar a  CarmenSanDiegoService

public interface VillanosService {
    @GET("/villanos")
    void getVillanos(Callback<List<Villano>> callback);

    @POST("/iniciarJuego")
    void iniciarJuego(Callback<EstadoJuego> callback);
}
