package com.example.davicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends sinBarraSuperior {


    ImageButton ingresar, resgistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingresar=findViewById(R.id.btninicioingresar);
        resgistrarse=findViewById(R.id.btnregistrarse);

        resgistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(MainActivity.this, RegistrarseActivity.class);
                startActivity(register);
            }
        });

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login= new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });

    }
}