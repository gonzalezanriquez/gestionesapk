package com.example.davicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.davicio.crudproductos.ListProductsActivity;
import com.example.davicio.crudproductos.addproductoActivity;
import com.example.davicio.crudproductos.editproductActivity;
import com.example.davicio.sucursales.ListSucursalesActivity;
import com.example.davicio.crudusuarios.ListUserActivity;
import com.example.davicio.crudusuarios.addusuarioActivity;
import com.example.davicio.crudusuarios.edtuserActivity;
import com.example.davicio.sucursales.addSucursalesActivity;
import com.example.davicio.sucursales.edtSucursalesActivity;

public class adminActivity extends sinBarraSuperior {

    ImageButton crudusu,crudprod, crudsucur,añadirusuario,añadirproductos,añadirsucursales,btnaddprod,btnaddsucu, listuser,listproduct, listsucursales;
    TextView title;
    String nombreUsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        añadirusuario=findViewById(R.id.btnaddusu);
        añadirproductos=findViewById(R.id.btnaddprod);
        añadirsucursales=findViewById(R.id.btnaddsucu);

        crudusu=findViewById(R.id.btncrudusuarios);
        crudprod=findViewById(R.id.btncrudproductos);
        crudsucur=findViewById(R.id.btncrudsuucursales);

        listuser=findViewById(R.id.btnlistUsu);
        listproduct=findViewById(R.id.btnlistaproductos);
        listsucursales=findViewById(R.id.btnlistasucursales);

        title=findViewById(R.id.titulonombre);

        /*NOMBRE DEL USUARIO EN EL TITULO*/
        Bundle caja= getIntent().getExtras();
        nombreUsu=caja.getString("nombre");
        title.setText("Hola, "+nombreUsu+"!");

        /*UPDATE,DELETE------------------------------------------------*/
        crudusu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, edtuserActivity.class);
                Bundle caja= getIntent().getExtras();
                intent.putExtras(caja);
                startActivity(intent);
            }
        });
        crudprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, editproductActivity.class);
                Bundle caja= getIntent().getExtras();
                intent.putExtras(caja);
                startActivity(intent);
            }
        });
        crudsucur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, edtSucursalesActivity.class);
                Bundle caja= getIntent().getExtras();
                intent.putExtras(caja);
                startActivity(intent);
            }
        });
        /*INSERT------------------------------------------------*/
        añadirusuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, addusuarioActivity.class);
                Bundle caja= getIntent().getExtras();
                intent.putExtras(caja);
                startActivity(intent);
            }
        });
        añadirproductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, addproductoActivity.class);
                Bundle caja= getIntent().getExtras();
                intent.putExtras(caja);
                startActivity(intent);
            }
        });
        añadirsucursales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, addSucursalesActivity.class);
                Bundle caja= getIntent().getExtras();
                intent.putExtras(caja);
                startActivity(intent);
            }
        });

        /*LISTAS------------------------------------------------*/
        listuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, ListUserActivity.class);
                Bundle caja= getIntent().getExtras();
                intent.putExtras(caja);
                startActivity(intent);
            }
        });
        listproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, ListProductsActivity.class);
                Bundle caja= getIntent().getExtras();
                intent.putExtras(caja);
                startActivity(intent);
            }
        });
        listsucursales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, ListSucursalesActivity.class);
                Bundle caja= getIntent().getExtras();
                intent.putExtras(caja);
                startActivity(intent);
            }
        });
    }

}