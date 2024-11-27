package com.lisbeth.proyectofinal.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Manager {

    private BdHelper bdHelper;
    private SQLiteDatabase db;

    public Manager(Context context) {
        bdHelper = new BdHelper(context);
    }

    public void openBdWr() {
        db = bdHelper.getWritableDatabase();
    }

    public void openBdRd() {
        db = bdHelper.getReadableDatabase();
    }
    // Método para cerrar la base de datos de manera segura
    public void closeBd() {
        //verifica si la base de datos no es null y está abierta
        if (db != null && db.isOpen()) {
            //si la base de datos está abierta, la cierra
            db.close();
        }
    }

    public long insertarDatos(Datos datos) {
        openBdWr();
        ContentValues values = new ContentValues();
        values.put("velocidad_inicial", datos.getVelocidadInicial());
        values.put("angulo", datos.getAngulo());
        values.put("altura_inicial", datos.getAlturaInicial());
        values.put("alcance", datos.getAlcance());
        values.put("altura_maxima", datos.getAlturaMaxima());
        long result = db.insert("Datos", null, values);
        closeBd();
        return result;
    }

    public ArrayList<Datos> listarDatos() {
        openBdRd();
        ArrayList<Datos> lista = new ArrayList<>();
        Cursor cursor = db.query("Datos", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Datos datos = new Datos();
                datos.setId(cursor.getInt(0));
                datos.setVelocidadInicial(cursor.getDouble(1));
                datos.setAngulo(cursor.getDouble(2));
                datos.setAlturaInicial(cursor.getDouble(3));
                datos.setAlcance(cursor.getDouble(4));
                datos.setAlturaMaxima(cursor.getDouble(5));
                lista.add(datos); // almacenando los datos en la lista

            } while (cursor.moveToNext());
        }
        return lista;
    }
}