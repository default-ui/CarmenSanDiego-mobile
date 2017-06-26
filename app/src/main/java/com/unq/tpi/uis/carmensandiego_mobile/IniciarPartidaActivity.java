package com.unq.tpi.uis.carmensandiego_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.unq.tpi.uis.carmensandiego_mobile.model.EstadoJuego;
import com.unq.tpi.uis.carmensandiego_mobile.services.VillanosService;
import android.util.Log;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class IniciarPartidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_partida);

    }

    public void iniciarPartida(View view){
        crearNuevaPartida();
        Intent detailIntent = new Intent(this, ResolverMisterioActivity.class);
        startActivity(detailIntent);
    }


    private VillanosService createVillanosService() {
        //MMM código repetido, habría que modificar esto no?
        String SERVER_IP = "192.168.1.108"; //esta ip se usa para comunicarse con mi localhost en el emulador de Android Studio
        String SERVER_IP_GENY = "192.168.1.101";//esta ip se usa para comunicarse con mi localhost en el emulador de Genymotion
        String API_URL = "http://"+ SERVER_IP +":3000";

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        VillanosService villanosService = restAdapter.create(VillanosService.class);
        return villanosService;
    }

    private void crearNuevaPartida(){
        VillanosService villanosService = createVillanosService();
        villanosService.iniciarJuego(new Callback<EstadoJuego>() {
            @Override
            public void success(EstadoJuego partida, Response response) {

                //agregarVillanos(villanos);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", error.getMessage());
                error.printStackTrace();
            }
        });
    }
    }


