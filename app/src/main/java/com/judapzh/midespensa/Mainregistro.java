package com.judapzh.midespensa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Mainregistro extends AppCompatActivity {

    private EditText nombre, celular, email, contrasena, pregunta, respuesta;
    private Button registro, consultar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainregistro);

        // Inicializar las vistas
        nombre = findViewById(R.id.editText_PersonName);
        celular = findViewById(R.id.editTextPhone);
        email = findViewById(R.id.editTextTextEmailAddress);
        contrasena = findViewById(R.id.editText_Password);
        pregunta = findViewById(R.id.editText_preguntaseg);
        respuesta = findViewById(R.id.editText_respuesta);
        registro = findViewById(R.id.btn_registro);
        consultar = findViewById(R.id.btn_consultar);

    }
    public void volver (View v) {
        Intent Nuevo = new Intent(this, login.class);
        startActivity(Nuevo);
    }
    public void registrar(View view) {
        // Obtener los valores de las vistas
        String Nombre = nombre.getText().toString();
        String Celular = celular.getText().toString();
        String Correo = email.getText().toString();
        String Contrasena = contrasena.getText().toString();
        String Pregunta = pregunta.getText().toString();
        String Respuesta = respuesta.getText().toString();

        Adminsqlite admin = new Adminsqlite(this);
        SQLiteDatabase db = admin.getWritableDatabase();

        if (Nombre.isEmpty() ||Celular.isEmpty() || Correo.isEmpty() || Contrasena.isEmpty() || Pregunta.isEmpty() || Respuesta.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Uno o más campos están vacíos", Toast.LENGTH_SHORT).show();
            if (Nombre.isEmpty()) {
               nombre.setError("Producto vacío");
                nombre.requestFocus();
            }
            if (Celular.isEmpty()) {
                celular.setError("Cantidad vacía");
                celular.requestFocus();
            }
            if (Correo.isEmpty()) {
                email.setError("Cantidad vacía");
                email.requestFocus();
            }
            if (Contrasena.isEmpty()) {
                contrasena.setError("Cantidad vacía");
                contrasena.requestFocus();
            }
            if (Pregunta.isEmpty()) {
                pregunta.setError("Cantidad vacía");
                pregunta.requestFocus();
            }
            if (Respuesta.isEmpty()) {
               respuesta.setError("Costo vacío");
              respuesta.requestFocus();
            }
        } else {

        ContentValues datos = new ContentValues();
        datos.put("nombre", Nombre);
        datos.put("celular", Celular);
        datos.put("email", Correo);
        datos.put("contrasena", Contrasena);
        datos.put("pregunta", Pregunta);
        datos.put("respuesta", Respuesta);
        db.insert(Adminsqlite.TABLE_NAME, null, datos);

        Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
        nombre.setText("");
        celular.setText("");
        email.setText("");
        contrasena.setText("");
        pregunta.setText("");
        respuesta.setText("");

        Intent login = new Intent(this, login.class);
        startActivity(login);
    }
    }

    public void Consultar(View view) {
        String Correo = email.getText().toString(); // Obtener el correo a consultar
        Adminsqlite admin = new Adminsqlite(this);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM registro WHERE email='" + Correo + "'", null);

        if(cursor!=null){
        if (cursor.moveToFirst()) {
            nombre.setText(cursor.getString(1));
            celular.setText(cursor.getString(3));
            contrasena.setText(cursor.getString(4)); // Corrección: índice 3 en lugar de 4
            pregunta.setText(cursor.getString(5)); // Corrección: índice 4 en lugar de 5
            respuesta.setText(cursor.getString(6)); // Corrección: índice 5 en lugar de 6
        } else {
            Toast.makeText(this, "Correo no encontrado", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

}
}


