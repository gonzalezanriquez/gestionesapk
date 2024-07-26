package com.example.davicio.sucursales;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davicio.R;
import com.example.davicio.adminActivity;
import com.example.davicio.contexto.DbSQLHelper;
import com.example.davicio.sinBarraSuperior;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class addSucursalesActivity extends sinBarraSuperior {
    Button agregar;
    EditText nombre, direccion,telefono;
    TextView camposincompletos;
    ImageButton btnvolver;

    private DbSQLHelper dbSQLHelper;
    private SQLiteDatabase db;

    private ExecutorService executorService;
    String nombreUsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sucursales);

        btnvolver = findViewById(R.id.btnvolver);
        agregar=findViewById(R.id.btnagregarSucursal);
        nombre=findViewById(R.id.nombresucursal);
        direccion=findViewById(R.id.direccion);
        telefono=findViewById(R.id.editTextPrecio);

        dbSQLHelper = new DbSQLHelper(this);



        //HILO SECUNDARIO PARA LA CARGA DE LA BASE DE DATOS
        executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                db = dbSQLHelper.getWritableDatabase();


            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nombre.equals("") || !direccion.equals("")|| !telefono.equals(""))
                {
                    if( dbSQLHelper.insertSucursales(nombre.getText().toString(),direccion.getText().toString(),telefono.getText().toString()))
                    {

                        nombre.setText("");
                        direccion.setText("");
                        telefono.setText("");
                        Toast.makeText(addSucursalesActivity.this, "Sucursal Registrado con éxito", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(addSucursalesActivity.this, "Sucursal Ya registrada", Toast.LENGTH_SHORT).show();

                    }

                } else{
                    Toast.makeText(addSucursalesActivity.this, "Sucursal Ya registrada", Toast.LENGTH_SHORT).show();

                }

            }
        });

        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(addSucursalesActivity.this, adminActivity.class);

                Bundle caja= getIntent().getExtras();
                nombreUsu= caja.getString("nombre");
                Bundle bundle = new Bundle();
                bundle.putString("nombre", nombreUsu);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });



    }
}