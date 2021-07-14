package com.example.listadoactividades.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.listadoactividades.entidades.Actividades;

import java.util.ArrayList;

public class DbActividades extends DbHelper{

    Context context;

    public DbActividades(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarActividad(String nombre, String jornada, String prioridad) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("jornada", jornada);
            values.put("prioridad", prioridad);

            id = db.insert(TABLE_ACTIVIDADES, null, values);
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList<Actividades> mostrarActividades(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Actividades> listaActividades = new ArrayList<>();
        Actividades actividad = null;
        Cursor cursorActividades = null;

        cursorActividades = db.rawQuery("SELECT * FROM " + TABLE_ACTIVIDADES, null);

        if(cursorActividades.moveToFirst()){
            do{
                actividad = new Actividades();
                actividad.setId(cursorActividades.getInt(0));
                actividad.setNombre(cursorActividades.getString(1));
                actividad.setJornada(cursorActividades.getString(2));
                actividad.setPrioridad(cursorActividades.getString(3));
                listaActividades.add(actividad);
            } while (cursorActividades.moveToNext());
        }
        cursorActividades.close();

        return listaActividades;
    }
    public Actividades verActividades(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Actividades actividad = null;
        Cursor cursorActividades;

        cursorActividades = db.rawQuery("SELECT * FROM " + TABLE_ACTIVIDADES + " WHERE id = " + id + " LIMIT 1", null);

        if(cursorActividades.moveToFirst()){

                actividad = new Actividades();
                actividad.setId(cursorActividades.getInt(0));
                actividad.setNombre(cursorActividades.getString(1));
                actividad.setJornada(cursorActividades.getString(2));
                actividad.setPrioridad(cursorActividades.getString(3));


        }
        cursorActividades.close();

        return actividad;
    }

    public boolean editarActividad(int id, String nombre, String jornada, String prioridad) {

       boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
           db.execSQL("UPDATE " + TABLE_ACTIVIDADES + " SET nombre = '"+nombre+"', jornada = '"+jornada+"', prioridad = '"+prioridad+"'WHERE id='" + id + "'  ");
            correcto = true;
        } catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    public boolean eliminarActividad(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_ACTIVIDADES + " WHERE id = '"+id+"'");
            correcto = true;
        } catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
}
