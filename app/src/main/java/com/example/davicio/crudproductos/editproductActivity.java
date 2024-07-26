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

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class editproductActivity extends sinBarraSuperior {
    Button btneliminarproducto, btnEditarProducto, btnconsultarProducto;
    ImageButton btnvolver;
    EditText nombre, descripcion, precio, id;
    Map<String, String> info;
    private DbSQLHelper dbSQLHelper;
    private SQLiteDatabase db;
    ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editproduct);

        btnconsultarProducto = findViewById(R.id.btnconsultarProducto);
        btnEditarProducto = findViewById(R.id.btnEditarProducto);
        btneliminarproducto = findViewById(R.id.btneliminarproducto);

        id = findViewById(R.id.editIdProducto);
        nombre = findViewById(R.id.editTextNombreproducto);
        descripcion = findViewById(R.id.editTextDescripcionproducto);
        precio = findViewById(R.id.editTextPrecioproducto);
        btnvolver = findViewById(R.id.btnvolver);

        dbSQLHelper = new DbSQLHelper(this);

        executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                db = dbSQLHelper.getWritableDatabase();

                btnconsultarProducto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int productId = Integer.parseInt(id.getText().toString());
                        info = dbSQLHelper.consultaproductos(productId);
                        if (info != null) {
                            nombre.setText(info.get("nombre"));
                            descripcion.setText(info.get("descripcion"));
                            precio.setText(info.get("precio"));
                        } else {
                            Toast.makeText(editproductActivity.this, "No se encontró el producto con el ID especificado", Toast.LENGTH_SHORT).show();
                            nombre.setText("");
                            descripcion.setText("");
                            precio.setText("");
                        }
                    }
                });

                btnEditarProducto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String query=dbSQLHelper.queryEditarProducto(Integer.parseInt(id.getText().toString()),nombre.getText().toString(), descripcion.getText().toString(),precio.getText().toString());
                        db.execSQL(query);

                        id.setText("");
                        nombre.setText("");
                        descripcion.setText("");
                        precio.setText("");

                        Toast.makeText(editproductActivity.this, "PRODUCTO EDITADO DE MANERA CORRECTA", Toast.LENGTH_SHORT).show();


                    }
                });

                btneliminarproducto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String query=dbSQLHelper.queryEliminarProducto(Integer.parseInt(id.getText().toString()));
                        db.execSQL(query);
                        id.setText("");
                        nombre.setText("");
                        descripcion.setText("");
                        precio.setText("");

                        Toast.makeText(editproductActivity.this, "PRODUCTO ELIMINADO DE MANERA CORRECTA", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editproductActivity.this, adminActivity.class);
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
