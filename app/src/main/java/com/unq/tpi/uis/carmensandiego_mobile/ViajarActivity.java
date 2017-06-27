package com.unq.tpi.uis.carmensandiego_mobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.unq.tpi.uis.carmensandiego_mobile.model.EstadoJuego;

public class ViajarActivity extends AppCompatActivity{


    private EstadoJuego estadoActual;

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
        ///
    }

    public void setDataJuego(){
        ((TextView) findViewById(R.id.paisActual)).setText(String.valueOf(estadoActual.getPais().getNombre()));

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

}
