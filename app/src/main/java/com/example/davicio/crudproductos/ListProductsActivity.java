package com.example.davicio.crudproductos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.davicio.InicioActivity;
import com.example.davicio.R;
import com.example.davicio.adminActivity;
import com.example.davicio.adptadores.ListaProductosAdapter;
import com.example.davicio.contexto.DbSQLHelper;
import com.example.davicio.entidades.Productos;
import com.example.davicio.sinBarraSuperior;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListProductsActivity extends sinBarraSuperior {
RecyclerView listaproductos;
private DbSQLHelper dbSQLHelper;
private SQLiteDatabase db;
private ExecutorService executorService;
private ArrayList<Productos> listaArrayProductos;
public  RecyclerView listproductos;
ImageButton btnvolver;
String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_productos);


        listproductos= findViewById(R.id.listadeProductos);
        listproductos.setLayoutManager(new LinearLayoutManager(ListProductsActivity.this));
        btnvolver=findViewById(R.id.btnvolver);
        dbSQLHelper = new DbSQLHelper(ListProductsActivity.this);
        listaArrayProductos= new ArrayList<>();

        Bundle caja= getIntent().getExtras();
        mail=caja.getString("mail");

        executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                db = dbSQLHelper.getReadableDatabase();
                ListaProductosAdapter adapter = new ListaProductosAdapter(dbSQLHelper.mostrarProductos());
                listproductos.setAdapter(adapter);
            }
        });

        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mail.equals("mail")){
                Intent intent=new Intent(ListProductsActivity.this, adminActivity.class);
                    Bundle caja= getIntent().getExtras();
                    intent.putExtras(caja);
                    startActivity(intent);
                }else{
                    Intent intent=new Intent(ListProductsActivity.this, InicioActivity.class);
                    Bundle caja= getIntent().getExtras();
                    intent.putExtras(caja);
                    startActivity(intent);
                }
            }

        });
    }
}