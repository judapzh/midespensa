package com.judapzh.midespensa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private EditText email, contrasena;
    private Button inicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editTxt_email1);
        contrasena = findViewById(R.id.editTextTextPassword);
        inicio = findViewById(R.id.btn_ingreso);
    }

    public void Nuevo(View v) {
        Intent Nuevo = new Intent(this, Mainregistro.class);
        startActivity(Nuevo);
    }

    public void Ingresar(View view) {
        String correo = email.getText().toString();
        String Contrasena = contrasena.getText().toString();
        Adminsqlite dbHelper = new Adminsqlite(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        if (correo.isEmpty() || Contrasena.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Correo o Contraseña vacíos", Toast.LENGTH_SHORT).show();
            if(correo.isEmpty()){
                email.setError("Usuario vacío");
                email.requestFocus();
            } else if(Contrasena.isEmpty()){
                contrasena.setError("Contrasena vacía");
                contrasena.requestFocus();
            }
        } else if (correo.equals("iud2023") && Contrasena.equals("moviles")) {
            Toast.makeText(getApplicationContext(), "Bienvenido...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, listado.class);
            startActivity(intent);
        } else {
            Cursor cursor = db.rawQuery("SELECT * FROM registro WHERE email='" + correo + "' AND contrasena ='" + Contrasena + "'", null);
            if (cursor.moveToFirst()) {
                Intent Ingresar = new Intent(this, listado.class);
                Ingresar.putExtra("email", correo);
                Ingresar.putExtra("contrasena", Contrasena);
                startActivity(Ingresar);
                cursor.close();
            } else {
                Toast.makeText(getApplicationContext(), "Correo o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }

        }
    }



}


