package com.unq.tpi.uis.carmensandiego_mobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ResolverMisterioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolver_misterio);

    }





    public void ordenDeArrestooo(View view){
        Intent detailIntent = new Intent(this, OrdenDeArrestoActivity.class);
        startActivity(detailIntent);
    }



}
