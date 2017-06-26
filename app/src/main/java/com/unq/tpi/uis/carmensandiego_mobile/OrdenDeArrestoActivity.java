package com.unq.tpi.uis.carmensandiego_mobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.unq.tpi.uis.carmensandiego_mobile.model.EstadoJuego;
import com.unq.tpi.uis.carmensandiego_mobile.model.Villano;
import com.unq.tpi.uis.carmensandiego_mobile.services.VillanosService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OrdenDeArrestoActivity extends AppCompatActivity {

    private int idSeleccionado;
    private List<Villano> villanos;

    private EstadoJuego estadoJuego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.estadoJuego = (EstadoJuego) intent.getSerializableExtra("EstadoJuego");
        setContentView(R.layout.activity_orden_de_arresto);
        obtenerVillanos();
        //TODO borrar esto. (villanos siempre es null???)
        System.out.println(this.villanos);

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

    private void obtenerVillanos() {
        VillanosService villanosService = createVillanosService();
        villanosService.getVillanos(new Callback<List<Villano>>() {
            @Override
            public void success(List<Villano> villanos, Response response) {

                agregarVillanos(villanos);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", error.getMessage());
                error.printStackTrace();
            }
        });
    }

    public void agregarVillanos(List<Villano> villanos) {
        this.villanos = villanos;
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        List<String> nombres = getNombres(villanos);
        nombres.add(0,"-Seleccione un Villano-");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, nombres);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private int getIdByName(List<Villano> villanos, String nombre) {
        int id = 0;
        for(Villano v : villanos){
            if (v.getNombre()==nombre){
                id = v.getId();
            }
        }
        return id;
    }

    private List<String> getNombres(List<Villano> villanos) {
        List<String> res = new ArrayList<>();
        for(Villano v : villanos) {
            res.add(v.getNombre());
        }
        return res;
    }

    public void emitirOrdenDeArresto(View view){
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        String nombre = spinner.getSelectedItem().toString();
        if(nombre!="-Seleccione un Villano-") {
            //Do nothing.

            int idSeleccionado = getIdByName(villanos, nombre);
            TextView txtView5 = (TextView) findViewById(R.id.textView5);
            txtView5.setText(nombre);
            System.out.println(idSeleccionado);
        }
    }

    public String obtenerNombreDeOrdenDeArresto(View view){
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        return spinner.getSelectedItem().toString();
    }

    public void volverAPantallaPrincipal(View view){
        Intent detailIntent = new Intent(this, ViajarActivity.class);
        detailIntent.putExtra("EstadoJuego", this.getEstadoJuego());
        startActivity(detailIntent);
    }

    public EstadoJuego getEstadoJuego() {
        return estadoJuego;
    }

    public void setEstadoJuego(EstadoJuego estadoJuego) {
        this.estadoJuego = estadoJuego;
    }


}
