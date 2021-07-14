package com.example.listadoactividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.listadoactividades.adaptadores.ListaActividadesAdapter;
import com.example.listadoactividades.db.DbActividades;
import com.example.listadoactividades.db.DbHelper;
import com.example.listadoactividades.entidades.Actividades;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaActividades;
    ArrayList<Actividades> listaArrayActividades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaActividades = findViewById(R.id.listaActividades);
        listaActividades.setLayoutManager(new LinearLayoutManager(this));

        DbActividades dbActividades = new DbActividades(MainActivity.this);

        listaArrayActividades = new ArrayList<>();

        ListaActividadesAdapter adapter = new ListaActividadesAdapter(dbActividades.mostrarActividades());
        listaActividades.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoActividad();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void nuevoActividad(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }

}