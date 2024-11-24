package com.lisbeth.proyectofinal.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    private SQLiteDatabase db;

    public Manager(Context context) {
        BdHelper dbHelper = new BdHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertarDatos(Datos datos) {
        ContentValues values = new ContentValues();
        values.put(Constantes.KEY_VELOCIDAD, datos.getVelocidadInicial());
        values.put(Constantes.KEY_ANGULO, datos.getAngulo());
        values.put(Constantes.KEY_ALTURA, datos.getAlturaInicial());
        values.put(Constantes.KEY_ALCANCE, datos.getAlcance());
        values.put(Constantes.KEY_ALTURA_MAX, datos.getAlturaMaxima());
        return db.insert(BdHelper.TABLE_DATOS, null, values);
    }

    public List<Datos> listarDatos() {
        List<Datos> datosList = new ArrayList<>();
        Cursor cursor = db.query(BdHelper.TABLE_DATOS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                double velocidad = cursor.getDouble(cursor.getColumnIndexOrThrow(Constantes.KEY_VELOCIDAD));
                double angulo = cursor.getDouble(cursor.getColumnIndexOrThrow(Constantes.KEY_ANGULO));
                double altura = cursor.getDouble(cursor.getColumnIndexOrThrow(Constantes.KEY_ALTURA));
                double alcance = cursor.getDouble(cursor.getColumnIndexOrThrow(Constantes.KEY_ALCANCE));
                double alturaMax = cursor.getDouble(cursor.getColumnIndexOrThrow(Constantes.KEY_ALTURA_MAX));

                Datos datos = new Datos(velocidad, angulo, altura, alcance, alturaMax);
                datos.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Constantes.KEY_ID)));
                datosList.add(datos);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return datosList;
    }
}