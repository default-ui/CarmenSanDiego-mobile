package com.unq.tpi.uis.carmensandiego_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class IniciarPartidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_partida);

    }

    public void iniciarPartida(View view){
        Intent detailIntent = new Intent(this, ResolverMisterioActivity.class);
        startActivity(detailIntent);
    }
}
