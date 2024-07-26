package com.example.davicio.crudproductos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.davicio.R;
import com.example.davicio.adminActivity;
import com.example.davicio.contexto.DbSQLHelper;
import com.example.davicio.sinBarraSuperior;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class addproductoActivity extends sinBarraSuperior {

    Button agregar;
    EditText nombre, descripcion,precio;
    ImageButton btnvolver;
    private DbSQLHelper dbSQLHelper;
    private SQLiteDatabase db;
    ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproducto);

        agregar=findViewById(R.id.agregarProductoButton);
        nombre=findViewById(R.id.editTextNombre);
        descripcion=findViewById(R.id.editTextDescripcion);
        precio=findViewById(R.id.editTextPrecio);
        btnvolver = findViewById(R.id.btnvolver);

        dbSQLHelper = new DbSQLHelper(this);


        executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                db = dbSQLHelper.getWritableDatabase();
                agregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nombreText = nombre.getText().toString();
                        String descripcionText = descripcion.getText().toString();
                        String precioText = precio.getText().toString();


                        if (nombreText.isEmpty() || descripcionText.isEmpty() || precioText.isEmpty()) {
                            Toast.makeText(addproductoActivity.this, "DEBE COMPLETAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                        } else {
                            if (dbSQLHelper.insertProductos(nombreText, descripcionText, precioText)) {
                                nombre.setText("");
                                descripcion.setText("");
                                precio.setText("");
                                Toast.makeText(addproductoActivity.this, "PRODUCTO REGISTRADO CON ÉXITO", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(addproductoActivity.this, "PRODUCTO YA REGISTRADO", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });

        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addproductoActivity.this, adminActivity.class);
                Bundle caja= getIntent().getExtras();
                intent.putExtras(caja);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
            if (db != null) {
            db.close();
        }
        executorService.shutdown();
    }
}