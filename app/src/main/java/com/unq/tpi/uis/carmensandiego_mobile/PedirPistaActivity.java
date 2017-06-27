package com.unq.tpi.uis.carmensandiego_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.unq.tpi.uis.carmensandiego_mobile.connection.CarmenSanConnection;
import com.unq.tpi.uis.carmensandiego_mobile.model.EstadoJuego;
import com.unq.tpi.uis.carmensandiego_mobile.model.MiniPais;
import com.unq.tpi.uis.carmensandiego_mobile.model.MiniPaisConConexiones;
import com.unq.tpi.uis.carmensandiego_mobile.model.Pista;
import com.unq.tpi.uis.carmensandiego_mobile.model.Villano;
import com.unq.tpi.uis.carmensandiego_mobile.services.CarmenSanDiegoService;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PedirPistaActivity extends AppCompatActivity {

    private EstadoJuego estadoJuego;
    public MiniPaisConConexiones paisActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.estadoJuego = (EstadoJuego) intent.getSerializableExtra("EstadoJuego");
        obtenerPais(this.estadoJuego.getPais().getId());
        this.generarBotones();
        setContentView(R.layout.activity_pedir_pista);
    }

    private void generarBotones() {
        List<MiniPais> conexiones = paisActual.getConexiones();
        ((TextView) findViewById(R.id.lugar1)).setText(String.valueOf(conexiones.get(0).getNombre()));
        ((TextView) findViewById(R.id.lugar2)).setText(String.valueOf(conexiones.get(1).getNombre()));
        ((TextView) findViewById(R.id.lugar3)).setText(String.valueOf(conexiones.get(2).getNombre()));
    }

    private void obtenerPista() {
        CarmenSanDiegoService carmenSanDiegoService = new CarmenSanConnection().getService();
        carmenSanDiegoService.getPista(new Callback<Pista>() {

            @Override
            public void success(Pista pista, Response response) {
                mostrarPista(pista);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", error.getMessage());
                error.printStackTrace();
            }
        });
    }

    private void obtenerPais(int idPais) {
        CarmenSanDiegoService carmenSanDiegoService = new CarmenSanConnection().getService();
        carmenSanDiegoService.getPais(idPais, new Callback<MiniPaisConConexiones>() {

            @Override
            public void success(MiniPaisConConexiones miniPaisConConexiones, Response response) {
                paisActual = miniPaisConConexiones;
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", error.getMessage());
                error.printStackTrace();
            }
        });
    }

    private void mostrarPista(Pista pista) {
        ((TextView) findViewById(R.id.pistaActual)).setText(String.valueOf(pista.getPista()));
    }
}
