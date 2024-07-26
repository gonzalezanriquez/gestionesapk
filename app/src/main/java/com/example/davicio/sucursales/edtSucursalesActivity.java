package com.example.davicio.sucursales;

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

public class edtSucursalesActivity extends sinBarraSuperior {
    Button btneliminarSucursal, btnEditarSucursal, btnconsultarSucursal;
    ImageButton btnvolver;
    EditText nombre, direccion, telefono,id;
    Map<String, String> info;
    private DbSQLHelper dbSQLHelper;
    private SQLiteDatabase db;
    ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edt_sucursales);

        btnvolver = findViewById(R.id.btnvolver);
        btnconsultarSucursal = findViewById(R.id.btnconsultarSucursal);
        btnEditarSucursal = findViewById(R.id.btnEditarSucursal);
        btneliminarSucursal = findViewById(R.id.btneliminarSucursal);
        nombre = findViewById(R.id.editNombreSucursal);
        direccion = findViewById(R.id.direccion);
        telefono = findViewById(R.id.telefono);
        id = findViewById(R.id.editIdSucursal);

        dbSQLHelper = new DbSQLHelper(this);
        db = dbSQLHelper.getWritableDatabase();



        btnconsultarSucursal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int productId = Integer.parseInt(id.getText().toString());
                info = dbSQLHelper.consultasucursales(productId);
                if (info != null) {
                    nombre.setText(info.get("nombre"));
                    direccion.setText(info.get("direccion"));
                    telefono.setText(info.get("telefono"));
                } else {
                    Toast.makeText(edtSucursalesActivity.this, "No se encontró la sucursal con el ID especificado", Toast.LENGTH_SHORT).show();
                    nombre.setText("");
                    direccion.setText("");
                    telefono.setText("");
                    id.setText("");
                }
            }
        });

        btnEditarSucursal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sucursalId = Integer.parseInt(id.getText().toString());
                String nuevoNombre = nombre.getText().toString();
                String nuevaDireccion = direccion.getText().toString();
                String nuevoTelefono = telefono.getText().toString();

                String query = dbSQLHelper.queryEditarSucursal(sucursalId, nuevoNombre, nuevaDireccion, nuevoTelefono);
                db.execSQL(query);

                nombre.setText("");
                direccion.setText("");
                telefono.setText("");
                id.setText("");

                Toast.makeText(edtSucursalesActivity.this, "SUCURSAL EDITADA DE MANERA CORRECTA", Toast.LENGTH_SHORT).show();
            }
        });



        btneliminarSucursal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String query=dbSQLHelper.queryEliminarSucursal(Integer.parseInt(id.getText().toString()));
                db.execSQL(query);
                nombre.setText("");
                direccion.setText("");
                telefono.setText("");
                id.setText("");

                Toast.makeText(edtSucursalesActivity.this, "SUCURSAL ELIMINADA DE MANERA CORRECTA", Toast.LENGTH_SHORT).show();

            }
        });
        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(edtSucursalesActivity.this, adminActivity.class);
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