package com.judapzh.midespensa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Adminsqlite extends SQLiteOpenHelper {
    // Nombre de la tabla "registro"
    public static final String TABLE_NAME = "registro";

    // Columnas de la tabla "registro"
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_CELULAR = "celular";
    public static final String COLUMN_CONTRASENA = "contrasena";
    public static final String COLUMN_PREGUNTA = "pregunta";
    public static final String COLUMN_RESPUESTA = "respuesta";

    // Nombre de la tabla "lista"
    public static final String LISTA = "lista";

    // Columnas de la tabla "lista"
    public static final String Id = "id";
    public static final String Producto = "producto";
    public static final String Cantidad = "cantidad";
    public static final String Costo = "costo";

    static final String DB_NAME = "mi_despensa";
    static final int DB_VERSION = 1;

    public Adminsqlite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla "registro"
        String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMAIL + " TEXT NOT NULL, " +
                COLUMN_NOMBRE + " TEXT NOT NULL, " +
                COLUMN_CELULAR + " TEXT NOT NULL, " +
                COLUMN_CONTRASENA + " TEXT NOT NULL, " +
                COLUMN_PREGUNTA + " TEXT NOT NULL, " +
                COLUMN_RESPUESTA + " TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE_QUERY);

        // Crear la tabla "lista"
        String CREATE_LISTA_TABLE_QUERY = "CREATE TABLE " + LISTA + " (" +
                Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Producto + " TEXT NOT NULL, " +
                Cantidad + " INTEGER NOT NULL, " +
                Costo + " INTEGER NOT NULL)";
        db.execSQL(CREATE_LISTA_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LISTA);
        onCreate(db);
    }
}
