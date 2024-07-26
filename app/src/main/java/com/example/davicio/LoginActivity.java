package com.example.davicio;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.davicio.contexto.DbSQLHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends sinBarraSuperior {

    ImageButton login, register;
    EditText mail, contrasenia;
    TextView camposincompletoslogin;
    private DbSQLHelper dbSQLHelper;
    private SQLiteDatabase db;
    private ExecutorService executorService;

    int idUsuario;
    String nombreUsuario,apellidoUsuario,mailUsuario,conteaseniaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.btnlogin);
        register = findViewById(R.id.btnloginresgistro);
        mail = findViewById(R.id.maillogin);
        contrasenia = findViewById(R.id.contrasenialogin);
        camposincompletoslogin = findViewById(R.id.camposincompletoslogin);
        dbSQLHelper = new DbSQLHelper(this);



        //HILO SECUNDARIO PARA LA CARGA DE LA BASE DE DATOS
        executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
               db = dbSQLHelper.getReadableDatabase();

            }
        });


/*ELIMINAR LOS MENSAJES AL INGRESAR EN EL CAMPO MAIL O CONTRASEÑA*/

        mail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                camposincompletoslogin.setText("");}
            @Override
            public void afterTextChanged(Editable editable) {}

        });
        contrasenia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                camposincompletoslogin.setText("");}
            @Override
            public void afterTextChanged(Editable editable) { }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validacionmail() & validacioncontrasenia())
                    checkuser();
                else if (validacionmail())
                    camposincompletoslogin.setText(R.string.contraseniaincompleta);
                else if (validacioncontrasenia())
                    camposincompletoslogin.setText(R.string.mailincompleto);
                else
                    camposincompletoslogin.setText(R.string.ingresadatos);

            }

        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegistrarseActivity.class);
                startActivity(registerIntent);
            }
        });
    }

    public Boolean validacionmail() {
        String val = mail.getText().toString();

        if (val.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean validacioncontrasenia() {
        String val = contrasenia.getText().toString();

        if (val.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void checkuser() {
        String correo = mail.getText().toString();
        String pass = contrasenia.getText().toString();

        executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = db.rawQuery("SELECT * FROM usuario WHERE mail = '" + correo + "' AND contrasenia = '" + pass + "'", null);
                /*NOMBRE DE USUARIO*/
                while (cursor.moveToNext()) {

                    int idUsuario = cursor.getInt(0);
                     nombreUsuario = cursor.getString(1);
                     apellidoUsuario = cursor.getString(2);
                     mailUsuario = cursor.getString(3);
                     conteaseniaUsuario = cursor.getString(4);


                }

                boolean mailcontrasenia = cursor.moveToFirst();

                cursor.close();

                if (mailcontrasenia) {
                    // si es admin
                    if(mailUsuario.equalsIgnoreCase("mail")){
                        Intent registerIntent = new Intent(LoginActivity.this, adminActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("nombre", nombreUsuario);
                        bundle.putString("mail", mailUsuario);
                        registerIntent.putExtras(bundle);
                        startActivity(registerIntent);
                    } else{
                        Intent registerIntent = new Intent(LoginActivity.this, advertenciaActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("nombre", nombreUsuario);
                        bundle.putString("mail", mailUsuario);
                        registerIntent.putExtras(bundle);
                        startActivity(registerIntent);
                    }
                } else {
                    Cursor correoCursor = db.rawQuery("SELECT * FROM usuario WHERE mail = '" + correo + "'", null);
                    boolean mail = correoCursor.moveToFirst();

                    correoCursor.close();

                    if (mail) {
                        contraseniaIncorrecta();
                    } else {
                        usuarioContraseniaIncorrecta();
                    }
                }
            }
        });
    }


    private void contraseniaIncorrecta() {
        camposincompletoslogin.setText(R.string.contrasenianoencontrada);
    }
    private void usuarioContraseniaIncorrecta() {
        camposincompletoslogin.setText(R.string.usuarionoencontrado);
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
