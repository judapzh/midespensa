package com.judapzh.midespensa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.judapzh.midespensa.Adminsqlite;
import com.judapzh.midespensa.R;

public class listado extends AppCompatActivity {

    private EditText producto, cantidad, costo;
    private Button agregar, consultar, actualizar, eliminar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        //actualizar vistas//
        producto = findViewById(R.id.editTxt_producto);
        cantidad = findViewById(R.id.editTextcantidad);
        costo = findViewById(R.id.editTextcosto);
        agregar = findViewById(R.id.button);
        consultar = findViewById(R.id.btnconsul);
        actualizar = findViewById(R.id.btnActualiza);
        eliminar = findViewById(R.id.btnElimina);
    }


    public void registrar(View view) {
        // Obtener los valores de las vistas
        String Producto = producto.getText().toString();
        String Cantidad = cantidad.getText().toString();
        String Costo = costo.getText().toString();

        Adminsqlite admin = new Adminsqlite(this);
        SQLiteDatabase db = admin.getWritableDatabase();

        if (Producto.isEmpty() || Cantidad.isEmpty() || Costo.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Uno o más campos están vacíos", Toast.LENGTH_SHORT).show();
            if (Producto.isEmpty()) {
                producto.setError("Producto vacío");
                producto.requestFocus();
            }
            if (Cantidad.isEmpty()) {
                cantidad.setError("Cantidad vacía");
                cantidad.requestFocus();
            }
            if (Costo.isEmpty()) {
                costo.setError("Costo vacío");
                costo.requestFocus();
            }
        } else {
            ContentValues datos = new ContentValues();
            datos.put("producto", Producto);
            datos.put("cantidad", Cantidad);
            datos.put("costo", Costo);

            db.insert(Adminsqlite.LISTA, null, datos);

            Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();

            // Limpia los campos después de la inserción exitosa
            producto.setText("");
            cantidad.setText("");
            costo.setText("");
        }
    }

    // metodo listar//
    public void Consultar(View view) {
        String Producto = producto.getText().toString();
        String Cantidad = cantidad.getText().toString();
        String Costo = costo.getText().toString();

        Adminsqlite admin = new Adminsqlite(this);
        SQLiteDatabase db = admin.getWritableDatabase();


        Cursor cursor2 = db.rawQuery("SELECT * FROM LISTA WHERE producto='" + Producto + "'", null);
        if (cursor2 != null) {
            if (cursor2.moveToFirst()) {
                cantidad.setText(cursor2.getString(2));
                costo.setText(cursor2.getString(3));
            }
        }
        cursor2.close();
    }

    //metodo eliminar//
    public void Eliminar(View view) {
        String Producto = producto.getText().toString();

        Adminsqlite admin = new Adminsqlite(this);
        SQLiteDatabase db = admin.getWritableDatabase();

        int res = db.delete("LISTA", "producto='" + Producto + "'", null);
        if (res != 0) {
            Toast.makeText(this, "Producto Eliminado con exito", Toast.LENGTH_SHORT).show();
            producto.setText("");
            cantidad.setText("");
            costo.setText("");
        } else {
            Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show();

        }

    }

    //metodo Actualizar//
    public void Actualizar(View view) {
        String Producto = producto.getText().toString();
        String Cantidad = cantidad.getText().toString();
        String Costo = costo.getText().toString();

        Adminsqlite admin = new Adminsqlite(this);
        SQLiteDatabase db = admin.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("producto", Producto);
        valores.put("cantidad", Cantidad);
        valores.put("Costo", Costo);

        int res = db.update("LISTA", valores, "producto='" + Producto + "' ", null);
        if (res != 0) {
            Toast.makeText(this, "Actualizado con exito", Toast.LENGTH_SHORT).show();
            producto.setText("");
            cantidad.setText("");
            costo.setText("");
        }

    }
    /*public void Salir (View view) {

        finish(); // Cierra la actividad actual

        moveTaskToBack(true); // Lleva la aplicación al fondo
    }*/

}
