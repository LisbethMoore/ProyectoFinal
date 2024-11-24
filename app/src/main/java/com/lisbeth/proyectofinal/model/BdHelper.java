package com.lisbeth.proyectofinal.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BdHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "parabolico.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_DATOS = "Datos";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_VELOCIDAD = "velocidad_inicial";
    public static final String COLUMN_ANGULO = "angulo";
    public static final String COLUMN_ALTURA = "altura_inicial";
    public static final String COLUMN_ALCANCE = "alcance";
    public static final String COLUMN_ALTURA_MAX = "altura_maxima";

    private static final String CREATE_TABLE_DATOS =
            "CREATE TABLE " + TABLE_DATOS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_VELOCIDAD + " REAL, " +
                    COLUMN_ANGULO + " REAL, " +
                    COLUMN_ALTURA + " REAL, " +
                    COLUMN_ALCANCE + " REAL, " +
                    COLUMN_ALTURA_MAX + " REAL)";

    public BdHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DATOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATOS);
        onCreate(db);
    }
}