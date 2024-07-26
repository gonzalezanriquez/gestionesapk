package com.example.davicio;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.davicio.crudproductos.ListProductsActivity;
import com.example.davicio.sucursales.ListSucursalesActivity;

public class InicioActivity extends sinBarraSuperior {
    ImageButton sucursales,productos;
    TextView nombre;
    String nombreUsu;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        nombre= findViewById(R.id.titulohola);
        sucursales=findViewById(R.id.btnlistasucursales);
        productos=findViewById(R.id.btnlistaproductos);

        Bundle caja= getIntent().getExtras();
        nombreUsu= caja.getString("nombre");
        nombre.setText("¡Hola, "+ nombreUsu+"! ");

        sucursales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(InicioActivity.this, ListSucursalesActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("nombre", nombreUsu);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });
        productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(InicioActivity.this, ListProductsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("nombre", nombreUsu);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });





    }
}