package com.unq.tpi.uis.carmensandiego_mobile.services;

import com.unq.tpi.uis.carmensandiego_mobile.model.EstadoJuego;
import com.unq.tpi.uis.carmensandiego_mobile.model.MiniPaisConConexiones;
import com.unq.tpi.uis.carmensandiego_mobile.model.Lugar;
import com.unq.tpi.uis.carmensandiego_mobile.model.Villano;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

//TODO cambiar a  CarmenSanDiegoService

public interface CarmenSanDiegoService {
    @GET("/villanos")
    void getVillanos(Callback<List<Villano>> callback);

    @POST("/iniciarJuego")
    void iniciarJuego(Callback<EstadoJuego> callback);

    @GET("/pistaDelLugar/{id}")
    void getPista(@Path("id") int idCaso, @Query("lugar") String lugar, Callback<Lugar> callback);

    @GET("/pais/{id}")
    void getPais(@Path("id") int id, Callback<MiniPaisConConexiones> callback);

    @GET("/villano/{id}")
    void getVillano(@Path("id") int id, Callback<Villano> callback);
}
