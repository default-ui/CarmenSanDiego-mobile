package com.unq.tpi.uis.carmensandiego_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.unq.tpi.uis.carmensandiego_mobile.connection.CarmenSanConnection;
import com.unq.tpi.uis.carmensandiego_mobile.model.EstadoJuego;
import com.unq.tpi.uis.carmensandiego_mobile.model.Lugar;
import com.unq.tpi.uis.carmensandiego_mobile.model.MiniPaisConConexiones;
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
        obtenerLugares(this.estadoJuego.getPais().getId());
        setContentView(R.layout.activity_pedir_pista);
    }

    private void generarBotones(MiniPaisConConexiones paisConConexiones) {
        List<String> lugares = paisConConexiones.getLugares();
        ((TextView) findViewById(R.id.lugar1)).setText(String.valueOf(lugares.get(0)));
        ((TextView) findViewById(R.id.lugar2)).setText(String.valueOf(lugares.get(1)));
        ((TextView) findViewById(R.id.lugar3)).setText(String.valueOf(lugares.get(2)));
    }

    public void obtenerPista(View view) {
        CarmenSanDiegoService carmenSanDiegoService = new CarmenSanConnection().getService();
        String lugar = String.valueOf(((Button) view).getText());
        int idCaso = this.estadoJuego.getId();
        carmenSanDiegoService.getPista(idCaso, lugar, new Callback<Lugar>() {

            @Override
            public void success(Lugar lugar, Response response) {
                mostrarPista(lugar);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", error.getMessage());
                error.printStackTrace();
            }
        });
    }

    private void obtenerLugares(int idPais) {
        CarmenSanDiegoService carmenSanDiegoService = new CarmenSanConnection().getService();
        carmenSanDiegoService.getPais(idPais, new Callback<MiniPaisConConexiones>() {

            @Override
            public void success(MiniPaisConConexiones miniPaisConConexiones, Response response) {
                generarBotones(miniPaisConConexiones);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", error.getMessage());
                error.printStackTrace();
            }
        });
    }

    private void mostrarPista(Lugar lugar) {
        ((TextView) findViewById(R.id.pistaActual)).setText(String.valueOf(lugar.getPista()));
    }

    public void volverAPantallaPrincipal(View view){
       // Intent actualIntent =
        Intent detailIntent = new Intent(this, ViajarActivity.class);
        detailIntent.putExtra("EstadoJuego", this.getEstadoJuego());
        detailIntent.putExtra("OrdenPara", getIntent().getSerializableExtra("OrdenPara"));
        startActivity(detailIntent);
    }

    public EstadoJuego getEstadoJuego() {
        return estadoJuego;
    }

    public void setEstadoJuego(EstadoJuego estadoJuego) {
        this.estadoJuego = estadoJuego;
    }
}
