package com.example.davicio.crudusuarios;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.davicio.R;
import com.example.davicio.adminActivity;
import com.example.davicio.adptadores.ListaUsuariosAdapter;
import com.example.davicio.contexto.DbSQLHelper;
import com.example.davicio.entidades.Usuarios;
import com.example.davicio.sinBarraSuperior;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListUserActivity extends sinBarraSuperior {
RecyclerView listausuarios;
private DbSQLHelper dbSQLHelper;
private SQLiteDatabase db;
private ExecutorService executorService;
private ArrayList<Usuarios> listaArrayUsuarios;
public  RecyclerView listuser;
ImageButton btnvolver;
    String nombreUsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_usuarios);

        listuser= findViewById(R.id.listaUsuarios);
        listuser.setLayoutManager(new LinearLayoutManager(ListUserActivity.this));
        btnvolver=findViewById(R.id.btnvolver);
        dbSQLHelper = new DbSQLHelper(ListUserActivity.this);
        listaArrayUsuarios= new ArrayList<>();


        executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                db = dbSQLHelper.getReadableDatabase();
                ListaUsuariosAdapter adapter = new ListaUsuariosAdapter(dbSQLHelper.mostrarUsuarios());
                listuser.setAdapter(adapter);
            }
        });
        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ListUserActivity.this, adminActivity.class);
                Bundle caja= getIntent().getExtras();
                intent.putExtras(caja);
                startActivity(intent);
            }
        });


    }
}