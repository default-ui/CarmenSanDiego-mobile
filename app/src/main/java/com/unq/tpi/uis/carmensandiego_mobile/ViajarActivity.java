package com.unq.tpi.uis.carmensandiego_mobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.unq.tpi.uis.carmensandiego_mobile.connection.CarmenSanConnection;
import com.unq.tpi.uis.carmensandiego_mobile.model.EstadoJuego;
import com.unq.tpi.uis.carmensandiego_mobile.model.MiniPais;
import com.unq.tpi.uis.carmensandiego_mobile.model.MiniPaisConConexiones;
import com.unq.tpi.uis.carmensandiego_mobile.model.ViajarRequest;
import com.unq.tpi.uis.carmensandiego_mobile.services.CarmenSanDiegoService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ViajarActivity extends AppCompatActivity{


    private EstadoJuego estadoJuego;
    private ListView list;
    private List<MiniPais> conexiones;
    private Integer casoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        Intent intent = getIntent();
        int id = (int) intent.getSerializableExtra("CasoID");
        this.casoId = new Integer(id);
        EstadoJuego estadoAct = (EstadoJuego) intent.getSerializableExtra("EstadoJuego");
        setEstadoJuego(estadoAct);
        setContentView(R.layout.activity_viajar);
        setDataJuego();
        traerConexionesDePais();

        //traerConexionesDePais();

        ///
    }



    public void setDataJuego(){
        ((TextView) findViewById(R.id.paisActual)).setText(String.valueOf(estadoJuego.getPais().getNombre()));
        TextView txtViewOrden = (TextView) findViewById(R.id.textViewOrden);
        txtViewOrden.setText("Iupi!");
        ((TextView) findViewById(R.id.destinosRecorridos)).setText(obtenerRecorrido(estadoJuego.getRecorrido()));

    }

    private String obtenerRecorrido(List<MiniPaisConConexiones> recorrido) {
        String rec = "";
        for (MiniPaisConConexiones p : recorrido){
            rec = rec+">> "+p.getNombre()+" ";
        }
        return rec;
    }

    public void ordenDeArresto(View view){
        Intent detailIntent = new Intent(this, OrdenDeArrestoActivity.class);
        detailIntent.putExtra("CasoID", this.getEstadoJuego().getId());
        detailIntent.putExtra("EstadoJuego", this.getEstadoJuego());
        startActivity(detailIntent);
    }

    public void pedirPistas(View view) {
        Intent detailIntent = new Intent(this, PedirPistaActivity.class);
        detailIntent.putExtra("EstadoJuego", this.getEstadoJuego());
        startActivity(detailIntent);
    }

    public EstadoJuego getEstadoJuego() {
        return estadoJuego;
    }

    public void setEstadoJuego(EstadoJuego estadoJuego) {
        this.estadoJuego = estadoJuego;
    }

    private void traerConexionesDePais(){
        CarmenSanDiegoService carmenSanDiegoService = new CarmenSanConnection().getService();
        carmenSanDiegoService.getPais(this.getEstadoJuego().getPais().getId(), new Callback<MiniPaisConConexiones>() {
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
        List<String> nombresConexiones = new ArrayList<>();
        conexiones = paisConConexiones.getConexiones();
        for (MiniPais p : conexiones){
            nombresConexiones.add(p.getNombre());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.list_row, nombresConexiones);
        ListView listView = (ListView) findViewById(R.id.mainListView);
        listView.setAdapter(dataAdapter);
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                viajar(((TextView) view).getText().toString());
            }
        });

    }

    private void viajar(String text) {
        int destinoId = 0;
        for (MiniPais p : conexiones){
            if (p.getNombre()==text){
                destinoId = p.getId();
            }
        }
        CarmenSanDiegoService carmenSanDiegoService = new CarmenSanConnection().getService();
        ViajarRequest vi = new ViajarRequest(destinoId, casoId);
        carmenSanDiegoService.viajar(vi, new Callback<EstadoJuego>() {
            @Override
            public void success(EstadoJuego partida, Response response) {
                System.out.println(partida.getPais().getNombre());
                setEstadoJuego(partida);
                setContentView(R.layout.activity_viajar);
                setDataJuego();
                traerConexionesDePais();
            }

            @Override
            public void failure(RetrofitError error) {
                error.getLocalizedMessage();
            }
        });
    }


    public void volverAPantallaPrincipal(View view) {
        Intent detailIntent = new Intent(this, ViajarActivity.class);
        detailIntent.putExtra("EstadoJuego", this.getEstadoJuego());
        startActivity(detailIntent);
    }





}
