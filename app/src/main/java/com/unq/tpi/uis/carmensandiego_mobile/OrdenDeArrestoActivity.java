package com.unq.tpi.uis.carmensandiego_mobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.unq.tpi.uis.carmensandiego_mobile.connection.CarmenSanConnection;
import com.unq.tpi.uis.carmensandiego_mobile.model.EmitirOrdenRequest;
import com.unq.tpi.uis.carmensandiego_mobile.model.EstadoJuego;
import com.unq.tpi.uis.carmensandiego_mobile.model.Villano;
import com.unq.tpi.uis.carmensandiego_mobile.services.CarmenSanDiegoService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OrdenDeArrestoActivity extends AppCompatActivity {

    private int idSeleccionado;
    private List<Villano> villanos;
    private Integer idCaso;
    private EstadoJuego estadoJuego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int id = (int) intent.getSerializableExtra("CasoID");
        this.idCaso = new Integer(id);
        this.estadoJuego = (EstadoJuego) intent.getSerializableExtra("EstadoJuego");
        setContentView(R.layout.activity_orden_de_arresto);
        obtenerVillanos();
        //TODO borrar esto. (villanos siempre es null???)
        System.out.println(this.villanos);

    }

    private void obtenerVillanos() {
        CarmenSanDiegoService carmenSanDiegoService = new CarmenSanConnection().getService();
        carmenSanDiegoService.getVillanos(new Callback<List<Villano>>() {
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
            Integer idSeleccionado = getIdByName(villanos, nombre);
            String str = idSeleccionado.toString();
            TextView txtView5 = (TextView) findViewById(R.id.textView5);
            txtView5.setText(nombre);
            CarmenSanDiegoService carmenSanDiegoService = new CarmenSanConnection().getService();
            EmitirOrdenRequest vi = new EmitirOrdenRequest(idSeleccionado, idCaso);
            carmenSanDiegoService.emitirOrdenPara(vi, new Callback<EmitirOrdenRequest>() {
                @Override
                public void success(EmitirOrdenRequest villano, Response response) {
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("", error.getMessage());
                    error.printStackTrace();
                }
            });
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
