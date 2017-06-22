package com.unq.tpi.uis.carmensandiego_mobile.services;

import com.unq.tpi.uis.carmensandiego_mobile.model.Villano;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;


public interface VillanosService {
    @GET("/villanos")
    void getVillanos(Callback<List<Villano>> callback);
}
