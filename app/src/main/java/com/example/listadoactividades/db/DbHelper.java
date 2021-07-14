package com.example.listadoactividades.db;


/* Esta clase se utiliza para crear la base de datos y sus tablas*/


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "todolist.db";
    public static final String TABLE_ACTIVIDADES = "t_actividades";

    public DbHelper(@Nullable Context context) {
        super(context,DATABASE_NOMBRE , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ACTIVIDADES + "("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT NOT NULL,"+
                "jornada TEXT NOT NULL," +
                "prioridad TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_ACTIVIDADES);
        onCreate(sqLiteDatabase);

    }
}
