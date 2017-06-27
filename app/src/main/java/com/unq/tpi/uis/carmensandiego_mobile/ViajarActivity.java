package com.unq.tpi.uis.carmensandiego_mobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.unq.tpi.uis.carmensandiego_mobile.connection.CarmenSanConnection;
import com.unq.tpi.uis.carmensandiego_mobile.model.EstadoJuego;
import com.unq.tpi.uis.carmensandiego_mobile.model.MiniPais;
import com.unq.tpi.uis.carmensandiego_mobile.model.MiniPaisConConexiones;
import com.unq.tpi.uis.carmensandiego_mobile.services.CarmenSanDiegoService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ViajarActivity extends AppCompatActivity{


    private EstadoJuego estadoActual;
    private ConexionListAdapter adapter;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        Intent intent = getIntent();
        EstadoJuego estadoAct = (EstadoJuego) intent.getSerializableExtra("EstadoJuego");
        System.out.println(estadoActual);
        setEstadoActual(estadoAct);
        setContentView(R.layout.activity_viajar);
        setDataJuego();
        //traerConexionesDePais();

        ///
    }

    public void setDataJuego(){
        ((TextView) findViewById(R.id.paisActual)).setText(String.valueOf(estadoActual.getPais().getNombre()));
        TextView txtViewOrden = (TextView) findViewById(R.id.textViewOrden);
        txtViewOrden.setText(estadoActual.getOrdenEmitidaPara());

    }

    public void ordenDeArresto(View view){
        Intent detailIntent = new Intent(this, OrdenDeArrestoActivity.class);
        detailIntent.putExtra("EstadoJuego", this.getEstadoActual());
        startActivity(detailIntent);
    }

    public void pedirPistas(View view) {
        Intent detailIntent = new Intent(this, PedirPistaActivity.class);
        detailIntent.putExtra("EstadoJuego", this.getEstadoActual());
        startActivity(detailIntent);
    }

    public EstadoJuego getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(EstadoJuego estadoActual) {
        this.estadoActual = estadoActual;
    }

    private void traerConexionesDePais(){
        CarmenSanDiegoService carmenSanDiegoService = new CarmenSanConnection().getService();
        carmenSanDiegoService.getPais(this.getEstadoActual().getPais().getId(), new Callback<MiniPaisConConexiones>() {
            @Override
            public void success(MiniPaisConConexiones paisConConexiones, Response response) {
                mostrarConexiones(paisConConexiones);
            }
            @Override
            public void failure(RetrofitError error) {
                Log.e("", error.getMessage());
                error.printStackTrace();
            }
        });
    }

    public void mostrarConexiones(MiniPaisConConexiones paisConConexiones){
        list = (ListView) findViewById(R.id.list);
        List<MiniPais> conexiones = paisConConexiones.getConexiones();
        List<Button> botonesConexiones = new ArrayList<Button>();

        for (int i=0; i<conexiones.size() ; i++){

            botonesConexiones.add((Button)findViewById(R.id.conexionButton));
            botonesConexiones.get(i).setText(conexiones.get(i).getNombre());
            System.out.println(conexiones.get(i).getNombre());
        }
        adapter = new ConexionListAdapter(this, botonesConexiones);
        list.setAdapter((ListAdapter) adapter);

    }

}
