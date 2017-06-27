package com.unq.tpi.uis.carmensandiego_mobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.unq.tpi.uis.carmensandiego_mobile.model.Villano;
import com.unq.tpi.uis.carmensandiego_mobile.services.PaisService;
import com.unq.tpi.uis.carmensandiego_mobile.services.VillanosService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ResolverMisterioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolver_misterio);
        cargarConexiones(idPais);
    }

    private PaisService createPaisService() {
        //MMM código repetido, habría que modificar esto no?
        String SERVER_IP = "192.168.0.5"; //esta ip se usa para comunicarse con mi localhost en el emulador de Android Studio
        String SERVER_IP_GENY = "192.168.0.9";//esta ip se usa para comunicarse con mi localhost en el emulador de Genymotion
        String API_URL = "http://"+ SERVER_IP +":3000";

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        PaisService paisService = restAdapter.create(PaisService.class);
        return paisService;
    }

    private void cargarConexiones(int id) {
            PaisService paisService = createPaisService();
            //System.out.println("hola");
            paisService.setID(id);
            paisService.getPais(new Callback<Pais>() {
                @Override
                public void success(Pais pais, Response response) {

                    mostrarConexiones(pais);
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("", error.getMessage());
                    error.printStackTrace();
                }
            });
        }
    }

    public void ordenDeArrestooo(View view){
        Intent detailIntent = new Intent(this, OrdenDeArrestoActivity.class);
        startActivity(detailIntent);
    }



}
