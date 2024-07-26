package com.example.davicio;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.davicio.contexto.DbSQLHelper;


public class RegistrarseActivity extends sinBarraSuperior {
    ImageButton registro, cancelar;
    EditText nombre, apellido, mail, contrasenia;
    TextView camposincompletos;
    int id = 1;

    private DbSQLHelper dbSQLHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        registro = findViewById(R.id.btnregistrarse);
        cancelar = findViewById(R.id.btncancelarregistro);
        nombre = findViewById(R.id.nombreregisro);
        apellido = findViewById(R.id.apellidoresgistro);
        mail = findViewById(R.id.mailregistro);
        contrasenia = findViewById(R.id.contraseniaresgistro);
        camposincompletos = findViewById(R.id.camposincompletos);

        dbSQLHelper = new DbSQLHelper(this);
        db = dbSQLHelper.getWritableDatabase();

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if (!nombre.equals("") || !apellido.equals("")|| !mail.equals("")|| !contrasenia.equals("")){
                    if( dbSQLHelper.insertUsuario(nombre.getText().toString(),apellido.getText().toString(),mail.getText().toString(),contrasenia.getText().toString())){


                        Intent register = new Intent(RegistrarseActivity.this, LoginActivity.class);
                        startActivity(register);
                        nombre.setText("");
                        apellido.setText("");
                        mail.setText("");
                        contrasenia.setText("");
                    }else{
                        camposincompletos.setText(R.string.yaregistrado);
                    }


               }
                else{
                   camposincompletos.setText(R.string.camposincompletos);
               }
            }
        });
        ;
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancel = new Intent(RegistrarseActivity.this, MainActivity.class);
                startActivity(cancel);
            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}