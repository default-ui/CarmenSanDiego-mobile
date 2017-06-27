package com.unq.tpi.uis.carmensandiego_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.unq.tpi.uis.carmensandiego_mobile.connection.CarmenSanConnection;
import com.unq.tpi.uis.carmensandiego_mobile.model.EstadoJuego;
import com.unq.tpi.uis.carmensandiego_mobile.services.CarmenSanDiegoService;
import android.util.Log;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class IniciarPartidaActivity extends AppCompatActivity {

    private EstadoJuego estadoJuego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_partida);
        ///////////////////
        //Solucion provisional porque estoy por matar a alguien. Si comento esto la primera vez que hago click
        // estadoJuego no se setea y queda null
        // y no se porque AAAAAAAAAAAAAAHHHHHHHHHHHHHHHHGGGGGGGGGGGG
        crearNuevaPartida();
        ////////////////////
    }

    public void iniciarPartida(View view){
        this.crearNuevaPartida();
        Intent detailIntent = new Intent(this, ViajarActivity.class);
        detailIntent.putExtra("EstadoJuego", this.getEstadoJuego());
        System.out.println(this.getEstadoJuego().getRecorrido().get(0).getNombre());
        startActivity(detailIntent);
    }

    private void crearNuevaPartida(){
        CarmenSanDiegoService carmenSanDiegoService = new CarmenSanConnection().getService();
        carmenSanDiegoService.iniciarJuego(new Callback<EstadoJuego>() {
            @Override
            public void success(EstadoJuego partida, Response response) {
              //  System.out.println(partida);
                guardarPartida(partida);
            }
            @Override
            public void failure(RetrofitError error) {
                Log.e("", error.getMessage());
                error.printStackTrace();
            }
        });
    }

    public void guardarPartida(EstadoJuego partida) {
        this.setEstadoJuego(partida);
        //System.out.println(partida);
    }

    public EstadoJuego getEstadoJuego() {
        return estadoJuego;
    }

    public void setEstadoJuego(EstadoJuego estadoJuego) {
        this.estadoJuego = estadoJuego;
    }

    }


