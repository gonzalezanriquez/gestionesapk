package com.example.davicio.crudusuarios;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.davicio.R;
import com.example.davicio.adminActivity;
import com.example.davicio.contexto.DbSQLHelper;
import com.example.davicio.sinBarraSuperior;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class addusuarioActivity extends sinBarraSuperior {
    private DbSQLHelper dbSQLHelper;
    private SQLiteDatabase db;
    ImageButton btnvolver;
    Button añadir;
    EditText nombre, apellido, mail, contrasenia;
    TextView camposincompletos;
    ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addusuario);

        btnvolver = findViewById(R.id.btnvolver);
        añadir = findViewById(R.id.buttonadduser);
        nombre = findViewById(R.id.editTextNombre);
        apellido = findViewById(R.id.editTextApellido);
        mail = findViewById(R.id.editTextMail);
        contrasenia = findViewById(R.id.editTextContrasenia);
        camposincompletos = findViewById(R.id.camposincompletos);

        dbSQLHelper = new DbSQLHelper(this);

        executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                db = dbSQLHelper.getWritableDatabase();

                añadir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nombreText = nombre.getText().toString();
                        String apellidoText = apellido.getText().toString();
                        String mailText = mail.getText().toString();
                        String contraseniaText = contrasenia.getText().toString();

                        if (nombreText.isEmpty() || apellidoText.isEmpty() || mailText.isEmpty() || contraseniaText.isEmpty()) {
                            camposincompletos.setText(R.string.camposincompletos);
                        } else {
                            if (dbSQLHelper.insertUsuario(nombreText, apellidoText, mailText, contraseniaText)) {
                                nombre.setText("");
                                apellido.setText("");
                                mail.setText("");
                                contrasenia.setText("");
                                camposincompletos.setText("Usuario Registrado con éxito");
                            } else {
                                camposincompletos.setText(R.string.yaregistrado);
                            }
                        }
                    }
                });
            }
        });



        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addusuarioActivity.this, adminActivity.class);
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
