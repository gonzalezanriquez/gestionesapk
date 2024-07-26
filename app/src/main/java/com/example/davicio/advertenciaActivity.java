package com.example.davicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class advertenciaActivity extends sinBarraSuperior {
    ImageButton soymayor, soymenor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertencia);

        soymayor = findViewById(R.id.btnsoymayor);
        soymenor = findViewById(R.id.btnsoymenor);

        soymayor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent soymayor= new Intent(advertenciaActivity.this, InicioActivity.class);
                Bundle caja= getIntent().getExtras();
                soymayor.putExtras(caja);
                startActivity(soymayor);
            }
        });
        soymenor.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent soymenor= new Intent(advertenciaActivity.this, MainActivity.class);
                 startActivity(soymenor);
             }
         });


    }
}