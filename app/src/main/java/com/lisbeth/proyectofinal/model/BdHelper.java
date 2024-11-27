    package com.lisbeth.proyectofinal.model;

    import android.content.Context;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    import androidx.annotation.Nullable;

    public class BdHelper extends SQLiteOpenHelper {

        public BdHelper(@Nullable Context context) {
            super(context, Constantes.NAME_DB, null, Constantes.VERSION_DB);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            // llamo la sentencia de la tabla
            sqLiteDatabase.execSQL(Constantes.SENTENCIATABLA);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        }
    }
